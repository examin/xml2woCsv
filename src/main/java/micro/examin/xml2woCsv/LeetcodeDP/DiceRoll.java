package micro.examin.xml2woCsv.LeetcodeDP;

import java.util.Arrays;
public class DiceRoll {
	public static void main(String[] args) {
		DiceRoll roll =new DiceRoll();
		roll.numRollsToTarget(2,6,6);
	}
	public int numRollsToTarget(int d, int faces, int target) {
		int MOD = 1000000007;
		int dp[][] = new int[d+1][target+1];
		dp[0][0]=1;
		for(int i =1;i<=d;i++){
			for(int j = 1;j<=target&&j<=i*faces;j++){
				for(int f = 1;f<=faces&&f<=j;f++){
					try {
						dp[i][j] += dp[i - 1][j - f];
					}catch (Exception e){
						e.printStackTrace();
					}
				}
			}
		}
		for(int[] curr: dp){
			System.out.println( Arrays.toString(curr));
		}
		return dp[d][target];
	}
}
