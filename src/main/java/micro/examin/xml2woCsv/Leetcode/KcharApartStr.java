package micro.examin.xml2woCsv.Leetcode;

public class KcharApartStr {
    static int MAX_CHAR = 26;

    static int nextChar(int freq[], int dist[]) {
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < MAX_CHAR; i++)
            if (dist[i] <= 0 && freq[i] > 0 && (max == Integer.MIN_VALUE || freq[i] > freq[max])) {
                max = i;
            }
        return max;
    }

    static int rearrange(char str[], char out[], int d) {
        int n = str.length;
        int[] freq = new int[MAX_CHAR];
        for (int i = 0; i < n; i++) {
            freq[str[i]-97]++;
        }
        int[] dist = new int[MAX_CHAR];
        for (int i = 0; i < n; i++) {
            int j = nextChar(freq, dist);
            if (j == Integer.MIN_VALUE) {
                return 0;
            }
            out[i] = (char) (j+97);
            freq[j]--;
            dist[j] = d;
            for (int k = 0; k < MAX_CHAR; k++) {
                dist[k]--;
            }
        }
        return 1;
    }

    public static void main(String[] args) {
        char str[] = "aaaabbbcc".toCharArray();
        int n = str.length;
        char[] output = new char[n];
        if (rearrange(str, output, 2) == 1)
            System.out.println(String.valueOf(output));
        else
            System.out.println("Cannot be rearranged");
    }
}
