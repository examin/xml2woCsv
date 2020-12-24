package micro.examin.xml2woCsv.Leetcode;

import java.util.List;

public class BinarySearchRotated {
    public static void main(String[] args) {
        int[] nums = {3, 5, 1};
        int target = 5;
        System.out.println(search(nums, target));
    }

    public static int search(int[] nums, int target) {
        int rotateIndex = getRotateIndx(nums);
        int first = binarySearch(nums, 0, rotateIndex, target);
        if (first != -1) {
            return first;
        }
        int second = binarySearch(nums, rotateIndex+1, nums.length-1, target);
        if (second != -1) {
            return second;
        } else return -1;

    }

    private static int binarySearch(int[] arr, int low, int high, int target) {
        int l = low, r = high;
        while (l <= r) {
            int m = l + (r - l) / 2;

            // Check if x is present at mid
            if (arr[m] == target)
                return m;

            // If target greater, ignore left half
            if (arr[m] < target)
                l = m + 1;

                // If target is smaller, ignore right half
            else
                r = m - 1;
        }

        // if we reach here, then element was
        // not present
        return -1;
    }

    private static int getRotateIndx(int[] nums) {
        int result = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i - 1] > nums[i]) {
                result = i-1;
                break;
            }
        }
        return result;
    }
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> list = binaryMatrix.dimensions();
        int c = list.get(1);
        int r = list.get(0);

        int min = Integer.MAX_VALUE-100;
        for(int row = 0;row<r;row++){
            int low = 0;
            int high = c-1;
            while (low <= high) {

                int mid = (low + high) / 2;
                int currMid = binaryMatrix.get(row,mid);
                // if true, then 'mid' is the index of first '1'
                if ( currMid == 1 && (mid == 0 || binaryMatrix.get(row,mid - 1) == 0)) {
                    System.out.println("result : "+mid);
                    min = Math.min(mid,min);
                    break;
                }

                // first '1' lies to the left of 'mid'
                else if (currMid == 1) {
                    high = mid - 1;
                }

                // first '1' lies to the right of 'mid'
                else  low = mid + 1;
            }
        }

        // 1's are not present in the array
        return min>99?-1:min;
    }
}
interface BinaryMatrix {
      public int get(int x, int y);
      public List<Integer> dimensions();
};
