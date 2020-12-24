package micro.examin.xml2woCsv.Leetcode;

public class RangeBitwizeAnd {
}

// An efficient Java program to find bit-wise
// & of all numbers from x to y.
class GFG {

    // Find position of MSB in n. For example
    // if n = 17, then position of MSB is 4.
    // If n = 7, value of MSB is 3
    static int msbPos(long n) {

        int msb_p = -1;
        while (n > 0) {
            n = n >> 1;
            msb_p++;
        }

        return msb_p;
    }

    // Function to find Bit-wise & of all
    // numbers from x to y.
    static long andOperator(long x, long y) {

        long res = 0; // Initialize result

        while (x > 0 && y > 0) {

            // Find positions of MSB in x and y
            int msb_p1 = msbPos(x);
            int msb_p2 = msbPos(y);

            // If positions are not same, return
            if (msb_p1 != msb_p2)
                break;

            // Add 2^msb_p1 to result
            long msb_val = (1 << msb_p1);
            res = res + msb_val;

            // subtract 2^msb_p1 from x and y.
            x = x - msb_val;
            y = y - msb_val;
        }

        return res;
    }

    public static long rangeBitwiseAnd(long m, long n) {
        if (m == 0||m-n>m||Long.toBinaryString(m).length()!=Long.toBinaryString(n).length()) {
            return 0;
        }

        boolean isPow2 = (int) (Math.ceil((Math.log(n) / Math.log(2)))) ==
                (int) (Math.floor(((Math.log(n) / Math.log(2)))));
        if (isPow2) {
            return m;
        }
        for (long i = m + 1; i <= n; i++) {
            m &= i;
        }
        return m;
    }


    // Driver code
    public static void main(String[] args) {

        long x = 20000, y =
                2147483647;

        System.out.print(andOperator(x, y) == rangeBitwiseAnd(x, y));
        System.out.print(andOperator(x, y));
    }
}

// This code is contributed by Anant Agarwal.
