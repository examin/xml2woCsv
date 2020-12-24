package micro.examin.xml2woCsv.DP_BABU.DP_04;

public class LongestIncSubSeq {
    public static void main(String[] args) {
        int arr[] = {1, 2, 3, 10000, 5, 69};
        System.out.println(longestIncSubSeq(arr));
    }

    private static int longestIncSubSeq(int[] arr) {
        int dp[] = new int[arr.length];
        dp[0] = 1;
        int gMax = Integer.MIN_VALUE;
        for (int i = 1; i < arr.length; i++) {
            int max = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (arr[j] < arr[i]) {
                    max = Math.max(dp[j], max);

                }
            }
            dp[i] = 1 + max;
            gMax = Math.max(gMax, dp[i]);
        }
        return gMax;
    }
}
