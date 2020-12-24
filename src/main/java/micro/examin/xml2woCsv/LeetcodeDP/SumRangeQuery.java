package micro.examin.xml2woCsv.LeetcodeDP;

public class SumRangeQuery {
    public static void main(String[] args) {
        int nums[] = {-2, 0, 3, -5, 2, -1};
        int dp[] = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            dp[i] = dp[i - 1] + nums[i];
        }
        System.out.println(sumRange(2,5,dp));
    }

    public static int sumRange(int i, int j, int[] dp) {
        if(i>0){
            return dp[j]-dp[i-1];
        }
        return dp[j];
    }
}
