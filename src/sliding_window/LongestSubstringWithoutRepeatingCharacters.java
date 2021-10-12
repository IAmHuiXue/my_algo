package sliding_window;

import java.util.HashSet;
import java.util.Set;

/** https://leetcode.com/problems/longest-substring-without-repeating-characters/ */

public class LongestSubstringWithoutRepeatingCharacters {
    static class WhileLoop {
        public int lengthOfLongestSubstring(String s) {
            Set<Character> set = new HashSet<>();
            int longest = 0;
            int left = 0;
            int right = 0;
            while (right < s.length()) {
                if (set.add(s.charAt(right))) {
                    longest = Math.max(longest, ++right - left);
                    continue;
                }
                set.remove(s.charAt(left++));
            }
            return longest;
        }
    }

    static class ForLoop {
        public int lengthOfLongestSubstring(String s) {
            Set<Character> set = new HashSet<>();
            int longest = 0;
            int left = 0;
            for (int right = 0; right < s.length(); right++) {
                while (!set.add(s.charAt(right))) { // 1. 进
                    set.remove(s.charAt(left++)); // 2. 出
                }
                longest = Math.max(longest, right - left + 1); // 3. 算
            }
            return longest;
        }
    }

}
