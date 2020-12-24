package micro.examin.xml2woCsv.Leetcode;

import java.util.Arrays;
import java.util.HashSet;

public class RootenOrange {
    public static void main(String[] args) {
        int grid[][] = {{2, 1, 1}, {0, 1, 1}, {1, 0, 1}};
        for (int i = 0; i < grid.length; i++) {
            System.out.println(Arrays.toString(grid[i]));
        }

        System.out.println("---------------");
        System.out.println(orangesRotting(grid));
    }

    public static int orangesRotting(int[][] grid) {
        HashSet<int[]> wrottenOrangeSet = new HashSet<>();
        int unRottenCount = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                switch (grid[i][j]) {
                    case 0:
                        break;
                    case 1:
                        unRottenCount++;
                        break;
                    case 2:
                        wrottenOrangeSet.add(new int[]{i, j});
                        break;
                    default:
                        return -1;
                }
            }
        }
        int canAffect[][] = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
        int result = 0;
        boolean anyUpdates = true;
        while (unRottenCount > 0 && anyUpdates) {
            anyUpdates = false;
            result++;
            for (int[] curr : wrottenOrangeSet) {
                for (int[] affect : canAffect) {
                    int[] now = {curr[0] + affect[0], curr[1] + affect[1]};
                    if (grid.length > now[0] && grid[0].length > now[1] && now[0] >= 0 && now[1] >= 0 && grid[now[0]][now[1]] == 1) {
                        grid[now[0]][now[1]] = 2;
                        anyUpdates = true;
                        wrottenOrangeSet.add(new int[]{now[0], now[1]});
                        unRottenCount--;
                    }
                }
            }
        }
        return unRottenCount > 0 ? -1 : result;
    }

}
