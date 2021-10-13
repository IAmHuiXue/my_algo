package sliding_window;

import java.util.HashMap;
import java.util.Map;

/** https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/ */

public class LongestSubstringWithAtLeastKRepeatingCharacters {
    public int longestSubstring(String s, int k) {
        int maxUnique = getMaxUniqueLetters(s);
        int res = 0;
        // 在每一组 unique 的元素情况下，求极值
        for (int unique = 1; unique <= maxUnique; unique++) {
            Map<Character, Integer> map = new HashMap<>();
            int left = 0;
            int validCount = 0;
            for (int right = 0; right < s.length(); right++) {
                char ch = s.charAt(right);
                int freq = map.getOrDefault(ch, 0);
                // 进
                map.put(ch, freq + 1);
                if (freq == k - 1) {
                    validCount++;
                }
                // 出
                while (map.keySet().size() > unique) {
                    char leftChar = s.charAt(left++);
                    freq = map.getOrDefault(leftChar, 0);
                    if (freq == k) {
                        validCount--;
                    }
                    map.put(leftChar, freq - 1);
                    // 记得 remove
                    if (freq == 1) {
                        map.remove(leftChar);
                    }
                }
                // 算
                int count = map.keySet().size();
                // window 所有的 character 均出现了 k 次
                // 即此时的 count 必须等于当前的 unique 数，且满足要求的 validCount 也要相等
                if (count == unique && count == validCount) {
                    res = Math.max(res, right - left + 1);
                }
            }
        }
        return res;
    }

    private int getMaxUniqueLetters(String s) {
        boolean[] map = new boolean[26];
        int maxUnique = 0;
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (!map[ch - 'a']) {
                maxUnique++;
                map[ch - 'a'] = true;
            }
        }
        return maxUnique;
    }
    // time: O(maxUnique * N)
}
