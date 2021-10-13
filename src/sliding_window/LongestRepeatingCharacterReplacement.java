package sliding_window;

import java.util.HashMap;
import java.util.Map;

/** https://leetcode.com/problems/longest-repeating-character-replacement/ */

public class LongestRepeatingCharacterReplacement {
    public int characterReplacement(String s, int k) {
        // assume only 26 english letters, so an array[26] can replace a hashmap
        int[] map = new int[26];
        int left = 0;
        int res = 0;
        for (int right = 0; right < s.length(); right++) {
            char ch =  s.charAt(right);
            // 进
            map[ch - 'A']++;
            // 出
            while (right - left + 1 - findMax(map) > k) {
                map[s.charAt(left++) - 'A']--;
            }
            // 算
            res = Math.max(res, right - left + 1);
        }
        return res;

//        Map<Character, Integer> map = new HashMap<>();
//        int left = 0;
//        int res = 0;
//        for (int right = 0; right < s.length(); right++) {
//            char ch =  s.charAt(right);
//            map.put(ch, map.getOrDefault(ch, 0) + 1);
//            while (right - left + 1 - findMax(map) > k) {
//                ch = s.charAt(left++);
//                map.put(ch, map.get(ch) - 1);
//            }
//            res = Math.max(res, right - left + 1);
//        }
//        return res;

    }

    private int findMax(int[] map) { // o(1) because the size of map is only 26
        int res = 0;
        for (int count : map) {
            res = Math.max(res, count);
        }
        return res;

//        int res = 0;
//        for (int count : map.values()) {
//            res = Math.max(res, count);
//        }
//        return res;
    }
}
