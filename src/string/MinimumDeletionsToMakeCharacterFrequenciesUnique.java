package string;

import java.util.HashSet;
import java.util.Set;

/** https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/submissions/ */

public class MinimumDeletionsToMakeCharacterFrequenciesUnique {
    public int minDeletions(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        int res = 0;
        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < 26; ++i) {
            while (freq[i] > 0 && !used.add(freq[i])) {
                freq[i]--;
                res++;
            }
        }
        return res;
    }
}
