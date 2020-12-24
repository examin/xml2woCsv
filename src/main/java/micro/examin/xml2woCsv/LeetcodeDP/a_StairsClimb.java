package micro.examin.xml2woCsv.LeetcodeDP;

public class a_StairsClimb {
    public static void main(String[] args) {
        int[] costs = {10, 15, 20};
        System.out.println(minCostClimbingStairsBest(costs))        ;
    }

    public static int minCostClimbingStairs(int[] cost) {
        int dp[] = new int[cost.length];
        dp[0] = 0;
        dp[1] = 0;
        for (int i = 2; i < cost.length; i++) {
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length - 1];
    }

    public static int minCostClimbingStairs(int[] cost, int index) {
        if (index > cost.length - 1) {
            return 0;
        }
        return Math.min(minCostClimbingStairs(cost, index + 1), minCostClimbingStairs(cost, index + 2)) + cost[index];
    }

    public static int minCostClimbingStairsBest(int[] cost) {
        int last1 = cost[0];
        int last2 = cost[1];
        int result = 0;
        for (int i = 3; i < cost.length; i++) {
           result = Math.min(last1,last2)+cost[i];
        }
        return result;
    }

}
