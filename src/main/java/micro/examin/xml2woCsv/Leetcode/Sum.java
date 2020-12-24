package micro.examin.xml2woCsv.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;

public class Sum {
    public static void main(String[] args) {
        int arr[] = {3, 7, 1, 4, 6,2,7};
        ArrayList<int[]> result = sum1(arr,8);
        for(int[] curr:result){
            System.out.println(Arrays.toString(curr));
        }
        printpairs(arr,8);

    }
    //O(nlgn)
    public static ArrayList<int[]> sum1(int[] arr,int target) {
        Arrays.parallelSort(arr);
        int start = 0;
        int end = arr.length-1;
        ArrayList<int[]> result = new ArrayList<>();
        while(start<end){
            if(arr[start]+arr[end]==target){
                int[] fond = {arr[start],arr[end]};
                result.add(fond);
                start++;
                end--;
            }else{
                if(arr[start]+arr[end]<target){
                    start++;
                }else {
                    end--;
                }
            }
        }return  result;
    }
    static void printpairs(int arr[], int sum)
    {
        HashSet<Integer> s = new HashSet<Integer>();
        for (int i = 0; i < arr.length; ++i) {
            int temp = sum - arr[i];

            // checking for condition
            if (s.contains(temp)) {
                System.out.println("Pair with given sum " + sum + " is (" + arr[i] + ", " + temp + ")");
            }
            s.add(arr[i]);
        }
    }
}

