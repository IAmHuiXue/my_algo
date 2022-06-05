package amazon;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/count-unique-characters-of-all-substrings-of-a-given-string/">...</a>
 */

public class CountUniqueCharactersOfAllSubstringsOfAGivenString {

    public int uniqueLetterString(String s) {

        // For each char, find the closest same char at it left and right side, and record its index.
        // For each char,the num of valid substring while this char is unique in this String will be (numOfLeft * numOfRight)
        // YXXYXXY
        // For the second Y, the range for it is XXYXX.
        // So the sum is 3*3 = 9. (need to add it self 2 + 1 = 3), please check the link for the proof: https://leetcode.com/problems/sum-of-subarray-minimums/discuss/178876/stack-solution-with-very-detailed-explanation-step-by-step

        if (s == null || s.length() == 0) {
            return 0;
        }
        // setup dp array to record closest same char idx
        int[] left = new int[s.length()];
        int[] right = new int[s.length()];

        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            left[i] = map.getOrDefault(c, -1); // !
            map.put(c, i);
        }
        map.clear();

        for (int i = s.length() - 1; i >= 0; i--) {
            char c = s.charAt(i);
            right[i] = map.getOrDefault(c, s.length()); // !
            map.put(c, i);
        }

        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            // for each char, find its left / right border and calculate the number of chars
            // draw examples to find out
            int numOfLeft = i - left[i];
            int numOfRight = right[i] - i;
            res += (numOfLeft * numOfRight);
        }
        return res;
    }

    // tc: O(3n)
    // sc: O(3n)
}
