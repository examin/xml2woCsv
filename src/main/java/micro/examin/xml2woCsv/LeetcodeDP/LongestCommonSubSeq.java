package micro.examin.xml2woCsv.LeetcodeDP;

class LongestPalindrome {
    public static void main(String[] args) {
        String s1 = "bb";
        LongestPalindrome obj = new LongestPalindrome();
        System.out.println(obj.longestPalindrome(s1));
    }

    public String longestPalindrome(String s) {
        String maxLen = "";
        for (int i = 0; i < s.length(); i++) {
            String oddLen = extend(s, i, i);
            String evenLen = extend(s, i, i + 1);
            if (oddLen.length() > maxLen.length()) {
                maxLen = oddLen;
            }
            if (evenLen.length() > maxLen.length()) {
                maxLen = evenLen;
            }
        }
        return maxLen;
    }

    private String extend(String s, int i, int j) {
        while (i >= 0 && j < s.length() && s.charAt(i) == s.charAt(j)) {
            i--;
            j++;
        }
        return s.substring(i + 1, j);
    }
}

class Solution {
    public String longestPalindrome(String str) {
        int strLen = str.length();
        int start = 0, end = 0;
        boolean[][] dp = new boolean[strLen][strLen]; //Store from i to j is palindrome or not
        for (int i = strLen - 1; i >= 0; i--) {
            for (int j = i; j < strLen; j++) { // starting from character at  i, expand and check
                if (str.charAt(i) == str.charAt(j)) {
                    if (j - i < 2) { //if len is less than 2, we have one char which is palindrome itself
                        dp[i][j] = true;
                    } else if (dp[i + 1][j - 1]) {//else check if prev characters were palindrome or not
                        dp[i][j] = true;
                    }
                }
                //if this is a new palindrome we have found , sync with global length variables
                if (dp[i][j] == true && j - i > end - start) {
                    start = i;
                    end = j;
                }
            }
        }
        return strLen == 0 ? "" : str.substring(start, end + 1);
    }
}


