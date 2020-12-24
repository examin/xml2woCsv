package micro.examin.xml2woCsv.Leetcode;

import java.util.Stack;

public class StarParenthesis {
    static char[] chances = {'(', ' ',')'};

    public static void main(String[] args) {
        String str = "(*)";
        System.out.println(anyValid(str.toCharArray(), -1));
    }

    private static boolean anyValid(char[] arr, int i) {
        if (i > arr.length - 1) {
            return isValid(arr);
        } else {
            for (int x = i + 1; x < arr.length; x++) {
                if (arr[x] == '*') {
                    for (char curr : chances) {
                        arr[x] = curr;
                        if (anyValid(arr, i + 1)) {
                            return true;
                        }
                    }
                }
            }
            return anyValid(arr, arr.length);
        }
    }

    private static boolean isValid(char[] arr) {
        Stack<Character> stack = new Stack<>();
        for (char curr : arr) {
            switch (curr) {
                case '(':
                    stack.push('(');
                    break;
                case ')':
                    if (!stack.isEmpty()&&stack.peek() == '(') {
                        stack.pop();
                    } else {
                        return false;
                    }
                    break;
            }
            if (curr == ' ') {
                continue;
            } else if (curr == '(') {
                stack.push('(');
            } else {
                if (stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}
