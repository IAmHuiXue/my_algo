package string;

/** https://leetcode.com/problems/string-to-integer-atoi/ */

public class MyAToI {
    /**
     * corner cases needed to be discussed:
     * 1. null or empty string
     *      null, "" -> 0
     * 2. leading spaces
     *      "   123" -> 123
     * 3. sign, + or -
     *      "+123" -> 123
     *      "-123" -> -123
     *      "+-123" -> 0
     * 4. trailing spaces or other characters
     *      "123 1" -> 123
     *      "123a1" -> 1234
     *      "a123" -> 0
     *      "+ 123" -> 0
     * 5. overflow an integer
     *      Integer.MAX_VALUE/Integer.MIN_VALUE
     * 6. overflow a long
     */
    public int MyAToI(String s) {
        // let us assume the num will not overflow a long
        if (s == null || s.length() == 0) { // case 1
            return 0;
        }
        int len = s.length();
        int index = 0;
        while (index < len && s.charAt(index) == ' ') { // case 2
            index++;
        }
        boolean isPositive = true;
        if (index < len && (s.charAt(index) == '+' || s.charAt(index) == '-')) { // case 3
            isPositive = s.charAt(index) == '+';
            index++;
        }
        // we use long var to take care of Integer overflow potentials
        long sum = 0;
        // the cases such as multiple signs & non-digit characters before digits will
        // be prevented to go into the while loop
        while (index < len && Character.isDigit(s.charAt(index))) {
            sum = sum * 10 + (s.charAt(index) - '0'); // cast!
            //
            if (isPositive && sum > (long) Integer.MAX_VALUE) {
                return Integer.MAX_VALUE;
            }
            if (!isPositive && -sum < (long) Integer.MIN_VALUE) {
                return Integer.MIN_VALUE;
            }
            index++;
        }
        sum = isPositive ? sum : -sum;
        return (int) sum; // cast back!
    }
}
