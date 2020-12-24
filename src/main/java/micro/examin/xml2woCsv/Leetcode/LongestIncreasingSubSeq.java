package micro.examin.xml2woCsv.Leetcode;

import java.util.Arrays;

public class LongestIncreasingSubSeq {
    public static void main(String[] args) {
        int arr[] = {1, 3, 5, 4, 1};
        System.out.println(lis(arr));
    }

    public static boolean lis(int[] arr) {
        if (arr.length < 2) {
            return false;
        }
        int[] dp = new int[arr.length + 1];
        Arrays.fill(dp, 1);
        dp[0] = 1;
        for (int i = 0; i < arr.length; i++) {
            int currMax = 0;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    currMax = Math.max(dp[j], currMax);
                }
            }
            dp[i] = currMax + 1;
            if (dp[i] == 3) {
                return true;
            }
        }
        return false;
    }
}
