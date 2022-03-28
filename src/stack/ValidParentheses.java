package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/valid-parentheses/
 */

public class ValidParentheses {
    public boolean isValid(String s) {
        Map<Character, Character> leftToRight = new HashMap<>();
        leftToRight.put('(', ')');
        leftToRight.put('[', ']');
        leftToRight.put('{', '}');

        Map<Character, Character> rightToLeft = new HashMap<>();
        rightToLeft.put(')', '(');
        rightToLeft.put(']', '[');
        rightToLeft.put('}', '{');

        char[] array = s.toCharArray();
        Deque<Character> stack = new ArrayDeque<>();
        for (char c : array) {
            if (leftToRight.containsKey(c)) { // is a left bracket
                stack.offerFirst(c);
                continue;
            }
            if (stack.isEmpty() || stack.peekFirst() != rightToLeft.get(c)) {
                return false;
            }
            stack.pollFirst();
        }
        return stack.isEmpty();
    }
}
