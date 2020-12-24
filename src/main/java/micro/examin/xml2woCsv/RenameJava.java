package micro.examin.xml2woCsv;

import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

class RenameFileJava {

	public static void main(String[] args) throws IOException {
		File folder = new File("/home/gaurav/Downloads/AMAZON INTERVIEW/Tech Lead/CoderPro All Videos");
		HashMap<String, String> nameMap = resCsvGetNameMap();
		for (File curr : folder.listFiles()) {
			if(curr.isFile()&&curr.getName().contains(".mp4")) {
				//absolute path rename file
				String newName = nameMap.get(curr.getName());
				File newFile = new File(curr.getParent() + "/try/" + newName);
				try {
					FileUtils.copyFile(curr, newFile);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private static HashMap<String, String> resCsvGetNameMap() throws IOException {
		HashMap<String, String> map = new HashMap<>(100);
		File file = new File("/home/gaurav/Downloads/AMAZON INTERVIEW/Tech Lead/CoderPro All Videos/Coding Interview Practice Sessions.txt");
		CsvReader csvReader = new CsvReader();
		int start = 1;
		CsvContainer csv = csvReader.read(file, StandardCharsets.UTF_8);
		for (CsvRow row : csv.getRows()) {
			String newName  =  row.getField(0);
			map.put("lesson"+start+".mp4","0"+start+newName.replace(" ","_")+".mp4");
			++start;
		}
		return map;
	}
}