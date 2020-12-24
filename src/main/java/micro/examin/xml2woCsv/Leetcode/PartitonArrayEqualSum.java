package micro.examin.xml2woCsv.Leetcode;

public class PartitonArrayEqualSum {
	public boolean canPartition(int[] nums) {
		if(nums == null || nums.length == 0)
			return false;

		int sum = 0;
		for(int val : nums)
			sum += val;

		if((sum & 1) == 1)
			return false;
StringBuilder sb = new StringBuilder();
sb.append("45ttt");
sb.setLength(sb.length()-1);
		System.out.println(sb.toString());
		return true;
		//helper(nums, nums.length - 1, sum/2);
	}

	private boolean helper(int[] nums, int index, int sum) {
		if(sum == 0) return true;

			if(index < 0 || sum < 0 || sum < nums[index])
			return false;

		return helper(nums, index - 1, sum - nums[index]) || helper(nums, index - 1, sum);
	}

	public static void main(String[] args) {
		PartitonArrayEqualSum obj = new PartitonArrayEqualSum();
		System.out.println(obj.canPartition(new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,1,100}));
	}
}