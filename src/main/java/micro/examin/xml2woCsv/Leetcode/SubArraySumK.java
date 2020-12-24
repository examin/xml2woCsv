package micro.examin.xml2woCsv.Leetcode;

import java.util.HashMap;

public class SubArraySumK {
    public static void main(String[] args) {
        int arr[] = {0,0,0,0,0,0,0,0,0,0};
        int k = 0;
        System.out.println(subarraySumOp(arr,k));
    }

    private static int subarraySumOp(int[] nums, int k) {
        if(nums.length==0){
            return 0;
        }
        int sumArr[] = new int[nums.length];
        sumArr[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            sumArr[i] = nums[i]+sumArr[i-1];
        }
        int result = 0;
        HashMap<Integer,Integer> prevTimes =  new HashMap<>();
        for(int i=0;i<sumArr.length;i++){
            if(sumArr[i]==k){
                result++;
            }
            result+=prevTimes.getOrDefault(sumArr[i]-k,0);
            prevTimes.put(sumArr[i],prevTimes.getOrDefault(sumArr[i],0)+1);
        }
        return result;
    }

    public static int subarraySum(int[] nums, int k) {
        if(nums.length==0){
            return 0;
        }
        int sumArr[] = new int[nums.length];
        sumArr[0] = nums[0];
        for(int i=1;i<nums.length;i++){
            sumArr[i] = nums[i]+sumArr[i-1];
        }
        int result = 0;
        for(int i=0;i<sumArr.length;i++){
            if(sumArr[i]==k){
                result++;
            }
            for(int j = 0;j<i;j++){
                if(sumArr[i]-sumArr[j]==k){
                    result++;
                }
            }
        }
        return result;
    }
}