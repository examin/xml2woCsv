package micro.examin.xml2woCsv;

class Node {
	int val;
	Node left;
	Node right;

	Node() {
	}

	Node(int val) {
		this.val = val;
	}

	Node(int val, Node left, Node right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
}

class Solution {
	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.minDistance("horse", "ros"));
	}

	public int minDistance(String word1, String word2) {
		String now = new String();
		return minDistance(word1, word2, 0, 0, now);
	}

	public int minDistance(String word1, String word2, int index1, int index2, String now) {
		if (index1 >= word2.length()) {
			return word1.length() - index1;
		}
		if (index2 >= word2.length() || index1 >= word1.length()) {
			if (index2 >= word2.length() && index1 >= word1.length()&&now.equals(word2)) {
				return 0;
			} else {
				return 10000000;
			}
		}
		if (word1.charAt(index1) == word2.charAt(index2)) {
			now+=word2.charAt(index2);
			return minDistance(word1, word2, index1 + 1, index2 + 1, now);
		}
		int delete = 1 + minDistance(word1, word2, index1 + 1, index2, now);
		now += (word2.charAt(index2));
		int replace = 1 + minDistance(word1, word2, index1 + 1, index2 + 1, now);
		int insert = 1 + minDistance(word1, word2, index1, index2 + 1, now);

		return Math.min(Math.min(insert, delete), replace);
	}

}