package stack.basic_calculator;

import java.util.ArrayDeque;
import java.util.Deque;

public class BasicCalculatorZero {
    /**
     * if only have +, -, and digits, no brackets
     * valid string
     */
    public int calculate1(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int sum = 0;
        char operator = '+';
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (Character.isDigit(cur)) {
                sum = 10 * sum + (cur - '0');
            }

            // here we should consider some cases
            if (i == s.length() - 1 || !Character.isDigit(cur)) {
                if (operator == '+') {
                    stack.offerFirst(sum);
                } else {
                    stack.offerFirst(-sum);
                }
                if (i != s.length() - 1) {
                    operator = cur;
                }
                sum = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pollFirst();
        }
        return res;
    }

    /**
     * if have +, -, *, /, and digits, no brackets
     * valid string
     */
    public int calculate2(String s) {
        Deque<Integer> stack = new ArrayDeque<>();
        int sum = 0;
        char operator = '+';
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (Character.isDigit(cur)) {
                sum = 10 * sum + (cur - '0');
            }
            if (i == s.length() - 1 || !Character.isDigit(cur)) {
                if (operator == '+') {
                    stack.offerFirst(sum);
                } else if (operator == '-') {
                    stack.offerFirst(-sum);
                } else if (operator == '*') {
                    // 比上一个多的就是如果当前的数字前面是 * or / 就需要 poll 出栈顶元素，进行运算后 offer 进栈
                    stack.offerFirst(stack.pollFirst() * sum);
                } else {
                    stack.offerFirst(stack.pollFirst() / sum);
                }
                if (i != s.length() - 1) {
                    operator = cur;
                }
                sum = 0;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pollFirst();
        }
        return res;
    }

    /**
     * if have +, -, *, /, (, ) and digits,
     * valid string
     */
    // need a global number to point the current position
    private int i = 0;

    public int calculate3(String s) {

        // where there are parentheses, we should use recursion to deal with t
        // when we meet (, we go into a recursion to deal with it, when we meet ),
        // we jump out of the recursion

        Deque<Integer> stack = new ArrayDeque<>();
        int sum = 0;
        char operator = '+';
        while (i < s.length()) { // change it to while loop
            char cur = s.charAt(i++); // here i has been updated
            if (Character.isDigit(cur)) {
                sum = 10 * sum + (cur - '0');
            }
            // when we meet a (, we run into a recursion
            if (cur == '(') {
                sum = calculate3(s);
            }
            if (i == s.length() || !Character.isDigit(cur)) {
                if (operator == '+') {
                    stack.offerFirst(sum);
                } else if (operator == '-') {
                    stack.offerFirst(-sum);
                } else if (operator == '*') {
                    // 比上一个多的就是如果当前的数字前面是 * or / 就需要 poll 出栈顶元素，进行运算后 offer 进栈
                    stack.offerFirst(stack.pollFirst() * sum);
                } else {
                    stack.offerFirst(stack.pollFirst() / sum);
                }
                if (i != s.length() && cur != ')') {
                    operator = cur;
                }
                sum = 0;
            }
            // when we meet ), we jump out of the recursion
            if (cur == ')') {
                break;
            }
        }
        int res = 0;
        while (!stack.isEmpty()) {
            res += stack.pollFirst();
        }
        return res;
    }
}
