package micro.examin.xml2woCsv.Leetcode;

public class KPartSubString {
    public static void main(String[] args) {
        int[] nums = {4, 3, 2, 3, 5, 2, 1};
        int k = 4;
        System.out.println(canPartitionKSubsets(nums, k));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        int req = 0;
        if (sum != 0) {
            if (sum % k != 0) {
                return false;
            }
            req = sum / k;
        }
        boolean[] visited =  new boolean[nums.length];
        return partKSumSubSet(nums, k, req,visited);
    }

    private static boolean partKSumSubSet(int[] nums, int k, int req, boolean[] visited) {
        if (k == 0) {
            return true;
        }
        for (int i = 0; i < nums.length; i++) {
            if(!visited[i]){
                visited[i] =true;
                partKSumSubSet(nums, k, req,visited);
            }
        }
        return true;
    }
}
