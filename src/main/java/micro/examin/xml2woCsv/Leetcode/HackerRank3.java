package micro.examin.xml2woCsv.Leetcode;

import java.util.LinkedList;
import java.util.List;

public class HackerRank3 {
	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			List<Integer> divisorsList = getAllDivisors(i);
			for (int curr : divisorsList) {
				List<Integer> divisorsList2 = getAllDivisors(i + curr);
				for (int curr2 : divisorsList) {
					if (!divisorsList2.contains(curr2)) {

					}
				}
			}
		}
	}

	static List<Integer> getAllDivisors(int n) {
		LinkedList<Integer> result = new LinkedList<>();
		for (int i = 2; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				if (n / i == i) {
					if (i % 2 == 0) result.add(i);
				} else {
					if (i % 2 == 0) result.add(i);
					if ((n / i) % 2 == 0) result.add(n / i);
				}
			}
		}
		return result;
	}
}
