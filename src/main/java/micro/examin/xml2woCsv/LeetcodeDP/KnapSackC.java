package micro.examin.xml2woCsv.LeetcodeDP;

public class KnapSackC {
    int countDp = 0;
    int countbr = 0;

    public static void main(String[] args) {
        // element at index 0 is fake. matrix starting from 1.
        int weightArr[] = {2, 2, 2};
        int valueArr[] = {6, 10, 12};
        KnapSackC obj = new KnapSackC();
        int[][] dp = new int[100+1][weightArr.length+1];
        System.out.println(obj.knapsack(100, weightArr, valueArr, 0));
        System.out.println(obj.knapsack(100, weightArr, valueArr, 0, dp));
        System.out.println("with : "+ obj.countDp);
        System.out.println("without : "+ obj.countbr);
    }

    public int knapsack(int maxW, int weightArr[], int valueArr[], int index) {
        countbr++;
        if (index >= weightArr.length || maxW < weightArr[index]) {
            return 0;
        }
        int without = knapsack(maxW, weightArr, valueArr, index + 1);
        int with = valueArr[index] + knapsack(maxW - weightArr[index], weightArr, valueArr, index + 1);
        return Math.max(with, without);
    }

    public int knapsack(int maxW, int weightArr[], int valueArr[], int index, int[][] dp) {
        countDp++;
        if (index >= weightArr.length || maxW < weightArr[index]) {
            return 0;
        }
        if(dp[maxW][index]!=0){
            return dp[maxW][index];
        }
        int without = knapsack(maxW, weightArr, valueArr, index + 1,dp);
        int with = valueArr[index] + knapsack(maxW - weightArr[index], weightArr, valueArr, index + 1,dp);
        dp[maxW][index] = Math.max(with, without);
        return dp[maxW][index];
    }
}