package micro.examin.xml2woCsv.Leetcode;


public class EditDistance {
    public static void main(String[] args) {
        String s1 ="sea";
        String s2 ="eat";

        int[][] dp = new int[s1.length()+1][s2.length()+1];
        System.out.println(minEditDistanceHelper(s1,s2,0,0,dp));
    }

    private static int minEditDistanceHelper(String s1, String s2, int x, int y,int[][] dp) {
        if(dp[x][y] != 0){
            return dp[x][y];
        }
       if(x>=s1.length()||y>=s2.length()){
           return Math.max(s1.length()-x,s2.length()-y);
        }
       if(s1.charAt(x)==s2.charAt(y)){
           return minEditDistanceHelper(s1,s2,x+1,y+1,dp);
       }
       dp[x][y] = Math.min(minEditDistanceHelper(s1,s2,x,y+1,dp),minEditDistanceHelper(s1,s2,x+1,y,dp))+1;
       return dp[x][y];
    }
}
