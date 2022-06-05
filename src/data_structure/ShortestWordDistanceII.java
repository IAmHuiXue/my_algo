package data_structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** <a href="https://leetcode.com/problems/shortest-word-distance-ii/">...</a> */

public class ShortestWordDistanceII {
    private Map<String, List<Integer>> map;

    public ShortestWordDistanceII(String[] wordsDict) {
        map = new HashMap<>();
        process(wordsDict);
    }

    private void process(String[] words) {
        for (int i = 0; i < words.length; i++) {
            map.computeIfAbsent(words[i], k -> new ArrayList<>()).add(i);
        }
    }

    public int shortest(String word1, String word2) {
        List<Integer> l1 = map.get(word1);
        List<Integer> l2 = map.get(word2);

        // both lists are sorted in ascending order
        // 采取谁小移谁，so that we will not miss the min result in linear time

        int shortest = Integer.MAX_VALUE;
        int i = 0, j = 0;
        while (i < l1.size() && j < l2.size()) {
            shortest = Math.min(shortest, Math.abs(l1.get(i) - l2.get(j)));
            if (l1.get(i) < l2.get(j)) {
                i++;
            } else {
                j++;
            }
        }
        return shortest;
    }
}
