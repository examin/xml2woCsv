package micro.examin.xml2woCsv.Leetcode;

import java.util.Arrays;
import java.util.LinkedList;

public class XorTries2 {
	static int[] finalPairCount;
	private static final int NUM_BITS = 5;

	public static void main(String[] args) {
		int[] arr = {1,2,3};
		int k = 2;
		System.out.println(solve(arr, k));
	}

	static int solve(int[] arr, int k) {
		MyTrieNode root = new MyTrieNode();
		finalPairCount = new int[k + 1];
		int result = 0;
		for (int curr : arr) {
			if (curr == 6) {
				int a = 0;
			}
			result += query(NUM_BITS - 1, curr, k, root);
			insert(root, curr);
		}
		System.out.println(Arrays.toString(finalPairCount));
		return result;
	}

	private static int query(int index, int num, int k, MyTrieNode root) {
		MyTrieNode node = root;
		StringBuilder sb = new StringBuilder();
		int result = 0;
		int i = index;
		for (; i >= 0; --i) {
			int bitK = ((k & (1 << i)) != 0) ? 1 : 0;
			int bitP = ((num & (1 << i)) != 0) ? 1 : 0;

			if (bitK == 0 && bitP == 0) {
				if (node.children[0] != null) {
					node = node.children[0];
					sb.append("0");
				} else {
					break;
				}
			}
			if (bitK == 0 && bitP == 1) {
				if (node.children[1] != null) {
					node = node.children[1];
					sb.append("1");
				} else {
					break;
				}
			}
			if (bitK == 1 && bitP == 0) {
				if (node.children[0] != null) {
					result += node.leftLeafNodes;
					calXork(node.children[0], k, num);
					System.out.println(getBinary(k) + " ( " + getBinary(num) + " : " + sb.toString() + "0*" + ") >>" + node.leftLeafNodes);
				}
				if (node.children[1] != null) {
					node = node.children[1];
					sb.append("1");
				} else break;
			}
			if (bitK == 1 && bitP == 1) {
				if (node.children[1] != null) {
					result += node.rightLeafNodes;
					calXork(node.children[1], k, num);
					System.out.println(getBinary(k) + " ( " + getBinary(num) + " : " + sb.toString() + "1*" + ") >>" + node.rightLeafNodes);
				}
				if (node.children[0] != null) {
					node = node.children[0];
					sb.append("0");
				} else break;
			}
		}
		if (sb.length() == index + 1) {
			result += node.endCound;
			calXork(node, k, num);
			System.out.println(getBinary(k) + " ( " + getBinary(num) + " : " + sb.toString() + ")");
		}
		System.out.println(num + " -> " + result);
		return result;
	}

	private static void calXork(MyTrieNode root, int k, int num) {
		MyTrieNode node = root;
		if (node.endCound == 0) {
			if (node.children[0] != null) {
				calXork(node.children[0], k, num);
			}
			if (node.children[1] != null) {
				calXork(node.children[1], k, num);
			}
		} else {
			for(int curr : node.list){
				if((curr^num)<=k){
					finalPairCount[(curr^num)]++;
				}
			}
		}
	}

	private static void insert(MyTrieNode root, int num) {
		MyTrieNode node = root;
		for (int i = NUM_BITS - 1; i >= 0; --i) {
			int index = ((num & (1 << i)) != 0) ? 1 : 0;
			if (index == 0) {
				node.leftLeafNodes += 1;
			} else {
				node.rightLeafNodes += 1;
			}
			if (node.children[index] == null) {
				node.children[index] = new MyTrieNode();
			}
			node = node.children[index];
		}
		node.endCound += 1;
		node.list.add(num);
	}

	private static class MyTrieNode {
		public int leftLeafNodes;
		public int rightLeafNodes;
		public int endCound = 0;
		LinkedList<Integer> list = new LinkedList<>();

		public MyTrieNode[] children;

		public MyTrieNode() {
			children = new MyTrieNode[2];
		}
	}

	private static String getBinary(int i) {
//		String fullzero = "00000000000000000000000000000000";
		String fullzero = "00000";
		String str = Integer.toBinaryString(i);
		StringBuilder sb = new StringBuilder(fullzero.substring(str.length()));
		//System.out.println((sb+str).length());
		return (sb + str);
	}
}
