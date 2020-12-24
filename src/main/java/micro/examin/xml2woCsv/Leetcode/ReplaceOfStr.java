package micro.examin.xml2woCsv.Leetcode;

import java.util.Arrays;

public class ReplaceOfStr {
	public static void main(String[] args) {
		ReplaceOfStr obj = new ReplaceOfStr();
		obj.modifyString("?");
	}
	public String modifyString(String s) {
		char[] arr = s.toCharArray();

		if (s.length() < 1) {
			return s;
		}
		if (arr[0] == '?') {
			if (s.length() > 1) {
				if (arr[1] == 'a') {
					arr[0] = 'b';
				} else {
					arr[0] = 'a';
				}
			}else{
				arr[0] = 'a';
			}
		}
		if (arr[s.length() - 1] == '?') {
			if (arr[s.length() - 2] == 'a') {
				arr[s.length() - 1] = 'b';
			} else {
				arr[s.length() - 1] = 'a';
			}
		}
		char[] options = {'a', 'b', 'c'};
		for (int i = 1; i < arr.length - 1; i++) {
			if (arr[i] == '?') {
				for (int j = 0; j < 3; j++) {
					if (arr[i - 1] != options[j]) {
						if (arr[i + 1] != options[j]) {
							arr[i] = options[j];
							break;
						}
					}
				}
			}
		}
		System.out.println(Arrays.toString(arr));
		return String.valueOf(arr);
	}
}
