package micro.examin.xml2woCsv.LeetcodeDP;

public class PerfectSquare {
    public static void main(String[] args) {
       int n  = 10;
       System.out.println(perfectSquare(n));
    }

    private static int perfectSquare(int n) {
        if (n <= 0)
        {
            return 0;
        }

        // cntPerfectSquares[i] = the least number of perfect square numbers
        // which sum to i. Note that cntPerfectSquares[0] is 0.
        int[] cntPerfectSquares =  new int[n + 1];

        cntPerfectSquares[0] = 0;
        for (int i = 1; i <= n; i++)
        {
            // For each i, it must be the sum of some number (i - j*j) and
            // a perfect square number (j*j).
            for (int j = 1; j*j <= i; j++)
            {
                cntPerfectSquares[i] =
                        Math.min(cntPerfectSquares[i], cntPerfectSquares[i - j*j] + 1);
            }
        }

        return cntPerfectSquares[cntPerfectSquares.length-1];
    }
}
