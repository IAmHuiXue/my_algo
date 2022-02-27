package string;

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
        for (final String word : words) {
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }
        List<Integer> res = new ArrayList<>();
        int n = s.length(), num = words.length, len = words[0].length();
        for (int i = 0; i <= n - num * len; i++) {
            Map<String, Integer> seen = new HashMap<>();
            int j = 0;
            while (j < num) {
                String word = s.substring(i + j * len, i + (j + 1) * len);
                Integer count = counts.get(word);
                if (count != null) {
                    seen.put(word, seen.getOrDefault(word, 0) + 1);
                    if (seen.get(word) > count) {
                        break;
                    }
                } else {
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
