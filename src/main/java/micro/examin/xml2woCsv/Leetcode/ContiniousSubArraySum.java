package micro.examin.xml2woCsv.Leetcode;

public class ContiniousSubArraySum {
	public boolean checkSubarraySum(int[] nums, int k) {
		if(k==0){
			int count=0;
			for(int curr: nums){
				if(curr==0){
					count++;
				}else{
					count=0;
				}
				if(count>1){
					return true;
				}
			}
			return false;
		}
		return checkSubarraySum(nums,k,0,0,0);
	}
	public boolean checkSubarraySum(int[] nums, int k, int index, int currSum, int count) {
		if(index == nums.length){
			return count>1&&currSum%k==0?true:false;
		}
		return checkSubarraySum(nums,k,index+1,currSum+nums[index],++count)|| checkSubarraySum(nums,k,index+1,0,0);
	}

	public static void main(String[] args) {
		ContiniousSubArraySum obj= new ContiniousSubArraySum();
		System.out.println(obj.checkSubarraySum(new int[]{0,1,0},0));
	}
}
