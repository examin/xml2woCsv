package micro.examin.xml2woCsv.LeetcodeDP;

public class ca_TrainTicket {
    public static void main(String[] args) {
        int[] days = {1, 4, 6, 7, 8, 20};
        int[] costs = {2, 7, 15};
        System.out.println(mincostTickets(days, costs));
    }


    public static int mincostTickets(int[] days, int[] costs) {
        int[] dp = new int[365+1];
        int dInx = 0;
        for(int i=1;i<=365&&dInx<days.length;i++){
            if(i==days[dInx]) {
                int cost7 = i>6?dp[i - 7]:0;
                int cost30 = i>29?dp[i - 30]:0;
                int cost = Math.min(Math.min(dp[i-1]+costs[0],cost7 + costs[1]),cost30 + costs[2]);
                dp[i] = cost;
                dInx++;
            }else dp[i] = dp[i-1];
        }
        return dp[days[days.length-1]];
    }
}
