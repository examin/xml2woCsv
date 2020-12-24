package micro.examin.xml2woCsv.Leetcode;

import java.util.LinkedList;
import java.util.List;

public class GenValidParenthesis {
	public static void main(String[] args) {
		int n = 3;
		GenValidParenthesis obj = new GenValidParenthesis();
		List<String> res = obj.generateParenthesis(n);
		for (String curr : res) {
			System.out.println(curr);
		}
	}

	public List<String> generateParenthesis(int n) {
		LinkedList<String> result = new LinkedList<>();
		char[] curr = new char[n * 2];

		if (n > 0) {
			curr[0] = '(';
			curr[(n * 2) - 1] = ')';
			int open = 1;
			int close = 1;
			generateParenthesis(n, curr, 1, result, open, close);
		}
		return result;
	}

	public void generateParenthesis(int n, char[] curr, int index, List<String> result, int open, int close) {
		if (index == (n * 2) - 1) {
			if (open == close) {
				result.add(new String(curr));
			}
		} else {
			curr[index] = '(';
			generateParenthesis(n, curr, index + 1, result, open + 1, close);

			if(close<=open) {
				curr[index] = ')';
				generateParenthesis(n, curr, index + 1, result, open, close + 1);
			}

		}
	}
}
