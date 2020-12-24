package micro.examin.xml2woCsv.LeetcodeDP;

import java.util.LinkedList;

public class CoinChange2 {
	public int change(int amount, int[] coins) {
		LinkedList<String> list = new LinkedList<>();
		return change(amount,coins,list);
	}
	public int change(int amount, int[] coins, LinkedList<String> list) {
		if(amount<1){
			if(amount==0){
				for(String curr : list) {
					System.out.print(curr);
				}
				System.out.println();
			}
			return amount==0?1:0;
		}
		int count = 0;
		for(int coin :coins){
			list.add("+"+coin);
			count += change(amount-coin,coins,list);
			list.removeLast();
		}
		return count;
	}

	public static void main(String[] args) {
		CoinChange2 obj= new CoinChange2();
		System.out.println(obj.change(5,new int[]{1,2,5}));
	}
}
