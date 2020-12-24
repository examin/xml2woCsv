package micro.examin.xml2woCsv.Leetcode;

import java.util.Arrays;

public class CountPath {
    public static void main(String[] args) {
        int m = 7, n = 3;
        int path[][] = {
                {0,0}
        };
        System.out.println(uniquePathsWithObstacles(path));
    }

    public static int uniquePathsWithObstacles(int[][] path) {
        int m = path.length;
        int n = path[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (path[i][j] == 1) {
                    path[i][j] = -1;
                }
            }
        }
        for (int i = 0; i < n; i++) {
            if (path[0][i] != -1) {
                path[0][i] = 1;
            }
        }
        for (int i = 0; i < m; i++) {
            if (path[i][0] != -1) {
                path[i][0] = 1;
            }
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (path[i][j] == -1) {
                    continue;
                } else if (path[i - 1][j] == -1) {
                    if (path[i][j - 1] == -1) {
                        path[i][j] = -1;
                    } else {
                        path[i][j] = path[i][j - 1];
                    }
                } else if (path[i][j - 1] == -1) {
                    if (path[i - 1][j] == -1) {
                        path[i][j] = -1;
                    } else {
                        path[i][j] = path[i - 1][j];
                    }
                } else
                    path[i][j] = Math.max(path[i - 1][j], path[i][j - 1]) + 1;
            }
        }
        for (int i = 0; i < m; i++) {
            System.out.println(Arrays.toString(path[i]));
        }
        return path[m - 1][n - 1];
    }
}
