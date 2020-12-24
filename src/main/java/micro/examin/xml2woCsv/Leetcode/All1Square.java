package micro.examin.xml2woCsv.Leetcode;

import java.util.Arrays;

public class All1Square {
    static int count = 0;

    public static void main(String[] args) {
        int arr[][] = {{0, 1, 1, 0, 1},
                {1, 1, 0, 1, 0},
                {0, 1, 1, 1, 0},
                {1, 1, 1, 1, 0},
                {1, 1, 1, 1, 1},
                {0, 0, 0, 0, 0}};
        System.out.println(Arrays.toString(getMaxSubSquare(arr)));
        System.out.println("count : " + count);
    }

    private static int[] getMaxSubSquare(int[][] arr) {
        int[] result = new int[4];
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                for (int k = 0; k < arr[0].length-(j-i); k++) {
                    int l = k+(j - i);
                    int sum = 0;
                    boolean anyZero = false;
                    if (i == 2 && j == 4 && k == 1 && l == 3) {
                        System.out.println();
                    }
                    for (int x = i; x < j; x++) {
                        for (int y = k; y < l; y++) {
                            if (anyZero) {
                                break;
                            } else {
                                if (arr[x][y] == 0) {
                                    anyZero = true;
                                }
                            }
                            sum++;
                            count++;
                        }
                    }
                    if (!anyZero && sum > max) {
                        result = new int[]{i, j, k, l};
                        max = sum;
                    }
                }
            }
        }
        for (int i = result[0]; i < result[1]; i++) {
            for (int j = result[2]; j < result[3]; j++) {
                System.out.print(arr[i][j] + ",");
            }
            System.out.println();
        }
        return result;
    }
}
