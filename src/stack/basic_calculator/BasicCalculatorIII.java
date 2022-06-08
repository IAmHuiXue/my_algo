package stack.basic_calculator;

import java.util.ArrayDeque;
import java.util.Deque;

/** <a href="https://leetcode.com/problems/basic-calculator-iii/">...</a> */

public class BasicCalculatorIII {

    // s consists of digits, '+', '-', '*', '/', '(', and ')'.

    private static int i = 0;

    public static int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int num = 0;
        char operator = '+';
        while (i < s.length()) {
            char cur = s.charAt(i++);
            if (cur == '(') {
                num = calculate(s);
            }
            if (Character.isDigit(cur)) {
                num = 10 * num + (cur - '0');
            }
            if (i == s.length() || cur == '+' || cur == '-' || cur == '*' || cur == '/' || cur == ')') {
                if (operator == '+') {
                    stack.offerFirst(num);
                } else if (operator == '-') {
                    stack.offerFirst(-num);
                } else if (operator == '*') {
                    stack.offerFirst(stack.pollFirst() * num);
                } else {
                    stack.offerFirst(stack.pollFirst() / num);
                }
                if (i != s.length() && cur != ')') {
                    operator = cur;
                }
                num = 0;
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
