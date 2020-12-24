package micro.examin.xml2woCsv.codeforce;

/* Name of the class has to be "Main" only if the class is public. */
class Ideone {
    public static void main(String[] args) throws java.lang.Exception {
        int arr[] = {2,3,4,3,2,1};
        System.out.println(stock_runs(arr));
    }

    public static int stock_runs(int[] prices) {
        if (prices.length == 0) {
            return 0;
        }
        int max = 1;
        int gMax = 1;
        int last = prices[0];
        int decis = 0;
        for (int i = 1; i < prices.length; i++) {
            int currPrice = prices[i];
            switch (decis) {
                case 0:
                    if (currPrice < last) {
                        max++;
                        decis = -1;
                    } else {
                        max++;
                        decis = 1;
                    }
                    break;
                case 1:
                    if (currPrice > last) {
                        max++;

                    } else {
                        if (currPrice < last) {
                            max = 2;
                            decis = -1;
                        }else {
                            max = 1;
                            decis = 0;
                        }
                    }
                    break;
                case -1:
                    if (currPrice < last) {
                        max++;

                    } else {
                        if (currPrice > last) {
                            max = 2;
                            decis = 1;
                        }else {
                            max = 1;
                            decis = 0;
                        }
                    }
            }
            gMax = Math.max(max, gMax);
            last = currPrice;
        }
        return max;
    }
}

