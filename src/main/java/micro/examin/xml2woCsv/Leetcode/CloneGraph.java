package micro.examin.xml2woCsv.Leetcode;

import java.util.*;

class CloneGraph {

    public Node cloneGraph(Node node) {
        HashMap<Node, Node> map = new HashMap<>();
        map.put(node, new Node(node.val));
        Queue<Node> queue = new LinkedList();
        for (Node curr : node.neighbors) {
            queue.add(curr);
        }
        while (queue.size() != 0) {
            Node currNode = queue.poll();
            for (Node curr : currNode.neighbors) {
                if (!map.containsKey(curr)) {
                    map.put(curr, new Node(curr.val));
                    queue.add(curr);
                }

            }
        }

        HashMap<Node, Node> visited = new HashMap<>();
        visited.put(node, new Node(node.val));
        Queue<Node> tmpQueue = new LinkedList();
        for (Node curr : node.neighbors) {
            tmpQueue.add(curr);
        }
        while (tmpQueue.size() != 0) {
            Node currNode = tmpQueue.poll();
            Node newNode = map.get(currNode);
            for (Node curr : currNode.neighbors) {
                newNode.neighbors.add(map.get(curr));
                if (!visited.containsKey(curr)) {
                    visited.put(curr, new Node(curr.val));
                    tmpQueue.add(curr);
                }

            }
        }
        return map.get(node);
    }


    // Driver method to
    public static void main(String args[]) {
        Node n1 = new Node(1);
        Node n2 =  new Node(2);
        Node n3 =  new Node(3);
        Node n4 =  new Node(4);
        n2.neighbors.add(n4);
        n1.neighbors.add(n3);
        n2.neighbors.add(n4);
        n1.neighbors.add(n3);
//        Node n5 =  new Node();
//        Node n6 =  new Node();
//        Node n7 =  new Node();


        System.out.println("Following is Breadth First Traversal " +
                "(starting from vertex 2)");

        CloneGraph obj = new CloneGraph();
        obj.cloneGraph(n1);
    }
}
class Node {
    public int val;
    public List<Node> neighbors;

	public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
// Th
//is code is contributed by Aakash Hasija
