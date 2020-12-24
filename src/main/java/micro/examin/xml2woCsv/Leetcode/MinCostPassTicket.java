package micro.examin.xml2woCsv.Leetcode;

import java.util.Arrays;

public class MinCostPassTicket {
    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 7, 8, 20};
        int[] costs = {2, 7, 15};

        int dp[] = new int[366];
        Arrays.fill(dp, Integer.MAX_VALUE);
        int dayToTravel = 0;
        dp[0] = 0;
        for (int i = 1; i < dp.length && dayToTravel < arr.length; i++) {
            if (dayToTravel > arr.length) {
                break;
            }
            if (i == arr[dayToTravel]) {
                if (i > 30) {
                    dp[i] = Math.min(dp[i-30]+costs[2],Math.min(dp[i-7]+costs[1],dp[i-1]+costs[0]));
                } else if (i >= 7) {
                    dp[i] = Math.min(dp[i-7]+costs[1],dp[i-1]+costs[0]);
                } else if (i >= 1) {
                    dp[i] = dp[i-1]+costs[0];
                }
                dayToTravel++;
            }else dp[i] = dp[i-1];

        }
        System.out.println(Arrays.toString(dp));    
        System.out.println(dp[arr[dayToTravel-1]]);
    }
}
