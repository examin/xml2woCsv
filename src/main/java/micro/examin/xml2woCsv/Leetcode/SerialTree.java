package micro.examin.xml2woCsv.Leetcode;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

class TreeNode {
      int val;
      TreeNode left;
      TreeNode right;
      TreeNode(int x) { val = x; }
 }
 class SerialTree {

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		if(root==null){
			return "X,";
		}
		return root.val+ ","+serialize(root.left)+serialize(root.right);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		String[] input = data.split(",");
		return deserialize(input,0);
	}
	public TreeNode deserialize(String[] input, int index){
		if(index==input.length-1||input[index].equals("X")){
			return null;
		}
		TreeNode node = new TreeNode(Integer.parseInt(input[index]));
		index++;
		node.left = deserialize(input,index);
		index++;
		node.right = deserialize(input,index);
		return node;
	}

	 public static void main(String[] args) {
		int inp = 5;
		AtomicInteger in = new AtomicInteger(5);
		rec(inp,in);
		 System.out.println(inp);
		 System.out.println(in);
	/*	 SerialTree codec = new SerialTree();
		 TreeNode node = new TreeNode(1);
		 TreeNode node2 = new TreeNode(2);
		 TreeNode node3 = new TreeNode(3);
		 TreeNode node4= new TreeNode(4);
		 TreeNode node5 = new TreeNode(5);
		 node.left=node2;
		 node.right = node3;
		 node3.left = (node4);
		 node3.right = (node4);
         codec.deserialize(codec.serialize(node));*/
	 }

	 private static void rec(int inp, AtomicInteger in) {
		if(inp==10){
			return;
		}
		in.incrementAndGet();
		rec(++inp,in);
		 LinkedList<Integer> input = new LinkedList();
		 int mid = input.get(input.size()/2) + input.get(input.size()+1/2);
	 }


 }

// Your Codec object will be instantiated and called as such:
// Codec codec = new Codec();
// codec.deserialize(codec.serialize(root));