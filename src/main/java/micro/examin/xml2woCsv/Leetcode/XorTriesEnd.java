package micro.examin.xml2woCsv.Leetcode;

import java.util.LinkedList;

public class XorTriesEnd {
	private static int NUM_BITS = 32;
	static int[] finalPairCount;

	public static void main(String[] args) {
		int[] arr = {1, 2, 3};
		int k = 2;
		System.out.println(solve(arr, k));
	}

	static int[] solve(int[] arr, int k) {
		MyTrieNode root = new MyTrieNode();
		finalPairCount = new int[k + 1];
		int max = 0;
		for (int curr : arr) {
			if (curr > max) {
				max = curr;
			}
		}
		NUM_BITS = Integer.toBinaryString(max).length() + 1;
		for (int curr : arr) {
			for(int x =0;x<=k;x++) {
			  query(NUM_BITS - 1, curr, k, root);
			}
			insert(root, curr);
		}
		return finalPairCount;
	}

	private static void query(int index, int num, int k, MyTrieNode root) {
		MyTrieNode node = root;
		StringBuilder sb = new StringBuilder();
		for (int i = index; i >= 0; --i) {
			int bitK = ((k & (1 << i)) != 0) ? 1 : 0;
			int bitP = ((num & (1 << i)) != 0) ? 1 : 0;

			if (bitK == 0) {
				if (bitP == 0) {
					if (node.children[0] != null) {
						node = node.children[0];
						sb.append("0");
					} else {
						break;
					}
				}
				if (bitP == 1) {
					if (node.children[1] != null) {
						node = node.children[1];
						sb.append("1");
					} else {
						break;
					}
				}
			}
			if (bitK == 1) {
				if (bitP == 0) {
					if (node.children[0] != null) {
						calXork(node.children[0], k, num);
					}
					if (node.children[1] != null) {
						node = node.children[1];
						sb.append("1");
					} else break;
				}
				if (bitP == 1) {
					if (node.children[1] != null) {
						calXork(node.children[1], k, num);
					}
					if (node.children[0] != null) {
						node = node.children[0];
						sb.append("0");
					} else break;
				}
			}
		}
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
			for (int curr : node.list) {
				if ((curr ^ num) <= k) {
					finalPairCount[(curr ^ num)]++;
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
		public MyTrieNode[] children;
		LinkedList<Integer> list = new LinkedList<>();

		public MyTrieNode() {
			children = new MyTrieNode[2];
		}
	}
}
