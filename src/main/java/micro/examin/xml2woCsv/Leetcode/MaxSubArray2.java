package micro.examin.xml2woCsv.Leetcode;

public class MaxSubArray2 {
	public static void main(String[] args) {
		MaxSubArray2 obj = new MaxSubArray2();
		int k = 1;
		System.out.println(obj.maxSumArray2(new int[]{2, 3, 4, 5, -7, 1, 2}, k));
	}

	public int maxSumArray2(int[] arr, int k) {
		int[] mem = new int[arr.length];
		mem[0] = arr[0];
		int max = arr[0];
		for (int i = 1; i < k; i++) {
			mem[i] = Math.max(mem[i - 1] + arr[i], arr[i]);
			max = Math.max(max, mem[i]);
		}
		for (int i = k; i < arr.length; i++) {
			int gotMax = Integer.MIN_VALUE;
			for (int j = i - k; j < i; j++) {
				int cal = mem[j];
				for (int x = j + 2; x <= i; x++) {
					cal += arr[x];
				}
				gotMax = Math.max(gotMax,cal);
			}
			mem[i] = gotMax;
		}
		return Math.max(mem[arr.length-1],mem[arr.length-2]);
	}
}
