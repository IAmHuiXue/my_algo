package monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;

/** https://leetcode.com/problems/remove-k-digits/ */

public class RemoveKDigits {
    public String removeKDigits(String num, int k) {
        // maintain 最小递增序列
        Deque<Character> stack = new ArrayDeque<>();
        for (int i = 0; i < num.length(); i++) {
            char cur = num.charAt(i);
            while (!stack.isEmpty() && k > 0 && cur < stack.peekFirst()) {
                stack.pollFirst();
                k--;
            }
            stack.offerFirst(cur);
        }
        // what if k is not yet 0?
        // since now, it is a 最小递增，then remove the rightmost elements
        while (k-- > 0) {
            stack.pollFirst();
        }
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.append(stack.pollFirst());
        }

        while (sb.length() != 0 && sb.charAt(sb.length() - 1) == '0') {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.length() == 0 ? "0" : sb.reverse().toString();
    }
}
