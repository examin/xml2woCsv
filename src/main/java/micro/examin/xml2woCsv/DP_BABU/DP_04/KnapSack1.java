package micro.examin.xml2woCsv.DP_BABU.DP_04;

public class KnapSack1 {
    public static void main(String[] args) {
        int profit[] = {60, 100, 120};
        int weight[] = {10, 20, 30};
        int maxWeight = 50;
        int[][] dp =  new int[weight.length+1][maxWeight+1];
        System.out.println("max profit brute : " + getKnapSack(profit, weight, maxWeight));
        System.out.println("max profit recur: " + recurGetKnapSack(profit, weight, 0,maxWeight,dp));
        System.out.println();
    }

    private static int recurGetKnapSack(int[] profit, int[] weight, int index,int maxWeight,int[][] dp) {
        if(index>weight.length-1){
            return 0;
        }else if(dp[index][maxWeight]>0){
            return dp[index][maxWeight];
        }
        else if(maxWeight-weight[index]<0){
            dp[index][maxWeight] = recurGetKnapSack(profit,weight,index+1, maxWeight,dp);
            return dp[index][maxWeight];
        }
        else {
            dp[index][maxWeight] = Math.max(recurGetKnapSack(profit,weight,index+1, maxWeight,dp),profit[index]+recurGetKnapSack(profit, weight, index+1, maxWeight-weight[index],dp));
            return dp[index][maxWeight];
        }
    }

    private static int getKnapSack(int[] profit, int[] weight, int maxWeight) {
        double times = Math.pow(weight.length, 2);
        int maxProfitSeenYet = 0;
        StringBuilder gSb = new StringBuilder();
        for (int i = 1; i < times; i++) {
            int currMaxProfit = 0;
            int currWeight = 0;
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < weight.length; j++) {

                if (currWeight+weight[j] <= maxWeight) {
                    if (((1 << j) & i) > 0) {
                        currMaxProfit += profit[j];
                        currWeight += weight[j];
                        sb.append(weight[j] + ", ");
                    }
                }else break;
            }
            if (currMaxProfit > maxProfitSeenYet) {
                maxProfitSeenYet = currMaxProfit;
                gSb = sb;
            }
        }
        System.out.println(gSb);
        return maxProfitSeenYet;
    }
}
