package micro.examin.xml2woCsv.DP_BABU.DP_13;

public class MaxSumIncSubSeq {
    public static void main(String[] args) {
        int[] arr = {1, 101, 2, 3, 100, 4, 5};
        System.out.println(maxSumIncSubSeq(arr));
    }

    public static int maxSumIncSubSeq(int[] arr) {
        if (arr.length < 0) {
            return 0;
        }
        int result = arr[0];
//        int maxIndex = 0;
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        for (int i = 1; i < arr.length; i++) {
            int curr = arr[i];
            int currMax = Integer.MIN_VALUE;
            boolean updated = false;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] <= curr) {
                    updated = true;
                    currMax = Math.max(currMax, dp[j]);
                }
            }
            if (updated) {
                dp[i] = currMax + arr[i];
            } else {
                dp[i] = arr[i];
            }
            if (dp[i] > result) {
//                maxIndex = i;
                result = dp[i];
            }
        }
        return result;
    }
}
