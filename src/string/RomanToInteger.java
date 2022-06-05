package string;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/roman-to-integer/">...</a>
 */

public class RomanToInteger {
    public int romanToInt(String s) {
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        map.put('I', 1);
        map.put('V', 5);
        map.put('X', 10);
        map.put('L', 50);
        map.put('C', 100);
        map.put('D', 500);
        map.put('M', 1000);

        int res = 0;
        for (int i = 0; i < chars.length; i++) {
            int cur = map.get(chars[i]);
            if (i == chars.length - 1) { // there is nothing on the right to compare to
                res += cur;
                continue;
            }
            int next = map.get(chars[i + 1]);
            if (cur >= next) {
                res += cur;
                continue;
            }
            res -= cur;
        }
        return res;
    }
}
