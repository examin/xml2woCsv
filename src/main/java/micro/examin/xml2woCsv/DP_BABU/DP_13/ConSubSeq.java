package micro.examin.xml2woCsv.DP_BABU.DP_13;

import java.util.HashMap;

public class ConSubSeq {
    public static void main(String[] args) {
        int[] arr = {3, 4, 6, 7, 8};
        System.out.println(conSubSeq(arr));
    }

    public static int conSubSeq(int[] arr) {
        HashMap<Integer, Boolean> set = new HashMap<>();
        int minVal =Integer.MAX_VALUE;
        int maxVal =Integer.MAX_VALUE;
        for (int i = 0; i < arr.length; i++) {
            set.put(arr[i], Boolean.FALSE);
            maxVal = Math.max(maxVal,arr[i]);
            minVal = Math.min(minVal,arr[i]);
        }
        int result = 0;
        for (Integer curr : set.keySet()) {
            int max = 1;
            if (!set.get(curr)) {
                for (int i = curr - 1; i >=minVal; i--) {
                    if (set.containsKey(i)) {
                        set.put(i, true);
                        max++;
                    } else break;
                }
                for (int i = curr+1; i <= maxVal; i++) {
                    if (set.containsKey(i)) {
                        set.put(i, true);
                        max++;
                    } else break;
                }
            }
            result = Math.max(result, max);
        }
        return result;
    }
}
