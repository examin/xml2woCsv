package micro.examin.xml2woCsv.AxsLogic_Temp.CodeGen;

import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EsServiceCodeGen2 {
	public static void main(String[] args) throws IOException {
		String columnDefFolderPath = "/home/gaurav/Downloads/Axslogic Utils/mapping";
		String esServiceOutputPath = "/home/gaurav/Downloads/Axslogic Utils/Java Code";
		final File columnDefFolder = new File(columnDefFolderPath);
		readExcel(columnDefFolder, esServiceOutputPath);
	}

	private static void readExcel(File columnDefFolderPath, String esServiceOutputFolder) throws IOException {

		for (final File fileEntry : columnDefFolderPath.listFiles()) {
			if (fileEntry.isDirectory()) {
				System.out.println("directory found not using it :"+fileEntry.getName());
				continue;//is required do recusion to read internal folders also
			} else {
				if (fileEntry.isFile() && fileEntry.getName().endsWith(".csv")) {
					System.out.println(fileEntry.getName());
					String currCsvPath = fileEntry.getPath();
					String currFileName = fileEntry.getName();
					String product = currFileName.replaceAll("_.*", "");
					String productType = currFileName.replaceAll("^.*_", "").replace(".csv", "");
					String productSubType = (currFileName.contains("_D_")?"D":"M");
					File file = new File(currCsvPath);
					ReplaceUtils replaceUtils;
					CsvReader csvReader = new CsvReader();
					csvReader.setFieldSeparator('|');
					CsvContainer csv = csvReader.read(file, StandardCharsets.UTF_8);
					ArrayList<String> productColumnsNames = new ArrayList<String>();
					ArrayList<String> productColumnsTypes = new ArrayList<String>();
					ArrayList<String> productColumnsESTypes = new ArrayList<String>();
					for (CsvRow row : csv.getRows()) {
						//System.out.println("ROW: "+row);
						String name  ="";
						String type = "";
						try {
							name = row.getField(0);
							type = row.getField(1);
						}
						catch (Exception e){
							System.out.println();
						}
						if (name.isEmpty() && type.isEmpty()) continue;
						switch (type) {
							case "decimal":
							case "Decimal":
							case "DECIMAL":
							case "DOUBLE":
							case "Double":
								productColumnsTypes.add("double");
								productColumnsESTypes.add("searchDoubleFields");
								break;
							case "varchar":
							case "VARCHAR":
							case "String":
							case "STRING":
								productColumnsTypes.add("String");
								productColumnsESTypes.add("searchFields");
								break;
							case "int":
							case "Integer":
							case "INTEGER":
							case "smallint":
							case "INT":
							case "smallInt":
								productColumnsTypes.add("int");
								productColumnsESTypes.add("searchIntFields");
								break;
							case "date":
							case "Date":
							case "DATE":
								productColumnsTypes.add("Date");
								productColumnsESTypes.add("searchDateFields");
								break;
							default:
								throw new IOException(String.format("column type %s for %s in %s not handled", type, name, fileEntry.getAbsolutePath()));

						}
						productColumnsNames.add(name);
					}
					replaceUtils = new ReplaceUtils(product, productType, productSubType,productColumnsTypes, productColumnsNames, productColumnsESTypes);
					String config = JavaCodeUtils.config.replace("/*$PRODUCT+Type*/", replaceUtils.PRODUCTType)
							.replace("/*$PRODUCT+Typefull*/", replaceUtils.PRODUCTTypefull)
							.replace("/*$product+Typefull*/", replaceUtils.productTypefull)
							.replace("/*$product+Type*/", replaceUtils.productType)
							.replace("/*Type*/", replaceUtils.Type)
							.replace("/*typefull*/", replaceUtils.typefull)
							.replace("/*product*/", replaceUtils.product)
							.replace("/*$column names*/", replaceUtils.configFieldStr);
					String processor = JavaCodeUtils.processor.replace("/*$PRODUCT+Type*/", replaceUtils.PRODUCTType)
							.replace("/*$PRODUCT+Typefull*/", replaceUtils.PRODUCTTypefull)
							.replace("/*$product+Typefull*/", replaceUtils.productTypefull)
							.replace("/*$product+Type*/", replaceUtils.productType);
					String writer = JavaCodeUtils.writer.replace("/*$PRODUCT+Type*/", replaceUtils.PRODUCTType)
							.replace("/*$PRODUCT+Typefull*/", replaceUtils.PRODUCTTypefull)
							.replace("/*$product+Typefull*/", replaceUtils.productTypefull)
							.replace("/*$product+Type*/", replaceUtils.productType)
							.replace("/*Fields*/", replaceUtils.writerFieldTypeStr)
							.replace("/*Properties*/", replaceUtils.writerFieldPropertyStr);
//					System.out.println("\n\n\\n"+writer+"\n\n\n\n");
					String dto = JavaCodeUtils.dto.replace("/*$PRODUCT+Type*/", replaceUtils.PRODUCTType)
							.replace("/*$PRODUCT+Typefull*/", replaceUtils.PRODUCTTypefull)
							.replace("/*$product+Typefull*/", replaceUtils.productTypefull)
							.replace("/*$getterSetterString*/", replaceUtils.getterSetterString)
							.replace("/*$dataString*/", replaceUtils.dataString);
					replaceUtils.print();
//					System.out.println(fileEntry.getName()+" ... \n"+Arrays.toString(productColumnsNames.toArray()));
//					System.out.println(Arrays.toString(productColumnsTypes.toArray()));


					writeUsingBufferedWriter(config, esServiceOutputFolder+"/jobs/config/"+replaceUtils.PRODUCTTypefull+"Config.java");
					writeUsingBufferedWriter(processor, esServiceOutputFolder+"/jobs/processor/"+replaceUtils.PRODUCTTypefull+"DocProcessor.java");
					writeUsingBufferedWriter(writer, esServiceOutputFolder+"/jobs/writer/"+replaceUtils.PRODUCTTypefull+"Writer.java");
					writeUsingBufferedWriter(dto,esServiceOutputFolder+"/dto/"+replaceUtils.PRODUCTTypefull+".java");
				}
//				else System.out.println("non csv file "+fileEntry.getName());
			}
		}
	}

	private static void writeUsingBufferedWriter(String config, String esServiceOutputPath) {
		File esServiceOutputFile =  new File(esServiceOutputPath);
		File esServiceOutputFolder =  new File(esServiceOutputFile.getParent());
		FileWriter fr = null;
		BufferedWriter br = null;
		String dataWithNewLine = config;
		if(!Files.exists(Paths.get(esServiceOutputFile.getParent()))) {
			boolean created = esServiceOutputFolder.mkdirs();
			if(created==false){
				System.out.println("unable to create file at path "+esServiceOutputPath);
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
