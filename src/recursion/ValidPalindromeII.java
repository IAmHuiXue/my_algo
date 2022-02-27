package recursion;

/**
 * Given a non-empty string s, you may delete at most one character. Judge whether you can make it a palindrome.
 *
 *
 * Example 1:
 *
 * Input: "aba"
 * Output: True
 *
 * Example 2:
 *
 * Input: "abca"
 * Output: True
 * Explanation: You could delete the character 'c'.
 * Note:
 *
 * The string will only contain lowercase characters a-z. The maximum length of the string is 50000.
 */

public class ValidPalindromeII {
    public boolean validPalindrome(String input) {
        if (input == null) {
            return true;
        }
        return validPalindrome(input, false, 0, input.length() - 1);
    }

    private boolean validPalindrome(String input, boolean isDeleted, int start, int end) {
        if (start >= end) {
            return true;
        }
        if (input.charAt(start) == input.charAt(end)) {
            return validPalindrome(input, isDeleted, start + 1, end - 1);
        }
        if (isDeleted) {
            return false;
        }
//        isDeleted = true;
        return validPalindrome(input, true, start + 1, end)
                || validPalindrome(input, true, start, end - 1);
    }
}
