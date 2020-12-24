package micro.examin.xml2woCsv.Leetcode;

public class MinFallingPathSum {
    public static void main(String[] args) {
        int[][] grid = {{1,2,3},{4,5,6},{7,8,9}};
        int[] options = {0, -1, 1};
        int[][] dp = new int[grid.length][grid[0].length];
        if (grid.length == 1) {
            System.out.println(grid[0][0]);
        } else {
            int bestStart = Integer.MAX_VALUE;
            for (int i = 0; i < grid[0].length; i++) {
                bestStart = Math.min(grid[0][i] + minFallingPathSum(grid, 1, i, options,dp), bestStart);
            }
            System.out.println(bestStart);
        }
    }

    private static  int minFallingPathSum(int[][] grid, int row, int lastColumn, int[] options,int[][]dp) {

        int best = Integer.MAX_VALUE;
        if(row>=grid.length){
            return 0;
        }
        if(dp[row][lastColumn]!=0){
            return dp[row][lastColumn]=0;
        }
        for (int columnOption : options) {
            if (isValidGridIndex(row, lastColumn + columnOption, grid)) {
                best = Math.min(grid[row][lastColumn + columnOption] + minFallingPathSum(grid, row + 1, lastColumn + columnOption, options,dp), best);
            }
        }
        dp[row][lastColumn] = best;
        return best;
    }

    public static  boolean isValidGridIndex(int row, int column, int[][] grid) {
        if (row >= 0 && row < grid.length && column < grid[0].length && column >= 0) {
            return true;
        }
        return false;
    }
}
