package micro.examin.xml2woCsv.Leetcode;

public class All1Sqare {
    public static void main(String[] args) {
        char arr[][] = {{1}};
        System.out.println(maximalSquare(arr)*2);
    }

    public static int maximalSquare(char[][] matrix) {
        if(matrix.length<1||matrix[0].length<1){
            return 0;
        }
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        int max = 0;
        for (int i = 1; i <= matrix.length; i++) {
            for (int j = 1; j <= matrix[0].length; j++) {
                if (matrix[i - 1][j - 1] == 1) {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                    max = Math.max(dp[i][j], max);
                } else {
                    dp[i][j] = 0;
                }
            }
        }
        return max;
    }
}