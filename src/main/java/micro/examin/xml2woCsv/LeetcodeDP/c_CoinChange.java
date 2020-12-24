package micro.examin.xml2woCsv.LeetcodeDP;

public class c_CoinChange {
    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        int target = 11;
//        System.out.println(coinChange(coins, target));
//        System.out.println(coinChangeRec(coins, target));
        c_CoinChange obj = new c_CoinChange();
        System.out.println(obj.coinChangeBrut(coins, target));
    }

    public int coinChangeBrut(int[] coins, int amount) {
        int min = Integer.MAX_VALUE;
        for (int curr : coins) {
            if (min > curr) {
                min = curr;
            }
        }
        if (coins.length < 1) {
            return -1;
        }
        return coinChangeHelper(coins, amount, min);
    }

    public int coinChangeHelper(int[] coins, int amount, int min) {
        if (amount == min) {
            return 1;
        }
        if (amount < min) {
            return -1;
        }
        int minNum = amount + 1;
        for (int curr : coins) {
            int withThisChoice = coinChangeHelper(coins, amount - curr, min);
            if (withThisChoice != -1) {
                if (minNum > withThisChoice) {
                    minNum = withThisChoice;
                }
            }
        }
        return minNum != amount + 1 ? 1 + minNum : -1;
    }

    public static int coinChangeRec(int[] coins, int amount) {
        if (amount <= 0) {
            return 0;
        }
        int min = amount + 1;
        for (int coin : coins) {
            if (amount - coin >= 0) {
                int curr = coinChangeRec(coins, amount - coin);
                if (curr >= 0) {
                    min = Math.min(min, coinChangeRec(coins, amount - coin) + 1);
                }
            }
        }
        return min == amount + 1 ? -1 : min;
    }

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            int min = amount + 1;
            for (int coin : coins) {
                if (i >= coin) {
                    if (dp[i - coin] != -1) {
                        min = Math.min(dp[i - coin] + 1, min);
                    }
                }
            }
            dp[i] = min == amount + 1 ? -1 : min;
        }
        return dp[amount];
    }
}
