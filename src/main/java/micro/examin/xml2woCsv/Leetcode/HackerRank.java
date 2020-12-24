package micro.examin.xml2woCsv.Leetcode;

import java.util.*;


class HackerRank {
	static int solve(int startInt, int target, TreeMap<Integer, LinkedList<String>> result, LinkedList<String> curr, int cost, HashMap<Integer, Integer> mapDp) {
		// recursion Break and target Condition check
		if (startInt == target) {
			LinkedList<String> newList = new LinkedList<>();
			for (String str : curr) {
				newList.add(str);
			}
			result.put(cost,newList);
			return 0;
		}

		if (startInt > target || startInt % 2 != 0 || target % 2 != 0) {
			return -1;
		}
		// dynamic Programming
		if(mapDp.containsKey(startInt)){
			return mapDp.get(startInt);
		}
		Integer[] divisors = getAllDivisors(startInt);
		int minCost = 10000000;
		for (int i = divisors.length - 2; i >= 0; i--) {
			curr.add("N :" + startInt + " div :" + divisors[i]);
			int got = solve(startInt + divisors[i], target, result, curr, cost + (startInt / divisors[i]), mapDp);
			if (got != -1) {
				minCost = Math.min(minCost, got + (startInt / divisors[i]));
			}
			curr.removeLast();
		}
		mapDp.put(startInt,minCost == 10000000 ? -1 : minCost);
		return minCost == 10000000 ? -1 : minCost;
	}

	static Integer[] getAllDivisors(int n) {
		TreeSet<Integer> result = new TreeSet<>();
		LinkedList<Integer> tor = new LinkedList<>();
		for (int i = 1; i <= Math.sqrt(n); i++) {
			if (n % i == 0) {
				// If divisors are equal, print only one
				if (n / i == i) {
					result.add(i);
				} else { // Otherwise print both
					result.add(i);
					result.add(n / i);
				}
			}
		}
		for (Integer curr : result) {
			if (curr % 2 == 0 || curr != n) {
				tor.add(curr);
			}
		}
		return tor.toArray(new Integer[tor.size()]);
	}


	static int solve2(int n, int m, TreeMap<Integer,LinkedList<String>> result, LinkedList<String> curr,int cost) {
		if (n == m) {
			LinkedList<String> newList = new LinkedList<>();
			for (String str : curr) {
				newList.add(str);
			}
			result.put(cost,newList);
			return 0;
		}
		if (n > m || n % 2 != 0 || m % 2 != 0) {
			return -1;
		}
		Integer[] divisors = getAllDivisors(n);
		int minCost = 10000000;
		for (int i = divisors.length - 2; i >= 0; i--) {
			curr.add("N :" + n + " div :" + divisors[i]);
			int got = solve2(n + divisors[i], m, result, curr, cost + (n / divisors[i]));
			if (got != -1) {
				return  (got + (n / divisors[i]));
			}
			curr.removeLast();
		}
		return minCost == 10000000 ? -1 : minCost;
	}

	public static void main(String[] args) {
		TreeMap<Integer, LinkedList<String>> result = new TreeMap();
		HashMap<Integer,Integer> dp = new HashMap<>();
		int got = solve(8, 6778, result, new LinkedList<>(), 0,dp);
		System.out.println(got);
		for (Map.Entry<Integer, LinkedList<String>> entry : result.entrySet()) {
			System.out.println(Arrays.toString(entry.getValue().toArray()));
			break;
		}
	}
}

