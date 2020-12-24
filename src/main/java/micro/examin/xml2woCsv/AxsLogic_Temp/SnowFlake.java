package micro.examin.xml2woCsv.AxsLogic_Temp;

import de.siegmar.fastcsv.reader.CsvParser;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import de.siegmar.fastcsv.writer.CsvAppender;
import de.siegmar.fastcsv.writer.CsvWriter;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class SnowFlake {
	private static String PRODUCT;
	private static String PRODUCT_TYPE;
	private static SnfFilesReader TESTOBJ;
	private static ArrayList<String> HEADER;

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		PRODUCT = "PL";
		PRODUCT_TYPE= "PORTM";
		String dataFile = "";
		File inputCsvPath = new File("/home/examin/Documents/POC/es-model/Axslogic Utils/Data/"+PRODUCT+"/"+PRODUCT_TYPE+"/"+dataFile);
		File outputCombinedPath = new File("/home/examin/Documents/POC/es-model/Axslogic Utils/CombinedTempData/"+PRODUCT+"/"+PRODUCT_TYPE+"/combined/Tempout.csv");
		File snfFolderPath = new File("/home/examin/Documents/POC/es-model/Axslogic Utils/SNF/"+PRODUCT);

		CsvWriter csvWriter = new CsvWriter();
		CsvReader csvReader = new CsvReader();


		csvWriter.setFieldSeparator('|');
		csvReader.setFieldSeparator('|');

		csvWriter.setAlwaysDelimitText(true);
		csvReader.setContainsHeader(true);

		//Collection<String[]> outputData = new ArrayList<>();

		//outputData.add(getFinalSnfCsvHeader());

		try (CsvAppender csvAppender = csvWriter.append(outputCombinedPath, StandardCharsets.UTF_8)) {
			CsvParser csv = csvReader.parse(inputCsvPath, StandardCharsets.UTF_8);
			CsvRow row ;

			int tempIndex = 0;
			while ((row = csv.nextRow()) != null) {

				if(tempIndex ==0){
					SnfFilesReader.addfieldsNotToInclude(csv.getHeader());
					SnfFilesReader.addOrgHeaderToFINALHEADER(csv.getHeader());
					TESTOBJ = new SnfFilesReader(snfFolderPath, PRODUCT);
					tempIndex++;

					csvAppender.appendLine(SnfFilesReader.FINALHEADER.toArray(new String[0]));
					writeUsingBufferedWriter(Arrays.toString(SnfFilesReader.FINALHEADER.toArray(new String[0])),snfFolderPath.getPath()+"/Output/"+PRODUCT_TYPE+" columns.txt");
					System.exit(0);
				}
				tempIndex++;
				if (tempIndex>50) System.exit(0);

				LinkedList<String> snfList = new LinkedList();
				for (String curr : TESTOBJ.ORGFKTOJOIN) {
					String fkValue = row.getField(curr);
					if (fkValue == null || fkValue.equals("NULL")|| fkValue.isEmpty()) {
						//// System.out.println("null for "+curr+" value"+fkValue);
						// TODO DO SOMETHING FOR THIS
						fkValue = "-1";

					}
					List<String> temp = getCkListForFk(fkValue, curr);
					if(temp==null){
						// System.out.println("null for fk  "+curr+" value "+fkValue);
						continue;
					}
					snfList.addAll(getCkListForFk(fkValue, curr));

				}
				List<String> orgRow = new LinkedList<>(row.getFields());

//				// System.out.println(Arrays.toString(snfList.toArray(new String[0])));
//				// System.out.println(Arrays.toString(orgRow.toArray(new String[0])));

				orgRow.addAll(snfList);
				csvAppender.appendLine(orgRow.toArray(new String[orgRow.size()]));

//				// System.out.println(" *** "+ Arrays.toString(orgRow.toArray(new String[orgRow.size()]))+"\n\n");

			}
//			csvWriter.write(outputCombinedPath, StandardCharsets.UTF_8, outputData);
		} catch (Exception e) {
			e.printStackTrace();
		}
		long endTime = System.nanoTime();
		long durationInNano = (endTime - startTime);
		// System.out.println(durationInNano);
	}


	//Here we add delimiter after each read and
	private static List<String> getCkListForFk(String fkValue, String currCkTable) {
		try {
			if (TESTOBJ.SNFJOINERMAPS.get(currCkTable)==null) throw new Exception(currCkTable);
			List<String> listForFk= (TESTOBJ.SNFJOINERMAPS.get(currCkTable).get(fkValue));
			return listForFk;
		} catch (Exception e) {
			//// System.out.println("Problem while joining snf and orignal csv getCkListForFk");
			e.printStackTrace();
			System.exit(0);
			return null;
		}
	}
	private static void writeUsingBufferedWriter(String data, String outputPath) {
		File esServiceOutputFile =  new File(outputPath);
		File esServiceOutputFolder =  new File(esServiceOutputFile.getParent());
		FileWriter fr = null;
		BufferedWriter br = null;
		String dataWithNewLine = data;
		if(!Files.exists(Paths.get(esServiceOutputFile.getParent()))) {
			boolean created = esServiceOutputFolder.mkdirs();
			if(created==false){
				// System.out.println("unable to create file at path "+outputPath);
				System.exit(0);

			}
		}
		try {
			fr = new FileWriter(esServiceOutputFile);
			br = new BufferedWriter(fr);

			br.write(dataWithNewLine);

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

}
