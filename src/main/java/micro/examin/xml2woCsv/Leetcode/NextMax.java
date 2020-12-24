package micro.examin.xml2woCsv.Leetcode;

public class NextMax {
    public static void main(String[] args) {
        int input = 54987;
        nextGreaterElement(input);
    }

    public static int nextGreaterElement(int n) {
        if (n < 10) {
            return -1;
        }
        int len = String.valueOf(n).length();
        int last = n % 10;
        int temp = n / 10;
        int revHelper = 1;
        for (int i = 1; i < len; i++) {
            int curr = temp % 10;
            if (curr < last) {
                return ((((((temp / 10) * 10) + last) * 10) + curr) * revHelper)+n%revHelper;
            } else {
                last = curr;
            }
            temp = temp / 10;
            revHelper *= 10;
        }
        return -1;
    }
}