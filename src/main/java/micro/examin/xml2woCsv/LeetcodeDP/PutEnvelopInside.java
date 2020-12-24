package micro.examin.xml2woCsv.LeetcodeDP;

import java.util.Arrays;

public class PutEnvelopInside {
    public int maxEnvelopes(int[][] envelopes) {
        if(envelopes.length<2){
            return envelopes.length>0? 1: 0;
        }
        Arrays.sort(envelopes, (a1, a2) -> {return a1[0]==a2[0]?a2[1]-a1[1]:a1[0]-a2[0];} );

        int[] dp = new int[envelopes.length];
        dp[0] =1;
        int max= 1;
        for(int i = 1;i<envelopes.length;i++){
            dp[i] = 1;
            for(int j =0;j<i;j++){
                if(envelopes[i][0]>envelopes[j][0]&&envelopes[i][1]>envelopes[j][1]){
                    dp[i] = Math.max(dp[i],1+ dp[j]);
                    max = Math.max(dp[i],max);
                }
            }
        }
        return max;
    }

    public static void main(String[] args) {
        PutEnvelopInside sol = new PutEnvelopInside();
        System.out.println(sol.maxEnvelopes(new int[][] {{5,4},{6,4},{6,7},{2,3}}));
    }
}
