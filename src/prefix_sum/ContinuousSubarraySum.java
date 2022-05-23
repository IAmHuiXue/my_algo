package prefix_sum;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * <a href="https://leetcode.com/problems/continuous-subarray-sum/">https://leetcode.com/problems/continuous-subarray-sum/</a>
 */

public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        // (p[j] - p[i]) % k = 0 -> p[j] % k = p[i] % k
        // 相同余数两次出现我们可以知道之间的 subarray 是可以被 k 整除的，因为余数没有变化
        Map<Integer, Integer> map = new HashMap<>();
        // <MOD, index>
        map.put(0, -1);
        int prefixSum = 0;
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            // k > 0
            int mod = prefixSum % k;
            Integer index = map.get(mod);
            if (index != null && i - index > 1) {
                // cover the edge case like [0] & 1
                return true;
            }
            // we want to keep the far-most pair to avoid the length of elements are less than 2
            map.putIfAbsent(mod, i);
        }
        return false;
    }
}
