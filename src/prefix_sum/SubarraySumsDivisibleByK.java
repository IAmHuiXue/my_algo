package prefix_sum;

import java.util.HashMap;
import java.util.Map;

/** https://leetcode.com/problems/subarray-sums-divisible-by-k/ */

public class SubarraySumsDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        // to find nums of a subarray sum where sum % k = 0
        // sum of array(i, j) -> prefix(j) - prefix(i)
        // if we store all the prefix as we traverse the array
        // then when we are at j and have prefix(j), we look for the records
        // of previous prefix to see if there is prefix(i) where ->
        // prefix(i) % k == prefix(j) % k !!!

        // <key=prefix, value=frequency>
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // !
        int prefix = 0, count = 0;
        for (int num : nums) {
            prefix = (prefix + num % k + k) % k; //为了避免 num < 0
            count += map.getOrDefault(prefix, 0);
            // search first then put, to avoiding accidentally counting itself
            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }
        return count;
    }
}
