package sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/minimum-window-substring/
 */

public class MinimumWindowSubstring {
    public String minWindow(String s, String t) {

        // first, move right pointer until find a valid answer
        // then, move left pointer to possibly narrow down the answer until current sliding window is not valid
        // move right again and repeat

        Map<Character, Integer> map = new HashMap<>();
        for (char ch : t.toCharArray()) {
            map.put(ch, map.getOrDefault(ch, 0) + 1);
        }
        int left = 0;
        int minStart = 0; // since we need to return the final result of the substring
        int minLen = Integer.MAX_VALUE;
        int validCount = 0; // represent the num of valid chars we have met so far in s that match the chars in t
        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            Integer freq = map.get(ch);
            if (freq != null) {
                // freq <= 0 means right has previously visited a valid ch
                // therefore, we should not validCount duplicates
                // e.g.: there are 2 As in t, starting from 3rd-A we meet in s, we will not count it as a valid count
                if (freq > 0) {
                    validCount++;
                }
                map.put(ch, freq - 1);
            }
            // when we have a valid answer(s) now, we need to try to narrow down to find minLen
            while (validCount == t.length()) {
                if (right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    minStart = left;
                }
                char leftChar = s.charAt(left++);
                freq = map.get(leftChar);
                if (freq != null) {
                    if (freq >= 0) { // means before put, nums of leftChar is just balanced, now valid count needs to be decremented
                        validCount--;
                    }
                    map.put(leftChar, freq + 1);
                }
            }
        }
        return minLen == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLen);
    }
}
