package micro.examin.xml2woCsv.DP_BABU.DP_04;

import java.util.Arrays;

public class PartitionProblem {
    public static void main(String[] args) {
        int[] arr = {5, 4, 1, 10};
        int sum = 0;
        boolean[] result = new boolean[arr.length];
        for (int curr : arr) {
            sum += curr;
        }

        System.out.println(partition(arr,sum/2,0,result));
        System.out.println(Arrays.toString(result));
    }

    //backtracking
    private static boolean partition(int[] arr, int required, int index, boolean[] result) {
            if(required==0){
                return true;
            }
            if(index>arr.length){
                return false;
            }
            boolean include = partition(arr,required-arr[index],index+1, result);
            boolean exclude = partition(arr,required-arr[index],index+1, result);
            if(include){
                result[index]=true;
                return include;
            }else return exclude;

    }
}
