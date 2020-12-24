package micro.examin.xml2woCsv.AxsLogic_Temp.SnfJoiner12Feb;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.Map.Entry;

public class SnfFilesReader {
    public HashMap<String, HashMap<String, List<String>>> SNFJOINERMAPS = new HashMap();
    public TreeMap<String, List<String>> SNFHeaderMAPS = new TreeMap();
    public String PRODUCT;
    public ArrayList<String> ORGFKTOJOIN = new ArrayList();
    public static HashSet<String> FIELDs_NOT_TO_INCLUDE = new HashSet();
    public static ArrayList<String> FINALHEADER = new ArrayList();

    public SnfFilesReader(File snowFlakePath, String product) {
        this.PRODUCT = product;
        this.readSnowFlakeFolder(snowFlakePath);
        this.populateFinalHeaderUsingSnfHeaderMap();
    }

    private void populateFinalHeaderUsingSnfHeaderMap() {
        Iterator var1 = this.SNFHeaderMAPS.entrySet().iterator();

        while(var1.hasNext()) {
            Entry<String, List<String>> entry = (Entry)var1.next();
            String key = processString((String)entry.getKey());
            List value = (List)entry.getValue();
            this.addCurrSnfTableColumnsToFinalHeader(value);
            this.ORGFKTOJOIN.add(key.replace("_CK", "_FK"));
            System.out.println(value);
        }

    }

    public static void addfieldsNotToInclude(List<String> fieldsNotToInclude) {
        Iterator var1 = fieldsNotToInclude.iterator();

        while(var1.hasNext()) {
            String curr = (String)var1.next();
            curr = processString(curr);
            if (!curr.equals("ACTV_FLAG")) {
                FIELDs_NOT_TO_INCLUDE.add(curr);
            }
        }

    }

    public void readSnowFlakeFolder(File folder) {
        File[] var2 = folder.listFiles();
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            File fileEntry = var2[var4];
            HashMap<String, List<String>> currTableMap = new HashMap();
            List<String> headerList = null;
            if (!fileEntry.isFile()) {
                System.out.println("..Folder for snow flake table csv's also contains subfolder do check.." + fileEntry.getPath());
            } else {
                String orgFileName = fileEntry.getName();
                String requiredName = orgFileName.replace("T_DIM_SNF_", "").replace(".csv", "").replace('_' + this.PRODUCT, "_FK");
                String[] arr = requiredName.split("_");
                if (arr.length > 0 && orgFileName.matches(".*csv$")) {
                    if (orgFileName.contains(".csv")) {
                        CsvReader csvReader = new CsvReader();
                        csvReader.setContainsHeader(true);
                        csvReader.setFieldSeparator('|');

                        try {
                            CsvContainer csv = csvReader.read(fileEntry, StandardCharsets.UTF_8);

                            try {
                                headerList = csv.getHeader();
                            } catch (Exception var16) {
                                System.out.printf("Failed:"+fileEntry);
                            }
                            this.addCurrSnfTableColumnsToSnfHashMap(csv.getHeader());
                            Iterator var13 = csv.getRows().iterator();

                            while(var13.hasNext()) {
                                CsvRow row = (CsvRow)var13.next();
                                List<String> rowValuesList = this.getProcessedRowValues(row, headerList);
                                currTableMap.put(processString((String)rowValuesList.get(0)), rowValuesList);
                            }

                            this.includeValueForNull(currTableMap, csv.getHeader());
                            this.SNFJOINERMAPS.put(processString((String)csv.getHeader().get(0)).replace("_CK", "_FK"), currTableMap);
                        } catch (IOException var17) {
                            var17.printStackTrace();
                            System.exit(0);
                        }
                    } else {
                        System.out.print("..Error Given table comtain non csv files also do check");
                    }
                } else {
                    System.out.println("..File names are not following right pattern");
                    System.exit(0);
                }
            }
        }

    }

    private void addCurrSnfTableColumnsToSnfHashMap(List<String> header) {
        this.SNFHeaderMAPS.put(processString(processString((String)header.get(0))), header);
    }

    private void addCurrSnfTableColumnsToFinalHeader(List<String> headerList) {
        Iterator var2 = headerList.iterator();

        while(var2.hasNext()) {
            String curr = (String)var2.next();
            curr = processString(curr);
            if (!FIELDs_NOT_TO_INCLUDE.contains(curr)) {
                FINALHEADER.add(curr);
            }
        }

    }

    private void includeValueForNull(HashMap<String, List<String>> currTableMap, List<String> header) {
        ArrayList<String> list = new ArrayList();
        Iterator var4 = header.iterator();

        while(var4.hasNext()) {
            String curr = (String)var4.next();
            curr = processString(curr);
            if (!FIELDs_NOT_TO_INCLUDE.contains(curr)) {
                list.add("-1");
            }
        }

        currTableMap.put("-1", list);
    }

    private List<String> getProcessedRowValues(CsvRow row, List<String> headerList) {
        Map<String, String> rowMap = row.getFieldMap();
        List<String> rowValuesList = new ArrayList();
        Iterator var5 = headerList.iterator();

        while(var5.hasNext()) {
            String curr = (String)var5.next();
            if (!FIELDs_NOT_TO_INCLUDE.contains(curr)) {
                rowValuesList.add(rowMap.get(curr));
            }
        }

        return rowValuesList;
    }

    public static void addOrgHeaderToFINALHEADER(List<String> header) {
        Iterator var1 = header.iterator();

        while(var1.hasNext()) {
            String curr = (String)var1.next();
            curr = processString(curr);
            FINALHEADER.add(processString(curr));
        }

    }

    public ArrayList<String> getFINALHEADER() {
        return FINALHEADER;
    }

    private static String processString(String curr) {
        if (curr.startsWith("\ufeff")) {
            curr = curr.substring(1);
        }

        curr = curr.replace("\"", "").replace(",", "").replace("'", "").replace("\"", "").trim();
        return curr;
    }
}
