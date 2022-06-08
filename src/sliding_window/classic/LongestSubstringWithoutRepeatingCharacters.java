package sliding_window.classic;

import java.util.HashSet;
import java.util.Set;

/** <a href="https://leetcode.com/problems/longest-substring-without-repeating-characters/">...</a> */

public class LongestSubstringWithoutRepeatingCharacters {
    static class ForLoop {
        public int lengthOfLongestSubstring(String s) {
            Set<Character> set = new HashSet<>();
            int res = 0;
            int l = 0;
            for (int r = 0; r < s.length(); r++) {
                while (!set.add(s.charAt(r))) { // 1. 进
                    set.remove(s.charAt(l++)); // 2. 出
                }
                res = Math.max(res, r - l + 1); // 3. 算
            }
            return res;
        }
    }

    static class WhileLoop {
        public int lengthOfLongestSubstring(String s) {
            Set<Character> set = new HashSet<>();
            int res = 0;
            int l = 0;
            int r = 0;
            while (r < s.length()) {
                if (set.add(s.charAt(r))) {
                    res = Math.max(res, ++r - l);
                    continue;
                }
                set.remove(s.charAt(l++));
            }
            return res;
        }
    }

}
