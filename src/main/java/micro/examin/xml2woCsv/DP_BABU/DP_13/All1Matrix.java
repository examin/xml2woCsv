package micro.examin.xml2woCsv.DP_BABU.DP_13;

import java.util.Arrays;

public class All1Matrix {
    public static void main(String[] args) {
       int[][] arr =
                        {{0, 1, 1, 0, 1},
                       {1, 1, 0, 1, 0},
                       {0, 1, 1, 1, 0},
                       {1, 1, 1, 1, 0},
                       {1, 1, 1, 1, 1},
                       {0, 0, 0, 0, 0}};
        System.out.println(all1Matrix(arr));
        System.out.println(dpAll1Matrix(arr));
    }

    private static int dpAll1Matrix(int[][] arr) {
        int[][] dp = new int[arr.length][arr[0].length];

        for(int i =0;i<arr.length;i++){
            dp[i][0] = arr[i][0];
        }
        for(int i =0;i<arr[0].length;i++){
            dp[0][i] = arr[0][i];
        }
        for(int i = 1;i<arr.length;i++){
            for(int j=1;j<arr[0].length;j++){
                if(arr[i][j] == 1) {
                    dp[i][j] = Math.min(dp[i][j - 1],
                            Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                }
                else {
                    dp[i][j] = 0;
                }
            }
        }
        for(int i =0;i<arr.length;i++){
            System.out.println(Arrays.toString(dp[i]));
        }
        return 1;
    }

    private static  int all1Matrix(int[][] arr){
        int maxSum = 0;
        int[] cordi = new int[4];
        for(int x= 0 ;x<arr.length;x++){
            for(int y=x;y<arr.length;y++){
                for(int p= 0 ;p<arr[0].length;p++){
                    for(int q=p;q<arr[0].length;q++){
                        boolean all1 = true;
                        int sum = 0;
                        for(int i= x ;i<=y;i++){
                            for(int j=p;j<=q;j++){
                                if(arr[i][j] ==0){
                                    all1 = false;
                                    i=y+1;
                                    j=q+1;
                                }
                                sum++;
                            }
                        }
                        if(all1){
                            if(sum>maxSum){
                                cordi[0] = x;
                                cordi[1] = y;
                                cordi[2] = p;
                                cordi[3] = q;
                                maxSum = sum;
                            }
                        }
                    }
                }
            }
        }
        return maxSum;
    }
}
