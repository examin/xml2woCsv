package micro.examin.xml2woCsv.TreeOrGraph.Tree.DFS;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class PreOrder {
	public static void main(String[] args) {
		Node root = new Node(0);
		Node n1 = new Node(1);
		Node n2 = new Node(2);
		Node n3 = new Node(3);
		Node n4 = new Node(3);
		Node n5 = new Node(3);
		Node n6 = new Node(3);

		root.left = n1;
		root.right = n2;
		n1.left = n3;
		n1.right = n4;
		n2.left = n5;
		n2.right = n6;
	}

	private List<Integer> preOrderItr(Node root) {
		List<Integer> result = new LinkedList<>();
		Stack<Node> stack = new Stack<>();
		while (stack.size() > 0 || root != null) {
			while (root != null) {
				stack.add(root);
				root = root.left;
			}
			root = stack.pop();
			result.add(root.val);
			root = root.right;
		}
		return result;
	}
}

class Node {
	int val;
	Node left;
	Node right;

	public Node(int val) {
		this.val = val;
	}
}
