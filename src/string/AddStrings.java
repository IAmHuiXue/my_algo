package string;

/**
 * <a href="https://leetcode.com/problems/add-strings/">...</a>
 */

public class AddStrings {
    public String addStrings(String num1, String num2) {
        int carry = 0;
        int i = num1.length() - 1;
        int j = num2.length() - 1;
        StringBuilder sb = new StringBuilder();
        while (carry != 0 || i >= 0 || j >= 0) {
            if (i >= 0) {
                carry += num1.charAt(i--) - '0'; // remember to cast!
            }
            if (j >= 0) {
                carry += num2.charAt(j--) - '0'; // remember to cast!
            }
            // sb.append(carry % 10) also works
            sb.append((char) (carry % 10 + '0'));
            carry /= 10;
        }
        return sb.reverse().toString();
    }
    // time: O(max(n1, n2))
    // space: O(max(n1, n2))
}
