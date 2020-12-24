package micro.examin.xml2woCsv.Leetcode;

import java.util.ArrayList;
import java.util.HashSet;

public class OnesAndZeros {
    static int brucont = 0;
    static int dpcont = 0;
    static HashSet<String> set = new HashSet<>();
    static ArrayList<String> list = new ArrayList<>();

    public static void main(String[] args) {
        String arr[] = {"10", "0001", "111001", "1", "0"};
        int m = 5, n = 3;
        int currStrIndex = 0;
        int[][][] dp = new int[arr.length+1][m+1][n+1];
        // System.out.println(findMaxForm(arr, m, n, currStrIndex)+" took; "+brucont);
        System.out.println(findMaxFormDp(arr, m, n, currStrIndex, dp) + " took: " + dpcont);
       // System.out.println(findMaxFormBUDp(arr, m, n) + " took: " + dpcont);
    }

    private static int findMaxFormBUDp(String[] items, int m, int n) {
        int[][][] cache = new int[items.length + 1][m + 1][n + 1];
        for (int i = 1; i <= items.length; i++) {
            for (int j = 0; j <= m; j++) {
                // If including item[i-1] would exceed max weight j, don't
                // include the item. Otherwise take the max value of including
                // or excluding the item
                for (int k = 0; k <= n; k++) {
                    dpcont++;
                    int[] currStr10Count = countOnesAndZeros(items[i-1]);
                    if (currStr10Count[0] > m || currStr10Count[1] > n) {
                        cache[i][j][k] = cache[i - 1][j][k];
                    } else {
                        int withThisStr = 1 + cache[i-1][m - currStr10Count[0]][ n - currStr10Count[1]];
                        int withoutThisStr = cache[i-1][m][n];
                        cache[i][j][k] = Math.max(withThisStr,withoutThisStr);

                    }
                }

            }
        }

        return cache[items.length][m][n];
    }

    public static int findMaxForm(String[] strs, int m, int n, int currStrIndex) {
        brucont++;
        if (currStrIndex > strs.length - 1) {
            return 0;
        }
        int[] currStr10Count = countOnesAndZeros(strs[currStrIndex]);
        if (currStr10Count[0] > m || currStr10Count[1] > n) {
            return findMaxForm(strs, m, n, currStrIndex + 1);
        }
        int withThisStr = 1 + findMaxForm(strs, m - currStr10Count[0], n - currStr10Count[1], currStrIndex + 1);
        int withoutThisStr = findMaxForm(strs, m, n, currStrIndex + 1);
        return Math.max(withoutThisStr, withThisStr);
    }

    public static int findMaxFormDp(String[] strs, int m, int n, int currStrIndex, int[][][] dp) {
       if (dp[currStrIndex][m][n]!=0) {
            return dp[currStrIndex][m][n];
        }
        if (currStrIndex > strs.length - 1) {
            return 0;
        }
        int[] currStr10Count = countOnesAndZeros(strs[currStrIndex]);
        if (currStr10Count[0] > m || currStr10Count[1] > n) {
            return findMaxFormDp(strs, m, n, currStrIndex + 1, dp);
        }
        int withThisStr = 1 + findMaxFormDp(strs, m - currStr10Count[0], n - currStr10Count[1], currStrIndex + 1, dp);
        int withoutThisStr = findMaxFormDp(strs, m, n, currStrIndex + 1, dp);
        dp[currStrIndex][m][n]= Math.max(withoutThisStr, withThisStr);
        return Math.max(withoutThisStr, withThisStr);
    }

    private static int[] countOnesAndZeros(String str) {
        int one = 0;
        int zer = 0;
        for (char curr : str.toCharArray()) {
            if (curr == '0') {
                zer++;
            } else {
                one++;
            }
        }
        return new int[]{zer, one};
    }
}
