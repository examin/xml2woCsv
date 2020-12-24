import java.math.BigInteger;
import java.util.Arrays;

class Solution {
	public static void main(String args[]) throws Exception {
		String[][] inputs = {{"3", "-2"}, {"5", "5", "4", "2"}, {"4", "4", "4"}, {}, {"-214748364801", "-214748364802"}};
		String[] results = {"-2", "4", "-1", "-1", "-214748364802"};
		for (int i = 0; i < inputs.length; i++) {
			if (results[i].equals(getSecondMax(inputs[i]))) {
				System.out.println(true);
			} else {
				System.out.println(Arrays.toString(inputs[i]) + ": result got is :" + getSecondMax(inputs[i]) + " should be " + results[i]);
			}
		}
	}

	private static String getSecondMax(String[] input) {
		String largest = null;
		String secLargest = null;
		String defaultReturnValue = "-1";
		for (String curr : input) {
			if (isGreaterThanUsingBigInt(curr, largest)) {
				secLargest = largest;
				largest = curr;
			} else if (isGreaterThanUsingBigInt(curr, secLargest) && isGreaterThanUsingBigInt(largest, curr)) {
				secLargest = curr;
			}
		}
		String result = secLargest != null ? secLargest : defaultReturnValue;
		return result;
	}

	private static boolean isGreaterThanUsingBigInt(String first, String sec) {
		if(sec==null||first==null){
			if(sec!=null){
				return false;
			}else{
				return true;
			}
		}
		return (new BigInteger(first)).compareTo(new BigInteger(sec)) > 0;
	}

	private static boolean isGreaterThanImpl(String first, String sec) {
		String regex = "^0+(?!$)";
		first = first.trim().replaceAll(regex, "");
		sec = sec.trim().replaceAll(regex, "");
		if (first.length() > 0 && sec.length() > 0) {
			if (first.charAt(0) == '-' || sec.charAt(0) == '-') {
				if (first.charAt(0) != '-' && sec.charAt(0) == '-') {
					return true;
				}
				if (first.charAt(0) == '-' && sec.charAt(0) != '-') {
					return false;
				}
				String temp = first;
				first = sec.replaceFirst("-", "");
				sec = temp.replaceFirst("-", "");
			}
			if (first.length() > sec.length()) {
				return true;
			}
			if (sec.length() > first.length()) {
				return false;
			}
			for (int i = 0; i < first.length(); i++) {
				if (first.charAt(i) < sec.charAt(i)) {
					return false;
				}
			}
			return first.equals(sec) ? false : true;
		} else {
			return sec.length() == 0 ? true : false;
		}
	}
}

