package basic_calculator;

import java.util.ArrayDeque;
import java.util.Deque;

/** https://leetcode.com/problems/basic-calculator/ */

public class BasicCalculator {

    // s consists of digits, '+', '-', '(', ')', and ' '.


    private static int i = 0;
    public static int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int sum = 0;
        char operator = '+';
        while (i < s.length()) {
            char cur = s.charAt(i++);
            if (Character.isDigit(cur)) {
                sum = 10 * sum + (cur - '0');
            }
            if (cur == '(') {
                sum = calculate(s);
            }
            if (i == s.length() || cur == '+' || cur == '-' || cur == ')') {
                if (operator == '+') {
                    stack.offerFirst(sum);
                } else {
                    stack.offerFirst(-sum);
                }
                if (i != s.length() && cur != ')') {
                    operator = cur;
                }
                sum = 0;
            }
            if (cur == ')') {
                break;
            }
            // if (cur == ' ') {
            //   continue;
            // }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pollFirst();
        }
        return res;
    }
}
