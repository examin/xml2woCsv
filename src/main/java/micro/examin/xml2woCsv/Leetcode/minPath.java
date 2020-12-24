package micro.examin.xml2woCsv.Leetcode;

import java.util.Arrays;

public class minPath {
    public static void main(String[] args) {
        int[][] grid = {
                {1, 3, 1},
                {1, 5, 1},
                {4, 2, 1}
        };
        int[][] dp = new int[grid.length][grid[0].length];
        System.out.println(minPathSum(grid, 0, 0, dp));
    }

    private static int minPathSum(int[][] grid, int i, int j, int[][] dp) {
        int row = grid.length;
        int col = grid[0].length;
        dp[0][0] = grid[0][0];
        for (int x = 1; x < row; x++) {
            dp[x][0] = grid[x][0]+dp[x-1][0];
        }
        for (int x = 1; x < col; x++) {
            dp[0][x] = grid[0][x]+dp[0][x-1];
        }
        for (int x = 1; x < row; x++) {
            for (int y = 1; y < col; y++) {
                dp[x][y] = Math.min(dp[x - 1][y], dp[x][y - 1]) + grid[x][y];
            }
        }
        for (int x = 0; x < grid.length; x++) {
            System.out.println(Arrays.toString(dp[x]));
        }
        System.out.println("s---------------");
        for (int x = 0; x < grid.length; x++) {
            System.out.println(Arrays.toString(dp[x]));
        }
        return dp[row - 1][col - 1];
    }

    public static boolean isValidPos(int x, int y, int grid[][]) {
        if (x < grid.length && y < grid[0].length) {
            if (x >= 0 && y >= 0) {
                return true;
            }
        }
        return false;
    }
}
