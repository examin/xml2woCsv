package micro.examin.xml2woCsv.Leetcode;

public class JUMP2 {
    public static int jump(int[] arr) {
        int n = arr.length;
        int dp[] = new int[n];
        dp[n-1] = 0;
        for(int i =n-2;i>=0;i--){
            int canChooseFrom = i+arr[i];
            int min  = Integer.MAX_VALUE-2;
            for(int j=i+1;j<=canChooseFrom&&j<n;j++){
                if(dp[j]<min){
                    min = dp[j];
                }
            }
            dp[i] = 1+min;
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int input[] = {2,3,0,1,4};
        System.out.println(jump(input));
    }
}
