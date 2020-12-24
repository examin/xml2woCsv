package micro.examin.xml2woCsv.Leetcode;

import java.util.Stack;

public class BackSlashString {
    public static void main(String[] args) {
        String i1 = "ab#c";
        String i2 = "ad#c";
        backspaceCompare(i1, i2);
    }

    public static boolean backspaceCompare(String str1, String str2) {
       return reduceString(str1).equals(reduceString(str2));
    }

    public static String reduceString(String str) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<str.length();i++){
            if(str.charAt(i)=='#'){
                if(stack.empty()){
                    stack.push(str.charAt(i));
                }else stack.pop();
            }else {
                stack.push(str.charAt(i));
            }
        }
        StringBuilder result = new StringBuilder();
        for(char curr: stack){
            result.append(curr);
        }
        System.out.println(result.toString());
        return result.toString();
    }

}
