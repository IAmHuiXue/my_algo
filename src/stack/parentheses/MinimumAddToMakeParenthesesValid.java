package stack.parentheses;

import java.util.ArrayDeque;
import java.util.Deque;

/** <a href="https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/">...</a> */

public class MinimumAddToMakeParenthesesValid {
    public int minAddToMakeValidStack(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                stack.offerFirst('(');
            } else {
                if (!stack.isEmpty() && stack.peekFirst() == '(') {
                    stack.pollFirst();
                } else {
                    stack.offerFirst(')');
                }
            }
        }
        return stack.size();
    }

    public int minAddToMakeValidOptimized(String s) {
        // at any step, the total num of ) seen so far cannot be bigger than total num of (
        // otherwise, there is a need to add a ( before the current step
        int numRightToBePaired = 0;
        int numLeftToBePaired = 0;
        for (char c : s.toCharArray()) {
            if (c == '(') {
                numLeftToBePaired++;
            } else {
                if (numLeftToBePaired == 0) { // no extra ( seen can be paired
                    numRightToBePaired++;
                } else {
                    numLeftToBePaired--;
                }
            }
        }
        return numRightToBePaired + numLeftToBePaired;
    }
}
