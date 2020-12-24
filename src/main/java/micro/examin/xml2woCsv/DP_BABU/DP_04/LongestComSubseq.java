package micro.examin.xml2woCsv.DP_BABU.DP_04;

public class LongestComSubseq {
    static int count = 0;

    public static void main(String[] args) {
        String s1 = "bhupender";
        char[] s11 = s1.toCharArray();
        String s2 = "sitender";
        char[] s22 = s2.toCharArray();
        StringBuilder sb = new StringBuilder();
        int dp[][] = new int[s11.length][s22.length];
        System.out.println("LCS : " + lcs(s11, s1.length() - 1, s22, s2.length() - 1, sb, dp));
        System.out.println(sb.toString());
        System.out.println("count " + count);
    }

    private static int lcs(char[] s11, int index1, char[] s22, int index2, StringBuilder sb, int[][] dp) {
        if (index1 < 0 || index2 < 0) {
            count++;
            return 0;
        } if (dp[index1][index2] != 0) {
            return dp[index1][index2];
        } else if (s11[index1] == s22[index2]) {
            count++;
            sb.append(s11[index1]);
            return 1 + lcs(s11, index1 - 1, s22, index2 - 1, sb, dp);
        } else {
            count++;
            return Math.max(lcs(s11, index1, s22, index2 - 1, sb, dp), lcs(s11, index1 - 1, s22, index2, sb, dp));
        }
    }

}
