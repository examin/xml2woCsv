package micro.examin.xml2woCsv.LeetcodeDP;

public class twoKeyPad {
    public static void main(String[] args) {
        System.out.println(minSteps(3));
    }

    public static int minSteps(int n) {
        int dp[][] = new int[n+1][n+1];
        return minSteps(1, 0, n,dp);
    }

    private static int minSteps(int len, int cacheLen, int target,int[][] dp) {
        if(dp[len][cacheLen]!=0){
            return dp[len][cacheLen];
        }
        if (len == target) {
            return 0;
        }
        int result = Integer.MAX_VALUE;
        if (cacheLen > 0 && cacheLen + len <= target) {
            //only then i can do op on cache
            int pasteALl = minSteps(len + cacheLen, cacheLen, target,dp);
            if (pasteALl != -1) {
                result = Math.min(1 + pasteALl, result);
            }
        }
        if (cacheLen != len && len * 2 <= target) {
            int copyAll = minSteps(len, len, target,dp);
            if (copyAll != -1) {
                result = Math.min(1 + copyAll, result);
            }
        }
        dp[len][cacheLen] = result == Integer.MAX_VALUE ? -1 : result;
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
