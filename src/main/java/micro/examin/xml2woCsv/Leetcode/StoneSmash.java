package micro.examin.xml2woCsv.Leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

public class StoneSmash {
    public static void main(String[] args) {

    }
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> queue = new PriorityQueue<>(stones.length, Collections.reverseOrder());
        for(int i =0;i<stones.length;i++){
            queue.add(stones[i]);
        }
        while (queue.size()>1){
            int x = queue.poll();
            int y = queue.poll();
            if(x!=y){
                queue.add(Math.abs(x-y));
            }
        }
        return queue.peek();
    }
}