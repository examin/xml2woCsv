package micro.examin.xml2woCsv.Leetcode;

public class JUMP1 {
    public static boolean canJump(int[] nums) {
        int reachable = 0;
        for (int i=0; i<nums.length; ++i) {
            if (i > reachable) return false;
            reachable = Math.max(reachable, i + nums[i]);
        }
        return true;
    }

    public static void main(String[] args) {
        int arr[] = {5,9,3,2,1,0,2,3,3,1,0,0};
        System.out.println(canJump(arr));
    }
}
