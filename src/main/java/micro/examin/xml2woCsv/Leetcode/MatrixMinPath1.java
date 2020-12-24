package micro.examin.xml2woCsv.Leetcode;

public class MatrixMinPath1 {
    public static void main(String[] args) {
        int[][] arr = {
                {1, 3, 1},
                {1, 5, 1}
        };
        System.out.println(minPathLR(arr, 0, 0));
    }

    private static int minPathLR(int[][] arr, int i, int j) {
        if (validGridIndex(arr, i, j)) {
            return Integer.MAX_VALUE;
        }
        if (i == arr.length - 1 && j == arr[0].length) {
            return arr[arr.length][arr[0].length];
        } else {
            boolean anyChange = false;
            int min = Integer.MAX_VALUE;
            if (validGridIndex(arr, i + 1, j)) {
                min = Math.min(minPathLR(arr, i + 1, j), min);
                anyChange = true;
            }
            if (validGridIndex(arr, i, j + 1)) {
                min = Math.min(minPathLR(arr, i + 1, j), min);
                anyChange = true;
            }
            if (anyChange) {
                return arr[i][j]+min;
            } else return arr[i][j];

        }
    }

    private static boolean validGridIndex(int[][] arr, int i, int j) {
        if (i < arr.length && j < arr[0].length) {
            return true;
        } else return false;
    }
}
