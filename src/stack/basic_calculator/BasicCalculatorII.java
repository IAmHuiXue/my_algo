package stack.basic_calculator;

import java.util.*;

/**
 * https://leetcode.com/problems/basic-calculator-ii/
 */

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

    public int calculateMyWay(String s) {
        // +, -, *, /
        Deque<Integer> stack = new ArrayDeque<>();
        int i = 0;
        char[] array = s.toCharArray();
        Set<Character> set = new HashSet<>(Arrays.asList('+', '-', '*', '/'));
        char operator = '+';
        while (i < array.length) {
            if (array[i] == ' ') {
                i++;
                continue;
            }
            if (set.contains(array[i])) {
                operator = array[i++];
                continue;
            }
            int num = 0;
            while (i < array.length && Character.isDigit(array[i])) {
                num = 10 * num + array[i++] - '0';
            }
            if (operator == '+') {
                stack.offerFirst(num);
                continue;
            }
            if (operator == '-') {
                stack.offerFirst(-num);
                continue;
            }
            if (operator == '*') {
                stack.offerFirst(stack.pollFirst() * num);
                continue;
            }
            stack.offerFirst(stack.pollFirst() / num);
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pollFirst();
        }
        return res;
    }
}
