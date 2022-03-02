package sliding_window.trick;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarrays-with-k-different-integers/
 */

public class SubarraysWithKDifferentIntegers {

    /**
     * 难点： 把 exactly k 转化成 atMost(k) - atMost(k-1)
     */

    public int subarraysWithKDistinct(int[] nums, int K) {
        return atMostK(nums, K) - atMostK(nums, K - 1);
    }

    private int atMostK(int[] nums, int K) {
        int left = 0;
        int res = 0;
        int count = 0;
        Map<Integer, Integer> map = new HashMap<>();
        for (int right = 0; right < nums.length; right++) {
            int freq = map.getOrDefault(nums[right], 0);
            map.put(nums[right], freq + 1);
            if (freq == 0) {
                count++;
            }

            while (count > K) {
                freq = map.get(nums[left]);
                map.put(nums[left++], freq - 1);
                if (freq == 1) {
                    count--;
                }
            }
            // 有 k, k-1, k-2 ... 个 unique integers
            res += right - left + 1; // 高斯算法展开式求和
        }
        return res;
    }
}
