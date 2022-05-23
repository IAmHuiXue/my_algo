package prefix_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/subarray-sums-divisible-by-k/">https://leetcode.com/problems/subarray-sums-divisible-by-k/</a>
 */

public class SubarraySumsDivisibleByK {
    public int subarraysDivByK(int[] nums, int k) {
        // to find nums of a subarray sum where sum % k = 0
        // sum of array(i, j) -> prefixSum(j) - prefixSum(i)
        // if we store all the prefixSum as we traverse the array
        // then when we are at j and have prefixSum(j), we look for the records
        // of previous prefixSum to see if there is prefixSum(i) where ->
        // prefixSum(i) % k == prefixSum(j) % k

        // 比如 5 % 3 = 2， 8 % 3 = 2；
        // 所以 (8 - 5) % 3 = 0

        // <key=prefixSum, value=frequency>
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // !
        int prefixSum = 0, count = 0;
        for (int num : nums) {
            prefixSum = (prefixSum + num % k + k) % k; //为了避免 num < 0
            count += map.getOrDefault(prefixSum, 0);
            map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
        }
        return count;
    }
}
