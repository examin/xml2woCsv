package micro.examin.xml2woCsv.Leetcode;

public class CountIslands {
        public static int maxAreaOfIsland(int[][] grid) {
            int max = 0;
            int count = 1;
            int[] dp = new int[100000];
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if (grid[i][j] == 1) {
                        count = count + 1;
                        visitWholeIsland(grid, i, j, count);
                    }
                }
            }
            for (int i = 0; i < grid.length; i++) {
                for (int j = 0; j < grid[0].length; j++) {
                    if(grid[i][j] !=0) {
                        ++dp[grid[i][j]];
                        if (dp[grid[i][j]] > max) {
                            max = dp[grid[i][j]];
                        }
                    }
                }
            }
            return max;
        }

        public static void visitWholeIsland(int grid[][], int i, int j, int count) {
            if (i == 2 && j == 2) {
                System.out.println();
            }
            int chances[][] = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
            for (int curr[] : chances) {
                int x = i + curr[0];
                int y = j + curr[1];
                if (x >= 0 && y >= 0 && x < grid.length && y < grid[0].length) {
                    if (grid[x][y] == 1) {
                        grid[x][y] = count;
                        visitWholeIsland(grid, x, y, count);
                    }
                }
            }
        }

    public static void main(String[] args) {
        int[][] input = {{0, 0, 1, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 0, 1, 0, 0},
                {0, 1, 0, 0, 1, 1, 0, 0, 1, 1, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 0, 0},
                {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0}};
        System.out.println(maxAreaOfIsland(input));
    }
}
