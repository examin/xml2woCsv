package micro.examin.xml2woCsv.AxsLogic_Temp.SnfJoiner12Feb;

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
import java.nio.file.LinkOption;
import java.nio.file.Paths;
import java.util.*;

public class SnowFlakeCSVJoiner3 {
    private static String PRODUCT;
    private static String PRODUCT_TYPE;
    private static SnfFilesReader TESTOBJ;
    private static ArrayList<String> HEADER;

    public SnowFlakeCSVJoiner3() {
    }

    public static void main(String[] args) {
        String currFolderPath = "/home/gaurav/Pictures/SA02_DATA_COMBINE_PROCESS";
        System.out.println(currFolderPath);
        String productDataPath = currFolderPath+"/FILE";
        File outputfilesCombinedPath = new File(currFolderPath+"/OUTPUT_COMBINED");
        File allSnfFolderPath = new File(currFolderPath+"/SNF");
        File productDataFolder = new File(productDataPath);
        File[] var5 = productDataFolder.listFiles();
        int var6 = var5.length;

        for(int var7 = 0; var7 < var6; ++var7) {
            File currDataProductFile = var5[var7];
            long startTime = System.nanoTime();
            if (currDataProductFile.isDirectory()) {
                PRODUCT = currDataProductFile.getName();
                File[] var11 = currDataProductFile.listFiles();
                int var12 = var11.length;

                for(int var13 = 0; var13 < var12; ++var13) {
                    File subProductData = var11[var13];
                    PRODUCT_TYPE = subProductData.getName();
                    File[] var15 = subProductData.listFiles();
                    int var16 = var15.length;

                    for(int var17 = 0; var17 < var16; ++var17) {
                        File dataFile = var15[var17];
                        String dataFileName = dataFile.getName();
                        File inputCsvPath = dataFile;
                        File outputCombinedPath = new File(outputfilesCombinedPath.getPath() + "/" + PRODUCT + "/" + PRODUCT_TYPE + "/" + dataFileName + "_Combined");
                        File snfFolderPath = new File(allSnfFolderPath.getPath() + "/" + PRODUCT);
                        CsvWriter csvWriter = new CsvWriter();
                        CsvReader csvReader = new CsvReader();
                        csvWriter.setFieldSeparator('|');
                        csvReader.setFieldSeparator('|');
                        csvWriter.setAlwaysDelimitText(true);
                        csvReader.setContainsHeader(true);
                        if (!Files.exists(Paths.get(outputfilesCombinedPath.getPath() + "/" + PRODUCT + "/" + PRODUCT_TYPE), new LinkOption[0])) {
                            boolean created = (new File(outputfilesCombinedPath.getPath() + "/" + PRODUCT + "/" + PRODUCT_TYPE)).mkdirs();
                            if (!created) {
                                System.out.println("unable to create file at path " + outputCombinedPath);
                                System.exit(0);
                            }
                        }

                        try {
                            CsvAppender csvAppender = csvWriter.append(outputCombinedPath, StandardCharsets.UTF_8);
                            Throwable var26 = null;

                            try {
                                CsvParser csv = csvReader.parse(inputCsvPath, StandardCharsets.UTF_8);
                                int tempIndex = 0;

                                CsvRow row;
                                while((row = csv.nextRow()) != null) {
                                    if (tempIndex == 0) {
                                        SnfFilesReader.addfieldsNotToInclude(csv.getHeader());
                                        SnfFilesReader.addOrgHeaderToFINALHEADER(csv.getHeader());
                                        TESTOBJ = new SnfFilesReader(snfFolderPath, PRODUCT);
                                        ++tempIndex;
                                        csvAppender.appendLine((String[])SnfFilesReader.FINALHEADER.toArray(new String[0]));
                                        writeUsingBufferedWriter(Arrays.toString(SnfFilesReader.FINALHEADER.toArray(new String[0])), snfFolderPath.getPath() + "/Output/" + PRODUCT_TYPE + " columns.txt");
                                    }

                                    LinkedList<String> snfList = new LinkedList();
                                    Iterator var31 = TESTOBJ.ORGFKTOJOIN.iterator();

                                    while(var31.hasNext()) {
                                        String curr = (String)var31.next();
                                        String fkValue = row.getField(curr);
                                        if (fkValue == null || fkValue.equals("NULL") || fkValue.isEmpty()) {
                                            fkValue = "-1";
                                        }

                                        List<String> temp = getCkListForFk(fkValue, curr);
                                        if (temp == null) {
                                            System.out.println("null for fk  " + curr + " value " + fkValue + " in " + dataFile.getPath());
                                        } else {
                                            snfList.addAll(getCkListForFk(fkValue, curr));
                                        }
                                    }

                                    List<String> orgRow = new LinkedList(row.getFields());
                                    orgRow.addAll(snfList);
                                    csvAppender.appendLine((String[])orgRow.toArray(new String[orgRow.size()]));
                                }
                            } catch (Throwable var43) {
                                var26 = var43;
                                throw var43;
                            } finally {
                                if (csvAppender != null) {
                                    if (var26 != null) {
                                        try {
                                            csvAppender.close();
                                        } catch (Throwable var42) {
                                            var26.addSuppressed(var42);
                                        }
                                    } else {
                                        csvAppender.close();
                                    }
                                }

                            }
                        } catch (Exception var45) {
                            var45.printStackTrace();
                        }

                        long endTime = System.nanoTime();
                        long durationInNano = endTime - startTime;
                        System.out.println(durationInNano);
                        TESTOBJ = null;
                        SnfFilesReader.FIELDs_NOT_TO_INCLUDE = new HashSet();
                        SnfFilesReader.FINALHEADER = new ArrayList();
                    }
                }
            }
        }

    }

    private static List<String> getCkListForFk(String fkValue, String currCkTable) {
        try {
            if (TESTOBJ.SNFJOINERMAPS.get(currCkTable) == null) {
                throw new Exception(currCkTable);
            } else {
                List<String> listForFk = (List)((HashMap)TESTOBJ.SNFJOINERMAPS.get(currCkTable)).get(fkValue);
                if (listForFk == null) {
                    listForFk = (List)((HashMap)TESTOBJ.SNFJOINERMAPS.get(currCkTable)).get("-1");
                }

                return listForFk;
            }
        } catch (Exception var3) {
            var3.printStackTrace();
            System.exit(0);
            return null;
        }
    }

    private static void writeUsingBufferedWriter(String data, String outputPath) {
        File esServiceOutputFile = new File(outputPath);
        File esServiceOutputFolder = new File(esServiceOutputFile.getParent());
        FileWriter fr = null;
        BufferedWriter br = null;
        String dataWithNewLine = data;
        if (!Files.exists(Paths.get(esServiceOutputFile.getParent()), new LinkOption[0])) {
            boolean created = esServiceOutputFolder.mkdirs();
            if (!created) {
                System.out.println("unable to create file at path " + outputPath);
                System.exit(0);
            }
        }

        try {
            fr = new FileWriter(esServiceOutputFile);
            br = new BufferedWriter(fr);
            br.write(dataWithNewLine);
        } catch (IOException var16) {
            var16.printStackTrace();
        } finally {
            try {
                br.close();
                fr.close();
            } catch (IOException var15) {
                var15.printStackTrace();
            }

        }

    }
}
