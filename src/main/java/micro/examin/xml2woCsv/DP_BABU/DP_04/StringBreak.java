package micro.examin.xml2woCsv.DP_BABU.DP_04;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class StringBreak {
    public static void main(String[] args) {
        String str = "iammanisha";
        HashSet<String> dic = new HashSet(Arrays.asList("i", "am","amma", "manish","nisha"));
        ArrayList<String> result = new ArrayList<>();

        System.out.println(wordBreak(str, dic,0,result));
        System.out.println(result.toString());
    }

    public static boolean wordBreak(String str, HashSet<String> dic, int start, ArrayList<String> result) {
        if (start==str.length()){
            return true;
        }
        if(start+2>str.length()){
            return false;
        }
        for (int i = start+1; i <=str.length(); i++) {
            String curr = str.substring(start,i);
            if(dic.contains(curr)){
                result.add(curr);
                if(wordBreak(str,dic,i, result)){
                    return true;
                }
            }
        }
        return false;
    }
}
