package stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/** https://leetcode.com/problems/minimum-remove-to-make-valid-parentheses/ */

public class MinimumRemoveToMakeValidParentheses {
    public String minRemoveToMakeValid(String s) {

        /*
            The parentheses in a string are balanced if and only if these 2 conditions are met:
            1. There are the same number of "(" and ")" in the string.
            2. Scanning through the string from left to right and
                counting how many "(" and ")" there are so far, there should never be a time when
                there are more ")" than "(". We call count("(") - count(")") the balance of the string.

            We can use a stack. Each time we see a "(", we should add its INDEX to the stack.
            Each time we see a ")", we should remove an index from the stack because
            the ")" will match with whatever "(" was at the top of the stack.
            The length of the stack is equivalent to the balance from above. We will need to do the:
            1. Remove a ")" if it is encountered when stack was already empty (prevent negative balance).
            2. Remove a "(" if it is left on stack at end (prevent non-zero final balance).

            Algorithm
            Let's put all this together into a 2-pass algorithm.
            1. Identify all indexes that should be removed.
            2. Build a new string with removed indexes.
         */

        Deque<Integer> stack = new ArrayDeque<>();
        // first pass identify all indexes that should be removed
        Set<Integer> indicesToRemove = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '(') {
                stack.offerFirst(i);
            }
            if (s.charAt(i) == ')') {
                if (stack.isEmpty()) {
                    indicesToRemove.add(i);
                } else {
                    stack.pollFirst();
                }
            }
        }

        // put any indices remaining on stack into the set.
        while (!stack.isEmpty()) {
            indicesToRemove.add(stack.pollFirst());
        }

        // second pass build a result string
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            if (!indicesToRemove.contains(i)) {
                sb.append(s.charAt(i));
            }
        }
        return sb.toString();
    }
    // time: O(n)
    // space: O(n)
}
