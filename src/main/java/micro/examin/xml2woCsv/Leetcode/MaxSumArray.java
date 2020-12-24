package micro.examin.xml2woCsv.Leetcode;

public class MaxSumArray {
    public static void main(String[] args) {
        int[] arr = {3,3,5,0,0,3,1,4};
        System.out.println(maxProfit(arr));
    }
    public static int maxProfit(int[] prices) {
        int oneT = maxProfit(prices,0,prices.length);
        int secT = 0;
        for(int i=0;i<prices.length;i++){
            int got = maxProfit(prices,0,i)+maxProfit(prices,i,prices.length);
            secT = Math.max(secT, got);
        }
        return Math.max(secT,oneT);

    }
    public static  int maxProfit(int[] prices, int start, int end){
        int min = prices[start];
        int res =0;
        for(int i=start;i<end;i++){
            if(prices[i]>min){
                res = Math.max(res,prices[i]-min);
            }else{
                min = prices[i];
            }
        }
        return res;
    }
}
