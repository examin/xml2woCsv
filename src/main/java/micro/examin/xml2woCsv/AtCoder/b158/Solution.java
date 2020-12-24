package micro.examin.xml2woCsv.AtCoder.b158;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution {
    public static void main(String[] args) {
        ListNode[] lists = new ListNode[3];
        ListNode node = new ListNode(1);
        node.next = new ListNode(4);
        node.next = new ListNode(5);

        ListNode node1 = new ListNode(1);
        node1.next = new ListNode(3);
        node1.next = new ListNode(4);

        ListNode node2 = new ListNode(2);
        node2.next = new ListNode(6);

        lists[0] = node;
        lists[1] = node1;
        lists[2] = node2;
        ListNode result = mergeKLists(lists);
        while(result!=null){
            System.out.println(result.val);
            result = result.next;
        }

    }
    static ListNode mergeKLists(ListNode[] lists) {
        ListNode result = null;
        ListNode tor = null;
        PriorityQueue<dto> pq = new PriorityQueue<dto>(lists.length, new Comparator<dto>() {
            public int compare(dto lhs, dto rhs) {
                if (lhs.val > rhs.val) return +1;
                if (lhs.val == rhs.val) return 0;
                return -1;
            }
        });
        for(int i =0;i<lists.length;i++){
            ListNode curr = lists[i];
            pq.add(new dto(curr.val,i,curr));
        }
        while(!pq.isEmpty()){
            dto curr = pq.poll();
            if(null!=result){
                result.next = curr.node;
                if(lists[curr.from].next!=null){
                    lists[curr.from] = lists[curr.from].next;
                    ListNode now = lists[curr.from];
                    pq.add(new dto(now.val,curr.from,now));
                }
                result = result.next;
            }else {
                result = curr.node;
                tor = result;
                if(lists[curr.from].next!=null){
                    lists[curr.from] = lists[curr.from].next;
                    ListNode now = lists[curr.from];
                    pq.add(new dto(now.val,curr.from,now));
                    result = result.next;
                }
            }
        }
        return tor;
    }

}

class dto {
    int val;
    int from;
    ListNode node;

    dto(int val, int from, ListNode node) {
        this.val = val;
        this.from = from;
        this.node =  node;
    }
}

class ListNode {
    int val;
    ListNode next;

    ListNode(int x) {
        val = x;
    }
}