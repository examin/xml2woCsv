package micro.examin.xml2woCsv.Leetcode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TreeSet;


public class HackerRank2 {
	public static void main(String[] args) throws IOException {

	}
	static void solve(int q, int[][] queries) {
		TreeSet<Integer> sortedInputSet = new TreeSet<>();
		ArrayList<Integer> inputSeq = new ArrayList<>();
		for (int[] currQuery : queries) {
			switch (currQuery[0]) {
				case 1:
					inputSeq.add(currQuery[1]);
					sortedInputSet.add(currQuery[1]);
					break;
				case 2:
					int fir = inputSeq.get(currQuery[1]-1);
					inputSeq.set(currQuery[1]-1,-1);
					sortedInputSet.remove(fir);
					break;
				case 3:
					long minElement = sortedInputSet.first();
					long maxElement = sortedInputSet.last();
					System.out.println(  Math.max(Math.abs(maxElement-currQuery[1]) , Math.abs(minElement-currQuery[1])));
					break;
			}
		}
	}
}