package micro.examin.xml2woCsv.DP_BABU.DP_04;

public class MatrixMultiplication {
    static  int cunt =0 ;
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4,3};
        int dp[][]  =  new int[arr.length][arr.length];
        for(int i =0;i<arr.length;i++){
            for(int j =0;j<arr.length;j++){
                if(i!=j){
                    dp[i][j]=Integer.MAX_VALUE;
                }
            }
        }
        System.out.println(matrixMultiplication(arr,1,arr.length-1,dp));
        System.out.println("\nBOTTON : "+BUmatrixMultiplication(arr,dp));
    }

    private static int BUmatrixMultiplication(int[] arr,int[][] dp) {
        for(int len  = 1;len<arr.length;len++){ //length
            System.out.println("\ndiff"+len);
            for(int start = 0 ;start<arr.length-len;start+=len) {
                int end  = start+len;
                int min = Integer.MAX_VALUE;
                System.out.println("start " + start + "| end" + end);
                for(int k=start+1;k<end;k++){
                    System.out.print(", splits :"+k);
                    int a = dp[start][k]+dp[k+1][end];
                    int z =(arr[start-1]*arr[k]*arr[end]);
                    int curr = a+z;
                    min = Math.min(min,curr);
                    System.out.print(" |curr :"+curr);
                }
                dp[start][end] = min;
                System.out.println();
            }
        }
        return dp[0][arr.length-1];
    }

    private static int matrixMultiplication(int[] arr,int start,int end,int[][] dp) {
        if(start==end){
            return 0;
        }
        int min= Integer.MAX_VALUE;
        if(dp[start][end]!=0){
            return dp[start][end];
        }
        for(int i =start;i<end;i++){ //cut at
           int a1 = matrixMultiplication(arr,start,i,dp) ;
           int z1 = matrixMultiplication(arr,i+1,end,dp);
           min = Math.min(a1+z1+(arr[start-1]*arr[i]*arr[end]),min);
           cunt++;
        }
        dp[start][end] = min;
        return min;
    }
}
