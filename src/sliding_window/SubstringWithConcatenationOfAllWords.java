package sliding_window;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** https://leetcode.com/problems/substring-with-concatenation-of-all-words/ */

public class SubstringWithConcatenationOfAllWords {

    // build map1 for all words and their counts
    // for loop traverse s [0, s.length() - words.length * length of each word]
        // for each section, build map2 to find all matches

    public List<Integer> findSubstring(String s, String[] words) {
        Map<String, Integer> counts = new HashMap<>();
        for (String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        int num = words.length, len = words[0].length();
        for (int i = 0; i <= s.length() - num * len; i++) {
            // we should not directly modify counts because we need to compare with that at each iteration
            // Therefore, we create another map to record the tmp result at each iteration
            Map<String, Integer> seenAtCurrentIter = new HashMap<>();
            int j = 0;
            while (j < num) {
                String word = s.substring(i + j * len, i + (j + 1) * len);
                Integer count = counts.get(word);
                if (count == null) {
                    break;
                }
                seenAtCurrentIter.put(word, seenAtCurrentIter.getOrDefault(word, 0) + 1);
                if (seenAtCurrentIter.get(word) > count) {
                    break;
                }
                j++;
            }
            if (j == num) {
                res.add(i);
            }
        }
        return res;
    }
}
