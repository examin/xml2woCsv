package micro.examin.xml2woCsv.AtCoder.b158;

import java.math.BigInteger;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        BigInteger p = new BigInteger(String.valueOf(sc.nextInt()));
        BigInteger ans = new BigInteger(String.valueOf(0));
        String str = sc.next();
        int count=0;
        for (int i = 0; i < n; i++) {
            for (int j = i+1; j < n+1; j++) {
                BigInteger num = new BigInteger(str.substring(i,j));
                count+=num.remainder(p).equals(ans)?1:0;
            }
        }
        System.out.println(count);
    }
}