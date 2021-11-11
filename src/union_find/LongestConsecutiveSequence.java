package union_find;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/longest-consecutive-sequence/
 */

public class LongestConsecutiveSequence {

    /*

If we regard a consecutive sequence as a connected component (or a disjoint set),
the problem becomes to get the size of the largest connected component (or set).

A node is a value of an element in nums in this case.
Two nodes are connected if they are consecutive.

O(N) time complexity is required, so given nums[i], we should tell the existence of the index of nums[i] - 1,
and nums[i] + 1 in O(1). That can be achieved using a map that maps value to index.

Please note that for duplicate elements, we count only once.

     */

    public int longestConsecutive(int[] nums) {
        DSUOptimizedWithSize dsu = new DSUOptimizedWithSize(nums.length);
        // Map val to index in nums
        Map<Integer, Integer> valToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (valToIndex.containsKey(nums[i])) { // DE-DUP
                continue;
            }

            if (valToIndex.containsKey(nums[i] - 1)) {
                dsu.union(i, valToIndex.get(nums[i] - 1));
            }

            if (valToIndex.containsKey(nums[i] + 1)) {
                dsu.union(i, valToIndex.get(nums[i] + 1));
            }

            valToIndex.put(nums[i], i);
        }

        return findMax(dsu);
    }

    private int findMax(DSUOptimizedWithSize dsu) {
        int maxSize = 0;
        for (int s : dsu.size) {
            maxSize = Math.max(maxSize, s);
        }
        return maxSize;
    }
}
