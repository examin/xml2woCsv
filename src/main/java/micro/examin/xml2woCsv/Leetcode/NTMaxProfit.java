package micro.examin.xml2woCsv.Leetcode;

public class NTMaxProfit {
    public static void main(String[] args) {
        int prices[] = {1, 2, 3, 4};
        int k = 2;
        int[][] dp = new int[prices.length + 1][k + 1];
        for (int d = 1; d < prices.length; d++) {
            for (int t = 1; t <= k; t++) {
                dp[d][t] = Math.max(dp[d - 1][t], dp[d][t - 1]);
                int maxFromItr = 0;
                for (int itr = 0; itr < d; itr++) {
                    if (prices[itr] < prices[d]) {
                        int a = (prices[d] - prices[itr]) + dp[itr][t - 1];
                        maxFromItr = Math.max(a, maxFromItr);
                    }
                }
                dp[d][t] = Math.max(dp[d][t], maxFromItr);
            }
        }
        System.out.println(dp[prices.length - 1][k]);
    }
}
