package micro.examin.xml2woCsv.Leetcode;

public class LongestNonRepSubString {
    public static int lengthOfLongestSubstring(String s) {
        char[] input = s.toCharArray();
        int[] charCount = new int[400];
        int start = 0;
        int max = 0;
        for (int i = 1; i <= input.length; i++) {
            char curr = input[i-1];
            charCount[curr]++;
            if (charCount[curr] > 1) {
                int sItr = start;
                while (charCount[curr] > 1) {
                    --charCount[input[sItr]];
                    ++sItr;
                }
                start = sItr;
            }
            max = Math.max(i - start, max);
        }
        return max;
    }

    public static void main(String[] args) {
        String str = "pwwkew";
        System.out.println(lengthOfLongestSubstring(str));
    }
}
