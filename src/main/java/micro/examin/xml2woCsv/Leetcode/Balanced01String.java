package micro.examin.xml2woCsv.Leetcode;

import java.util.HashMap;

public class Balanced01String {
    public static void main(String[] args) {
        int[] arr = {0};
        findMaxLength(arr);
    }

    public static int findMaxLength(int[] nums) {
        int max = 0;
        HashMap<Integer,Integer> lastIndex=  new HashMap<>();
        for(int i =0;i<nums.length;i++){
            if(nums[i]==0){
                nums[i] = -1;
            }
        }
        int run=0;
        for (int i = 0; i < nums.length; i++) {
            run+=nums[i];
            if(!lastIndex.containsKey(run)){
                lastIndex.put(run,i);
            }
            else {

                max = Math.max(i-lastIndex.get(run),max);
            }
        }
        return max;
    }
}
