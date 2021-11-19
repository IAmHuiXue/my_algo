package hash_table;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** https://leetcode.com/problems/least-number-of-unique-integers-after-k-removals/ */

public class LeastNumberOfUniqueIntegersAfterKRemovals {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {
        // get the freq of each individual number
        // sort the list of the freq, and remove from the smallest freq

        Map<Integer, Integer> map = new HashMap<>();
        for (int a : arr) {
            map.put(a, map.getOrDefault(a, 0) + 1);
        }

        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort((a, b) -> Integer.compare(a.getValue(), b.getValue()));
        int res = list.size();

        for (Map.Entry<Integer, Integer> entry : list) {
            int count = entry.getValue();
            while (k != 0) {
                k--;
                count--;
                if (count == 0) {
                    res--;
                    break;
                }
            }
            if (k == 0) {
                break;
            }
        }
        return res;
    }
}
