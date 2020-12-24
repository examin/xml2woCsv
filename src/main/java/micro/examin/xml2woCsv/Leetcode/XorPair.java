package micro.examin.xml2woCsv.Leetcode;

public class XorPair {
	public static void main(String[] args) {
		int[] arr = {4,4,5,5,5,6,4,2,7,8,5,9};
		int k = 5;

		for (int curr : arr) {
			System.out.println(curr + "->" + getBinary(curr));
		}
		System.out.println();
		solve(arr, k);
	}

	private static void solve(int[] arr, int k) {
		int result =0;
		for (int i = 0; i < arr.length; i++) {
			int count =0;
			for (int j = i - 1; j >= 0; j--) {
				if ((arr[i] ^ arr[j]) <= k) {
//					System.out.println(" ( " + arr[i] + " : " + arr[j] + " ) -> " + (arr[i] ^ arr[j]));
					System.out.println( getBinary(k) + " ( " + getBinary(arr[i]) + " : " + getBinary(arr[j]) + " ) -> " + getBinary(arr[i] ^ arr[j]));
					count++;
				}
			}
			System.out.println(arr[i] + " : "  + " -> " + count);
			result+=count;
		}
		System.out.println(result);
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