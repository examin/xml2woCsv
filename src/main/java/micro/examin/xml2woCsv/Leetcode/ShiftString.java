package micro.examin.xml2woCsv.Leetcode;

public class ShiftString {
    public static void main(String[] args) {
        String str = "abcdefg";
        int[][] shift = {{1,1},{1,1},{0,2},{1,3}};
        System.out.println(stringShift(str,shift));
    }
    public static String stringShift(String s, int[][] shift) {
        int start =0;
        int strLen = s.length();
        for(int[] curr: shift){
            switch (curr[0]) {
                case 0: //left -
                    start -= curr[1];
                    break;
                case 1: //right +
                    start += curr[1];
                    break;
            }
        }
        start %=strLen;
        System.out.println(start);
        return s.substring(strLen-start)+s.substring(0,strLen-start);
    }
}
