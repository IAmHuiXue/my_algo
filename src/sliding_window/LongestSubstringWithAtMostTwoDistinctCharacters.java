package sliding_window;

import java.util.HashMap;
import java.util.Map;

/** https://leetcode.com/problems/longest-substring-with-at-most-two-distinct-characters/ */

public class LongestSubstringWithAtMostTwoDistinctCharacters {
    public int lengthOfLongestSubstringTwoDistinct(String s) {
        return LongestSubstringWithAtMostKDistinctCharacters.lengthOfLongestSubstringKDistinct(s, 2);
    }
}
