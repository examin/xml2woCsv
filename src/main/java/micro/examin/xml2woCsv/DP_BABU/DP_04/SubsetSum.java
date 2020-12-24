package micro.examin.xml2woCsv.DP_BABU.DP_04;

public class SubsetSum {
    public static void main(String[] args) {
        int[] arr = {6, 2, 3, 1};
        int sum = 10;
        StringBuilder sb = new StringBuilder();
        System.out.println(subSetSum(arr, sum, 0,sb));
        System.out.println(sb.toString());
    }

    private static boolean subSetSum(int[] arr, int sum, int i,StringBuilder sb) {
        if (i > arr.length - 1) {
            return false;
        } else if (sum - arr[i] == 0) {
            sb.append(arr[i]+" ,");
            return true;
        } else if (sum - arr[i] < 0) {
            return subSetSum(arr, sum, i + 1,sb);
        } else {
            if(subSetSum(arr, sum - arr[i], i + 1,sb)){
                sb.append(arr[i]+" ,");
                return true;
            }
            return subSetSum(arr, sum, i + 1,sb);
        }
    }
}
