package hash_table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** <a href="https://leetcode.com/problems/top-k-frequent-elements/">...</a> */

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        // needs to return elements (key of the map) sorted by frequencies (value of the map)
        // so the best way to place the entrySet() into a list and sort it
        // this way it is easier to keep the relationship between keys and values after sorting
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> Integer.compare(b.getValue(), a.getValue()));
        int[] res = new int[k];
        for (int i = 0; i < res.length; i++) {
            res[i] = list.get(i).getKey();
        }
        return res;
    }
}
