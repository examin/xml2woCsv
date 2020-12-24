package micro.examin.xml2woCsv.Leetcode;

public class MinCostClimbingStairs {
    public static void main(String[] args) {
        int[] arr = {1, 100, 1, 1, 1, 100, 1, 1, 100, 1};
        System.out.println(getMinCostClimbingStairs(arr));
    }
    public static int getMinCostClimbingStairs(int[] arr){
        int[] dp =  new int[arr.length+1];
        dp[0] = 0;
        dp[1] = 0;
        for(int i=2;i<=arr.length;i++){
            int oneJump = arr[i-1]+dp[i-1];
            int twoJump = arr[i-2]+dp[i-2];
            dp[i] = oneJump<twoJump?oneJump:twoJump;
        }
        return dp[arr.length];
    }
}
