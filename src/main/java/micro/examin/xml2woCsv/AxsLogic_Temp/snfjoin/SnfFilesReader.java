package micro.examin.xml2woCsv.AxsLogic_Temp.snfjoin;

import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class SnfFilesReader {
	public HashMap<String, HashMap<String, List<String>>> SNFJOINERMAPS;
	public TreeMap<String, List<String>> SNFHeaderMAPS = new TreeMap<>();
	public String PRODUCT ;
	public ArrayList<String> ORGFKTOJOIN = new ArrayList<>();
	public static HashSet<String> FIELDs_NOT_TO_INCLUDE = new HashSet<>();
	public static ArrayList<String> FINALHEADER = new ArrayList<>();

	public SnfFilesReader(File snowFlakePath, String product) {
		SNFJOINERMAPS = new HashMap<>();
		PRODUCT = product;
//		fieldsNotToInclude(fieldsNotToInclude);
		readSnowFlakeFolder(snowFlakePath);
		populateFinalHeaderUsingSnfHeaderMap();
	}

	private void populateFinalHeaderUsingSnfHeaderMap() {
		for(Map.Entry<String, List<String>> entry : SNFHeaderMAPS.entrySet()) {
			String key = processString(entry.getKey());
			List value = entry.getValue();

			addCurrSnfTableColumnsToFinalHeader(value);
			ORGFKTOJOIN.add(key.replace("_CK","_FK"));

			System.out.println(value);
		}
	}

	public static void addfieldsNotToInclude(List<String> fieldsNotToInclude) {
		for(String curr : fieldsNotToInclude) {
			curr = processString(curr);
					FIELDs_NOT_TO_INCLUDE.add(curr);
		}
	}

	public void readSnowFlakeFolder(File folder) {

		for (final File fileEntry : folder.listFiles()) {

			HashMap<String, List<String>> currTableMap = new HashMap();
			List<String> headerList =null;
			if (fileEntry.isFile()) {
				String orgFileName = fileEntry.getName();
				String requiredName = orgFileName
						.replace("T_DIM_SNF_", "")
						.replace(".csv", "")
						.replace('_'+PRODUCT,"_FK");
				String[] arr = requiredName.split("_");
				if (arr.length > 0 &&orgFileName.matches(".*csv$")) {
//					String product = arr[2];
//					String ckName = arr[1] + "_" + arr[0];

					if (orgFileName.contains(".csv")) {
						CsvReader csvReader = new CsvReader();
						csvReader.setContainsHeader(true);
						csvReader.setFieldSeparator('|');

						try {
							CsvContainer csv = csvReader.read(fileEntry, StandardCharsets.UTF_8);
							try {
								headerList = csv.getHeader();
							}catch (Exception e){
								System.out.printf("Failed");
							}

							addCurrSnfTableColumnsToSnfHashMap(csv.getHeader());
//							System.out.println(fileEntry.getName());
							for (CsvRow row : csv.getRows()) {

								List<String> rowValuesList = getProcessedRowValues(row,headerList);
								currTableMap.put(processString(rowValuesList.get(0)), rowValuesList);

							}
							//todo delete this code when null_fk will be resolved
							includeValueForNull(currTableMap,csv.getHeader());

							SNFJOINERMAPS.put(processString(csv.getHeader().get(0)).replace("_CK","_FK"), currTableMap);
						} catch (IOException e) {
							e.printStackTrace();
							System.exit(0);
						}

					} else {
						System.out.print("..Error Given table comtain non csv files also do check");
					}
				} else {
					System.out.println("..File names are not following right pattern");
					System.exit(0);
				}

			} else {
				System.out.println("..Folder for snow flake table csv's also contains subfolder do check.."+fileEntry.getPath());
			}
		}
	}

	private void addCurrSnfTableColumnsToSnfHashMap(List<String> header) {
		SNFHeaderMAPS.put(processString(processString(header.get(0))),header);
	}

	private void addCurrSnfTableColumnsToFinalHeader( List<String> headerList) {
		for(String curr : headerList){

			curr = processString(curr);
			if(!FIELDs_NOT_TO_INCLUDE.contains(curr)){
				FINALHEADER.add(curr);
			}
		}

	}

	private void includeValueForNull(HashMap<String, List<String>> currTableMap, List<String> header) {

		ArrayList<String> list = new ArrayList<>();
		for(String curr : header){
			curr = processString(curr);
			if(!FIELDs_NOT_TO_INCLUDE.contains(curr)){
				list.add("-1");
			}
		}

		currTableMap.put("-1",list);
	}

	private List<String> getProcessedRowValues(CsvRow row, List<String> headerList) {
		Map<String, String> rowMap = row.getFieldMap();
		List<String> rowValuesList = new ArrayList<>();
		for(String curr : headerList){

			if(!FIELDs_NOT_TO_INCLUDE.contains(curr)){
				rowValuesList.add(rowMap.get(curr));
			}
		}
		return rowValuesList;
	}
	public static void addOrgHeaderToFINALHEADER(List<String> header){
		for(String curr : header) {
			curr = processString(curr);
			FINALHEADER.add(processString(curr));
		}
	}
	public ArrayList<String> getFINALHEADER(){
		return FINALHEADER;
	}
	private static String processString(String curr) {
		if (curr.startsWith("\uFEFF")) {
			curr = curr.substring(1);
		}
		curr = curr.replace("\"","").replace(",","").replace("'","").replace("\"","").trim();
		return curr;
	}
}
