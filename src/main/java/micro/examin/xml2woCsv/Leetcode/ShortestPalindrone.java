package micro.examin.xml2woCsv.Leetcode;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

public class ShortestPalindrone {
	public static void main(String[] args) {
		ShortestPalindrone obj = new ShortestPalindrone();
		System.out.println(obj.convert("PAYPALISHIRING", 4));
	}

	public String shortestPalindrome(String s) {
		if (s.equals((new StringBuilder(s)).reverse().toString())) {
			return s;
		}
		char[] str = s.toCharArray();
		int[] scoreArr = new int[s.length()];
		for (int i = 0; i < s.length(); i++) {
			Integer score = palindromeScore(str, i);
		}
		return "";
	}

	public Integer palindromeScore(char[] str, int pivot) {
		if (pivot != 0 && str.length > 2) {
			if (str[pivot] == str[pivot - 1]) {

			}
		}
		if (pivot < str.length && str.length > 2) {
			if (str[pivot] == str[pivot + 1]) {

			}
		}
		return null;
	}

	public String convert(String s, int numRows) {
		TreeMap<Integer, Queue<Character>> resMap = new TreeMap<>();
		for (int i = 0; i < numRows; i++) {
			Queue<Character> list = new LinkedList<>();
			resMap.put(i, list);
		}
		StringBuilder sb = new StringBuilder();
		double col = Math.ceil(s.length() / (numRows + numRows - 2));
		int i = 0;
		for (int x = 0; x <= col; x++) {
			for (int j = 0; j < numRows && i < s.length(); j++, i++) {
				resMap.get(j).add(s.charAt(i));
			}
			for (int j = numRows - 2; j > 0 && i < s.length(); j--, i++) {
				resMap.get(j).add(s.charAt(i));
			}
		}

		for(Map.Entry<Integer, Queue<Character>> entry : resMap.entrySet()){
			for(Character curr: entry.getValue()) {
				sb.append(curr);
			}
		}
		return sb.toString();
	}
}