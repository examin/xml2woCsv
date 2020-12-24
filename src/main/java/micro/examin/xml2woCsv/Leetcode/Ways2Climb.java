package micro.examin.xml2woCsv.Leetcode;

public class Ways2Climb {
    public static void main(String[] args) {

            System.out.println(climbStairs(8));
        }

        public static int climbStairs(int n) {
            int[] dp = new int[Math.max(n+1,3)];
            dp[2] = 2;
            dp[1] = 1;
            dp[0] = -1;
            if (n < 2) {
                if (n > 0) {
                    return dp[n];
                } else return 0;
            }
            for(int i =3;i<n+1;i++){
                dp[i] = dp[i-1]+dp[i-2];
            }
            return dp[n];
        }
}
