package sliding_window.classic;

import java.util.HashMap;
import java.util.Map;

/** https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/ */

public class LongestSubstringWithAtMostKDistinctCharacters {
    public static int lengthOfLongestSubstringKDistinct(String s, int k) {
        Map<Character, Integer> map = new HashMap<>();
        int left = 0;
        int res = 0;
        for (int right = 0; right < s.length(); right++) {
            char cur = s.charAt(right);
            // 1. 进
            map.put(cur, map.getOrDefault(cur, 0) + 1);
            // 2. 出
            // 把 map 变成 valid 符合条件 -> 不符合的话把左边 left 的吐出来
            while (map.size() > k) {
                char c = s.charAt(left++);
                int count = map.get(c);
                map.put(c, count - 1);
                if (count == 1) {
                    map.remove(c); // !
                }
            }
            // 3. 算
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstringKDistinct(("eceba"), 2));
    }
}
