package string;

import java.util.HashSet;
import java.util.Set;

/** https://leetcode.com/problems/minimum-deletions-to-make-character-frequencies-unique/ */

public class MinimumDeletionsToMakeCharacterFrequenciesUnique {
    public int minDeletions(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }
        int res = 0;
        // the set will store the individual freq of each character, and when there is a same freq,
        // reduce it until it is not the same with anyone in the set.
        Set<Integer> used = new HashSet<>();
        for (int i = 0; i < 26; ++i) {
            // how to prove that this way can get the least res?
            while (freq[i] > 0 && !used.add(freq[i])) { // if add() success, skip
                freq[i]--;
                res++;
            }
        }
        return res;
    }
}
