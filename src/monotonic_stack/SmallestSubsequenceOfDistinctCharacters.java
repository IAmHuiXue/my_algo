package monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/">...</a>
 */

public class SmallestSubsequenceOfDistinctCharacters {

    /**
     * 保持递增的 stack，如果之前的 char 不是最后一次出现，就被后面更小的顶掉
     */

    public String smallestSubsequence(String s) {
        // last[i] represent the index of the last time s.charAt(i) appears
        int[] last = new int[128];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i)] = i;
        }
        Deque<Integer> stack = new ArrayDeque<>();
        Set<Integer> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            int cur = s.charAt(i);
            if (!seen.add(cur)) { // cur has been added in the previous stage
                continue;
            }
            // stack 里面储存 char， 遇到下一个更小，并且前面的 char 并不是最后一次出现，就把前面的 pop 出去
            // 以此生成最小序列

            // for example:     c  b   c  a

            // stack has c when we are visiting b, because c > b and there will be c later on, we remove c from stack
            // and remember to update set as well while removing
            while (!stack.isEmpty() && cur < stack.peekFirst() && last[stack.peekFirst()] > i) {
                seen.remove(stack.pollFirst());
            }
            stack.offerFirst(cur);
        }
        StringBuilder sb = new StringBuilder();
        for (int i : stack) {
            sb.append((char) i);
        }
        return sb.reverse().toString();
    }
}

// similar problem

/**
 * <a href="https://leetcode.com/problems/remove-duplicate-letters/">...</a>
 */

class RemoveDuplicateLetters {
    public String removeDuplicateLetters(String s) {
        // last[i] represents the index of the last time s.charAt(i) appears
        int[] last = new int[26];
        for (int i = 0; i < s.length(); i++) {
            last[s.charAt(i) - 'a'] = i;
        }
        Deque<Character> stack = new ArrayDeque<>();
        Set<Character> seen = new HashSet<>();
        for (int i = 0; i < s.length(); i++) {
            char cur = s.charAt(i);
            if (!seen.add(cur)) { // cur has been added in the previous stage
                continue;
            }
            // stack is not empty + top element is larger than current + top element will occur in later positions
            while (!stack.isEmpty() && cur < stack.peekFirst() && last[stack.peekFirst() - 'a'] > i) {
                seen.remove(stack.pollFirst()); // remember to also remove it from the set
            }
            stack.offerFirst(cur);
        }
        StringBuilder sb = new StringBuilder();
        for (char i : stack) {
            sb.append(i);
        }
        return sb.reverse().toString();
    }
}
