package micro.examin.xml2woCsv.AxsLogic_Temp;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class elasticStoredFromFile {
    public static void main(String[] args) throws IOException {
        String ip = "172.16.33.119";
        String stored = "/home/gaurav/Downloads/CALA_LAGGED";
        String scriptStarting = "curl -XPOST \"http://%s:9200/_scripts/%s\" -H 'Content-Type: application/json' -d'\n" +
                "{\n" +
                "  \"script\": {\n" +
                "    \"lang\": \"painless\",\n" +
                "    \"source\": \"%s\"\n" +
                "  }\n" +
                "}'";
        File storedFile = new File(stored);
        if(storedFile.exists()){
            BufferedReader br =  new BufferedReader(new FileReader(storedFile));
            String curr = br.readLine();
            while (curr!=null){
                String[] inputArr =  curr.split("\",\"");
                if(inputArr.length==2){
                    System.out.println(String.format(scriptStarting,ip,inputArr[0],inputArr[1].replace("\'\'","\'").replace("\'","\\\"")));
                }else {
                    System.out.println("ERROR HIA DEKH LE ");
                }
                curr = br.readLine();
            }

        }
    }
}
