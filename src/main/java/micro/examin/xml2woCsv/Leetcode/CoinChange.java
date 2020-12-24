package micro.examin.xml2woCsv.Leetcode;

public class CoinChange {
    public static void main(String[] args) {
        int[] coins = {2, 5};
        int amount = 12;
        System.out.println(coinChange(coins, amount));
    }

    private static int coinChange(int[] coins, int amount) {

        int dp[] = new int[amount+1];
        for(int coin:coins){
            dp[coin] = 1;
        }

        for(int x=1;x<=amount;x++) {
            int min = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                if(coins[i]<=x&&dp[x-coins[i]]>=0){
                    int curr = dp[x-coins[i]]+1;
                    min = Math.min(curr,min);
                }
            }
            dp[x] = min>0&&min<Integer.MAX_VALUE?min:-1;
        }
        return  dp[amount];
    }
}
