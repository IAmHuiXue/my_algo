package string;

/** <a href="https://leetcode.com/problems/string-compression/">https://leetcode.com/problems/string-compression/</a> */

public class StringCompression {
    public int compress(char[] chars) {
        int s = 0, f = 0;
        while (f < chars.length) {
            chars[s++] = chars[f++];
            int count = 1;
            while (f < chars.length && chars[f] == chars[f - 1]) {
                count++;
                f++;
            }
            if (count == 1) {
                continue;
            }
            char[] num = String.valueOf(count).toCharArray();
            for (char c : num) {
                chars[s++] = c;
            }
        }
        return s;
    }
}
