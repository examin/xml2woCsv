package micro.examin.xml2woCsv.AxsLogic_Temp.snfjoin;

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
import java.util.*;

public class SnowFlakeCSVJoiner3 {
	private static String PRODUCT;
	private static String PRODUCT_TYPE;
	private static SnfFilesReader TESTOBJ;
	private static ArrayList<String> HEADER;

	public static void main(String[] args) {

//		String productDataPath = "/home/gaurav/Music/Axslogic_SNF_WORK_Utils/CloudLocal25M";
//		File  outputfilesCombinedPath = new File("/home/gaurav/Music/Axslogic_SNF_WORK_Utils/CloudLocalCombined25M");
//		File  allSnfFolderPath = new File("/home/gaurav/Music/Axslogic_SNF_WORK_Utils/snfcloud25M");

//		String productDataPath = "/home/gaurav/Music/Axslogic_SNF_WORK_Utils/CALC LOCAL";
//		File  outputfilesCombinedPath = new File("/home/gaurav/Music/Axslogic_SNF_WORK_Utils/CALC LOCAL COMBINED");
//		File  allSnfFolderPath = new File("/home/gaurav/Music/Axslogic_SNF_WORK_Utils/CALC SNF");

//CLOUD
//		String productDataPath = "/data/ES/ES_Data_File_Maker/FILE";
//		File  outputfilesCombinedPath = new File("/data/ES/ES_Data_File_Maker/OUTPUT_COMBINED");
//		File  allSnfFolderPath = new File("/data/ES/ES_Data_File_Maker/SNF");

		String path  = args[0];
		File productDataFolder = new File(path);

		System.out.println(path);
		File outputfilesCombinedPath = new File(productDataFolder.getParent()+"/OUTPUT_COMBINED");
		File allSnfFolderPath = new File(productDataFolder.getParent()+"/SNF");

		for (File currDataProductFile : productDataFolder.listFiles()) {

			long startTime = System.nanoTime();
			if (currDataProductFile.isDirectory()) {

				PRODUCT = currDataProductFile.getName();

				for (File subProductData : currDataProductFile.listFiles()) {
					PRODUCT_TYPE = subProductData.getName();
					for (File dataFile : subProductData.listFiles()) {
						String dataFileName = dataFile.getName();
						File inputCsvPath = dataFile;
						File outputCombinedPath = new File(outputfilesCombinedPath.getPath() +"/"+ PRODUCT + "/" + PRODUCT_TYPE +"/"+dataFileName+"_Combined");
						File snfFolderPath = new File(allSnfFolderPath.getPath()+"/"+PRODUCT);

						CsvWriter csvWriter = new CsvWriter();
						CsvReader csvReader = new CsvReader();


						csvWriter.setFieldSeparator('|');
						csvReader.setFieldSeparator('|');

						csvWriter.setAlwaysDelimitText(true);
						csvReader.setContainsHeader(true);

						//Collection<String[]> outputData = new ArrayList<>();

						//outputData.add(getFinalSnfCsvHeader());
						if(!Files.exists(Paths.get(outputfilesCombinedPath.getPath() +"/"+ PRODUCT + "/" + PRODUCT_TYPE))) {
							boolean created = (new File(outputfilesCombinedPath.getPath() +"/"+ PRODUCT + "/" + PRODUCT_TYPE)).mkdirs();
							if(created==false){
								System.out.println("unable to create file at path "+outputCombinedPath);
								System.exit(0);

							}
						}

						try (CsvAppender csvAppender = csvWriter.append(outputCombinedPath, StandardCharsets.UTF_8)) {
							CsvParser csv = csvReader.parse(inputCsvPath, StandardCharsets.UTF_8);
							CsvRow row;

							int tempIndex = 0;
							while ((row = csv.nextRow()) != null) {

								if (tempIndex == 0) {
									SnfFilesReader.addfieldsNotToInclude(csv.getHeader());
									SnfFilesReader.addOrgHeaderToFINALHEADER(csv.getHeader());
									TESTOBJ = new SnfFilesReader(snfFolderPath, PRODUCT);
									tempIndex++;

									csvAppender.appendLine(SnfFilesReader.FINALHEADER.toArray(new String[0]));
									writeUsingBufferedWriter(Arrays.toString(SnfFilesReader.FINALHEADER.toArray(new String[0])), snfFolderPath.getPath() + "/Output/" + PRODUCT_TYPE + " columns.txt");
								}
//								tempIndex++;
//								if (tempIndex > 15) System.exit(0);

								LinkedList<String> snfList = new LinkedList();
								for (String curr : TESTOBJ.ORGFKTOJOIN) {
									String fkValue = row.getField(curr);
									if (fkValue == null || fkValue.equals("NULL") || fkValue.isEmpty()) {
										//System.out.println("null for "+curr+" value"+fkValue);
										// TODO DO SOMETHING FOR THIS
										fkValue = "-1";

									}
									List<String> temp = getCkListForFk(fkValue, curr);
									if (temp == null) {
										System.out.println("null for fk  " + curr + " value " + fkValue+" in "+ dataFile.getPath());
										continue;
									}
									snfList.addAll(getCkListForFk(fkValue, curr));
								}
								List<String> orgRow = new LinkedList<>(row.getFields());
//								System.out.println(Arrays.toString(snfList.toArray(new String[0])));
//								System.out.println(Arrays.toString(orgRow.toArray(new String[0])));
								orgRow.addAll(snfList);
								csvAppender.appendLine(orgRow.toArray(new String[orgRow.size()]));
//								System.out.println(" *** "+ Arrays.toString(orgRow.toArray(new String[orgRow.size()]))+"\n\n");
							}
//							csvWriter.write(outputCombinedPath, StandardCharsets.UTF_8, outputData);
						} catch (Exception e) {
							e.printStackTrace();
						}
						long endTime = System.nanoTime();
						long durationInNano = (endTime - startTime);
						System.out.println(durationInNano);
						TESTOBJ = null;
						SnfFilesReader.FIELDs_NOT_TO_INCLUDE = new HashSet<>();
						SnfFilesReader.FINALHEADER = new ArrayList<>();
					}
				}
			}
		}
	}

	//Here we add delimiter after each read and
	private static List<String> getCkListForFk(String fkValue, String currCkTable) {
		try {
			if (TESTOBJ.SNFJOINERMAPS.get(currCkTable)==null) throw new Exception(currCkTable);
			List<String> listForFk= (TESTOBJ.SNFJOINERMAPS.get(currCkTable).get(fkValue));
			if(listForFk==null){

				listForFk = (TESTOBJ.SNFJOINERMAPS.get(currCkTable).get("-1"));

			}
			return listForFk;
		} catch (Exception e) {
			//System.out.println("Problem while joining snf and orignal csv getCkListForFk");
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
				System.out.println("unable to create file at path "+outputPath);
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
