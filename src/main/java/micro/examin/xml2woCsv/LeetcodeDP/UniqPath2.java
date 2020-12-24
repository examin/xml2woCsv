package micro.examin.xml2woCsv.LeetcodeDP;

import java.util.Arrays;

public class UniqPath2 {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int n = obstacleGrid.length;
        int m = obstacleGrid[0].length;
        if (obstacleGrid[n - 1][m - 1] == 1) {
            return -1;
        }
        int[][] dp = new int[n][m];
        for(int i =0;i<n;i++){
            if(obstacleGrid[i][0]==0) {
                dp[i][0] = 1;
            }else break;
        }
        for(int i =0;i<m;i++){
            if(obstacleGrid[0][i]==0) {
                dp[0][i] = 1;
            }else break;
        }
        for(int i =1;i<obstacleGrid.length;i++){
            for(int j =1;j<obstacleGrid[0].length;j++){
                if(obstacleGrid[i][j]==0) {
                    dp[i][j] = dp[i][j-1]+dp[i-1][j];
                }
            }
        }

        for(int[] curr :dp){
            System.out.println(Arrays.toString(curr));
        }
        return dp[n - 1][m - 1];
    }

    public static void main(String[] args) {
        UniqPath2 obj = new UniqPath2();
        System.out.println(obj.uniquePathsWithObstacles(new int[][]{{1, 0}}));
    }
}
