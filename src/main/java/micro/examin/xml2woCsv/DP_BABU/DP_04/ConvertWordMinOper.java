package micro.examin.xml2woCsv.DP_BABU.DP_04;


import java.util.Arrays;

public class ConvertWordMinOper {
    public static void main(String[] args) {
        String s1 = "Dant";
        String s2 = "Dentyio";
       // System.out.println(convertWord(s1, s2, 0, 0));
        System.out.println(bUConvertWord(s1, s2));
    }

    private static int bUConvertWord(String s1, String s2) {
        int[][] dp  =  new int[s1.length()][s2.length()];
        for(int i =0;i<s2.length();i++){
            dp[0][i] = i;
        }
        for(int i =0;i<s1.length();i++) {
            dp[i][0] = i;
            System.out.println(Arrays.toString(dp[i]));
        }
        for(int i =1;i<s1.length();i++){
            for(int j =1;j<s2.length();j++){
                if(s1.charAt(i)==s2.charAt(j)) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }else{
                    dp[i][j] =1+ Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1]));
                }
            }
        }
        System.out.println("========================");
        for(int i =0;i<s1.length();i++) {
            System.out.println(Arrays.toString(dp[i]));
        }
        return dp[s1.length()-1][s2.length()-1];

    }

    //todo repair required
    private static int convertWord(String s1, String s2, int index1, int index2) {
        if (s1.isEmpty()) {
            return s2.length();
        }
        if (s2.isEmpty()) {
            return s1.length();
        }
        if (index1 > s1.length() - 1 && index2 > s2.length() - 1) {
            return 0;
        }
        if (index1 > s1.length() - 1) {
            return s2.length() - index1;
        }
        if (index2 > s2.length() - 1) {
            return s2.length() - index1;
        }
        if (s1.charAt(index1) == s2.charAt(index2)) {
            if (s1.length() == index1 + 1 || s2.length() == index2 + 1) {
                return 1;
            }
            return convertWord(s1, s2, index1 + 1, index2 + 1);
        }
        int insert = convertWord(s1, s2, index1 + 1, index2);
        int delete = convertWord(s1, s2, index1, index2 + 1);
        int replace = convertWord(s1, s2, index1 + 1, index2 + 1);
        return 1 + Math.min(insert, Math.min(delete, replace));
    }
}
