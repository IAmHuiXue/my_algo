package stack;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/evaluate-reverse-polish-notation/">...</a>
 */

public class EvaluateReversePolishNotation {
    // the expression is assumed to be always valid
    // scan the token from left to right
    // if token is a num, put in stack
    // if token is an operator, take out two nums from stack and do the math, then put the result back to stack
    // there could be negative numbers

    public int evalRPN(String[] tokens) {
        Deque<Integer> stack = new ArrayDeque<>();
        Set<String> ops = new HashSet<>(Arrays.asList("+", "-", "*", "/"));
        for (String token : tokens) {
            if (ops.contains(token)) {
                if (token.equals("+")) {
                    stack.offerFirst(stack.pollFirst() + stack.pollFirst());
                    continue;
                }
                if (token.equals("*")) {
                    stack.offerFirst(stack.pollFirst() * stack.pollFirst());
                    continue;
                }
                if (token.equals("-")) {
                    // need to use the second last - last
                    int last = stack.pollFirst();
                    stack.offerFirst(stack.pollFirst() - last);
                    continue;
                }
                if (token.equals("/")) {
                    // need to use the second last / last
                    int last = stack.pollFirst();
                    stack.offerFirst(stack.pollFirst() / last);
                }
            } else {
                if (token.charAt(0) == '-') {
                    stack.offerFirst(-Integer.parseInt(token.substring(1)));
                    continue;
                }
                stack.offerFirst(Integer.parseInt(token));
            }
        }
        return stack.peekFirst();
    }
}
