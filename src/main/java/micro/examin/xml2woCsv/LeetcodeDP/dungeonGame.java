package micro.examin.xml2woCsv.LeetcodeDP;

import java.util.Arrays;

public class dungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int[][] dp = new int[dungeon.length+1][dungeon[0].length+1];
        for(int i =0;i<dungeon.length;i++){
            dp[0][i] = Integer.MIN_VALUE;
        }
        for(int i =0;i<dungeon[0].length;i++){
            dp[i][0] = Integer.MIN_VALUE;
        }
        for(int i = 1; i<=dungeon.length; i++){
            for(int j = 1; j<=dungeon[0].length; j++){
                if(dungeon[i-1][j-1]<0) {
                    dp[i][j] = dungeon[i - 1][j - 1] + Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        for(int[] curr : dp){
            System.out.println(Arrays.toString(curr));
        }
        return dp[dungeon.length][dungeon[0].length]<0?-1*dp[dungeon.length][dungeon[0].length]:0;
    }

    public static void main(String[] args) {
        dungeonGame obj = new dungeonGame();
        System.out.println(obj.calculateMinimumHP(new int[][]{{-2,-3,3},{-5,-10,1},{10,30,-5}}));
    }
}
