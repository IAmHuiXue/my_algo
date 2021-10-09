package string;

/**
 * https://leetcode.com/problems/valid-number/
 */

public class ValidNumber {
    /*

    1. Digits (one of ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9"])
    Both decimal numbers and integers must contain at least one digit.

    2. A sign ("+" or "-")
    Sign characters are optional for both decimal numbers and integers, but if one is present,
    it will always be the FIRST character, OR, it can also appear immediately after an exponent.

    3. An exponent ("e" or "E")
    Exponents are also optional, but if the string contains one then it must be after a decimal number or an integer.
    An integer must follow the exponent.

    4. A dot (".")
    A decimal number should only contain one dot. Integers cannot contain dots.

    5. Anything else
    There will never be anything else in a valid number.

     */
    public boolean isNumber(String s) {
//        s = s.trim();
        boolean dotSeen = false;
        boolean eSeen = false;
        boolean digitSeen = false;
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (Character.isDigit(cur)) {
                digitSeen = true;
            } else if (cur == '.') {
                if (eSeen || dotSeen) {
                    return false;
                }
                dotSeen = true;
            } else if (cur == 'e' || cur == 'E') {
                if (eSeen || !digitSeen) {
                    return false;
                }
                digitSeen = false; // clean up digitSeen, so we make sure there will be digits after e
                eSeen = true;
            } else if (cur == '-' || cur == '+') {
                if (i > 0 && s.charAt(i - 1) != 'e' && s.charAt(i - 1) != 'E') {
                    return false;
                }
            } else {
                return false;
            }
        }
        return digitSeen;
    }
}
