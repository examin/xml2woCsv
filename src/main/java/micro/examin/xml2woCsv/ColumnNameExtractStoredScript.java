package micro.examin.xml2woCsv;

import de.siegmar.fastcsv.reader.CsvContainer;
import de.siegmar.fastcsv.reader.CsvReader;
import de.siegmar.fastcsv.reader.CsvRow;
import micro.examin.xml2woCsv.AxsLogic_Temp.commonUtils.Utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColumnNameExtractStoredScript {
	public static void main(String[] args) throws IOException {
		String path = "/home/gaurav/Desktop/Upload.txt";
		BufferedWriter writer = new BufferedWriter(new FileWriter("/home/gaurav/Desktop/UploadSol.txt"));
		File file = new File(path);
		CsvReader csvReader = new CsvReader();
		CsvContainer csv = csvReader.read(file, StandardCharsets.UTF_8);
		String regex = "(?<=doc\\[.)\\w+";
		Pattern pattern = Pattern.compile(regex);
		HashSet<String> set = new HashSet<>();
		for (CsvRow row : csv.getRows()) {
			String str = "";
			if (row.getFields().get(0).contains(" | E")) {
				str = (Utils.decrypt(row.getFields().get(0).replace(" | E", "")));
				writer.write(str+ "\n");

			} else {
				str = (row.getFields().get(0).replace(" | D", ""));
				writer.write(str+ "\n");
			}
			Matcher matcher = pattern.matcher(str);
			while (matcher.find()) {
				set.add(matcher.group(0));
			}
		}
		writer.close();
	}
}
