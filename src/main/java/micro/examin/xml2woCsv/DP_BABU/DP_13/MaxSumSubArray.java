package micro.examin.xml2woCsv.DP_BABU.DP_13;

public class MaxSumSubArray {
    public static void main(String[] args) {
        int arr[] = {2,3,-2,4};
        int dp[] = new int[arr.length];
        System.out.println(maxProduct(arr));
    }

    public static int maxProduct(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int maxProduct = nums[0];
        int result = nums[0];
        int minProduct = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if(nums[i]<0){
                minProduct = Math.max(maxProduct,maxProduct*nums[i]);
                maxProduct = Math.max(minProduct,minProduct*nums[i]);
            }else {
                maxProduct = Math.max(maxProduct,maxProduct*nums[i]);
                minProduct = Math.max(minProduct,minProduct*nums[i]);
            }
            result = Math.max(maxProduct,result);
        }
        return result;
    }
}
