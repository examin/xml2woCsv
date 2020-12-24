package micro.examin.xml2woCsv.LeetcodeDP;

import java.util.Collections;
import java.util.PriorityQueue;

public class LastStoneWeight {
    public static void main(String[] args) {
        int[] arr = {2, 7, 4, 1, 8, 1};
        System.out.println(findLastStone(arr));
    }

    private static int findLastStone(int[] arr) {
        PriorityQueue<Integer> maxHeap = new PriorityQueue<Integer>(
                Collections.reverseOrder());
        for (int curr:arr){
            maxHeap.add(curr);
        }
        while (maxHeap.size()!=1){
            int one =    maxHeap.poll();
            int two =    maxHeap.poll();
            int diff = one-two;
            if(diff!=0){
                maxHeap.add(diff);
            }
        }
        return maxHeap.poll();
    }
}
