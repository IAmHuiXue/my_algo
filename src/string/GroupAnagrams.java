package string;

import java.util.*;

/** <a href="https://leetcode.com/problems/group-anagrams/">https://leetcode.com/problems/group-anagrams/</a> */

public class GroupAnagrams {
    static class SortWords {
        public List<List<String>> groupAnagrams(String[] strs) {
            // sort each word, and put the sorted word in a groups as the key, the original word as an element of a groups value list
            Map<String, List<String>> groups = new HashMap<>();
            for (String str : strs) {
                char[] array = str.toCharArray();
                Arrays.sort(array);
                String key = String.valueOf(array);
                groups.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
            }
            return new ArrayList<>(groups.values());
        }

        // time: O(n*m*log(m)) where n words, length m of each word
        // space: O(n*m)
    }

    static class CreateCharSet {
        public List<List<String>> groupAnagrams(String[] strs) {
            Map<String, List<String>> groups = new HashMap<>();
            for (String str : strs) {
                int[] count = new int[26];
                for (char ch : str.toCharArray()) {
                    count[ch - 'a']++;
                }
                // the reason maintaining a sb is to avoid edge cases like:
                // [abb..b] and [aa..ab] where 11 b in the first array and 11 a in the second array
                // [111] and [111]
                // so we need to implement delimiters in the key String
                StringBuilder sb = new StringBuilder();
                for(int co : count) {
                    sb.append(co).append(',');
                }
                String key = sb.toString(); // "1, 2, 3, 0 ..."
                groups.computeIfAbsent(key, k -> new ArrayList<>()).add(str);
            }
            return new ArrayList<>(groups.values());
        }
        // time: O(n*m + n*a) where n words, length m of each word, a is the length of the charset -> 26
            // a to build a sb for each word
        // space: O(n*m + n*a)
            // a to store a sb for each word
    }
}
