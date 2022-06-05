package dp;

import java.util.*;

/** <a href="https://leetcode.com/problems/concatenated-words/">...</a> */

public class ConcatenatedWords {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        Set<String> set = new HashSet<>(Arrays.asList(words));
        List<String> res = new ArrayList<>();
        for (String w : words) {
            if (valid(w, set)) {
                res.add(w);
            }
        }
        return res;
    }

    boolean valid(String word, Set<String> set) {
        if (word.length() == 0) {
            return false;
        }
        boolean[] dp = new boolean[word.length() + 1];
        // dp[i] represents if the first i letters including i can be concatenated
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                // A concatenated word is defined as a string that is composed entirely of at least TWO shorter words in the given array.
                if (j == 0 && i == dp.length - 1) {
                    continue;
                }
                if (dp[j] && set.contains(word.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[word.length()];
    }
    // time: O(m * n^3)
}
