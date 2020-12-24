package micro.examin.xml2woCsv.LeetcodeDP;

public class BuySellCoolDown {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2, 8, 4, 9};
        int fee = 2;
        System.out.println(maxProfit(arr, fee));
    }

    private static int maxProfit(int[] arr, int fee) {
        int[] owned = new int[arr.length];
        int[] clear = new int[arr.length];
        owned[0] = -arr[0];
        for (int i = 1; i < arr.length; i++) {
            clear[i] = Math.max(clear[i - 1], owned[i - 1] + arr[i] - fee);
            owned[i] = Math.max(owned[i - 1], clear[i - 1] - arr[i]);
        }
        return clear[arr.length - 1];
    }
}
