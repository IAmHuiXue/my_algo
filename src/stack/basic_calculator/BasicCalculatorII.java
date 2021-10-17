package stack.basic_calculator;

import java.util.ArrayDeque;
import java.util.Deque;

/**  https://leetcode.com/problems/basic-calculator-ii/*/

public class BasicCalculatorII {

    // s consists of integers and operators ('+', '-', '*', '/') separated by some number of spaces.

    public static int calculate(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int sum = 0;
        char operator = '+';
        int i = 0;
        while (i < s.length()) {
            char cur = s.charAt(i++); // here i has been updated
            if (Character.isDigit(cur)) {
                sum = 10 * sum + (cur - '0');
            }
            if (i == s.length() || cur == '+' || cur == '-' || cur == '*' || cur == '/') {
                if (operator == '+') {
                    stack.offerFirst(sum);
                } else if (operator == '-') {
                    stack.offerFirst(-sum);
                } else if (operator == '*') {
                    stack.offerFirst(stack.pollFirst() * sum);
                } else {
                    stack.offerFirst(stack.pollFirst() / sum);
                }
                if (i != s.length()) {
                    operator = cur;
                }
                sum = 0;
                // if (cur == ' ') {
                //   continue;
                // }
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pollFirst();
        }
        return res;
    }
}
