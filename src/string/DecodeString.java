package string;

/**
 * <a href="https://leetcode.com/problems/decode-string/">https://leetcode.com/problems/decode-string/</a>
 */

public class DecodeString {
    // maintain a global pointer
    int i = 0;

    public String decodeString(String s) {
        StringBuilder sb = new StringBuilder();
        while (i < s.length()) {
            char cur = s.charAt(i);
            // cur could be [, ], letter, digit
            int num = 0;
            if (Character.isDigit(cur)) {
                while (Character.isDigit(cur)) {
                    num = 10 * num + (cur - '0');
                    cur = s.charAt(++i);
                }
            }
            if (cur == '[') {
                i++;
                String str = decodeString(s);
                while (num-- > 0) {
                    sb.append(str);
                }
            }
            if (cur == ']') {
                i++;
                return sb.toString();
            }
//            if (Character.isLetter(cur))
            i++;
            sb.append(cur);
        }
        return sb.toString();
    }
}
