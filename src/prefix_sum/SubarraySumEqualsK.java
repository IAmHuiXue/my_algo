package prefix_sum;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 */

public class SubarraySumEqualsK {
    static class PurePrefixSum {
        public int subarraySum(int[] nums, int k) {
            // prefixSum[i] represents the prefixSum to index i including i
            int[] prefixSum = PrefixSum.prefixSum(nums); // O(n)

            int count = 0;
            for (int j = 0; j < nums.length; j++) {
                for (int i = 0; i <= j; i++) {
                    if (prefixSum[j] - prefixSum[i] + nums[i] == k) {
                        count++;
                    }
                }
            }
            return count;
        }
        // time: O(n + n^2)
    }

    static class MemoPrefixSum {
        public int subarraySum(int[] nums, int k) {
            // to find the nums of the subarray's prefixSum where prefixSum(i, j) = k
            // to find a prefixSum(i, j), use prefixSum(j) - prefixSum(i)
            // convert to we are at j and have prefixSum(j), if there is a prefixSum(i) before us == prefixSum(j) - k
            // we store prefixSum and the frequency of each prefixSum value in a map

            // 从左往右，左边 scan 过记录过，所以 i, j 我们应该 对于 prefixSum(j) 找 一个 prefixSum(i) which = prefixSum(j) - k
            // 而不是反过来

            // <key=prefixSum, value=frequency>
            Map<Integer, Integer> map = new HashMap<>();
            map.put(0, 1); // !
            int prefixSum = 0, count = 0;
            for (int num : nums) {
                prefixSum += num;
                int freq = map.getOrDefault(prefixSum - k, 0);
                count += freq;
                // search first then put, to avoiding accidentally counting itself
                map.put(prefixSum, map.getOrDefault(prefixSum, 0) + 1);
            }
            return count;
        }
        // time: O(n)
    }
}
