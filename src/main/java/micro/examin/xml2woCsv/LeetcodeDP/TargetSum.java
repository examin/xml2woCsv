package micro.examin.xml2woCsv.LeetcodeDP;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class TargetSum {
    public static void main(String[] args) {
        int[] arr = new int[]{1, 1, 1, 1, 1};
        AtomicInteger atm = new AtomicInteger(0);
        LinkedList<String> list = new LinkedList<>();
        countTargetSum(arr, 0, 0, 3, atm, list);
        System.out.println(atm.intValue());
    }

    private static int countTargetSum(int[] arr, int index, int prevSum, int target, AtomicInteger atm, LinkedList<String> list) {
      if ( index == arr.length) {
            return  target == prevSum ?atm.incrementAndGet():0;
        }

       return  index >= arr.length?0:countTargetSum(arr, index + 1, prevSum + arr[index], target, atm, list)+ countTargetSum(arr, index + 1, prevSum - arr[index], target, atm, list);
    }
}
