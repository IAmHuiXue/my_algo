package stack;

import java.util.ArrayDeque;
import java.util.Deque;

/** <a href="https://leetcode.com/problems/remove-all-adjacent-duplicates-in-string-ii/">...</a> */

public class RemoveAllAdjacentDuplicatesInStringII {
    public String removeDuplicates(String s, int k) {
        // stack will store the counts of the prev character visited
        StringBuilder sb = new StringBuilder(s);
        Deque<Integer> counts = new ArrayDeque<>();
        for (int i = 0; i < sb.length(); i++) {
            if (i == 0 || sb.charAt(i) != sb.charAt(i - 1)) {
                counts.offerFirst(1);
                continue;
            }
            int incremented = counts.pollFirst() + 1;
            if (incremented == k) {
                sb.delete(i - k + 1, i + 1);
                i -= k; // !
            } else {
                counts.offerFirst(incremented);
            }
        }
        return sb.toString();
    }
}
