package micro.examin.xml2woCsv.Leetcode;

import java.util.ArrayList;
import java.util.Arrays;

public class ProdEceptSelf {
    public static void main(String[] args) {
        int[] arr = {1, 0};
        System.out.println(Arrays.toString(productExceptSelf(arr)));
    }

    public static int[] productExceptSelf(int[] arr) {
        int[] result = new int[arr.length];
        if (arr.length == 0) {
            return new int[]{0};
        }
        int tProd = 1;
        ArrayList<Integer> indexZero = new ArrayList<>();
        int zeros = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 0) {
                tProd *= arr[i];
            } else {
                zeros++;
                indexZero.add(i);
            }
        }
        if (zeros >= 1) {
            if (zeros > 1) {
                return result;
            } else {
                result[indexZero.get(0)] = tProd;
                    return result;
            }
        } else {
            for(int i =0;i<arr.length;i++){
                tProd = tProd/arr[i];
                result[i] = tProd;
                tProd*=arr[i];
            }
            return result;
        }
    }
}
