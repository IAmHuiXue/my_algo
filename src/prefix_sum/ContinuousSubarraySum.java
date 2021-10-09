package prefix_sum;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/** https://leetcode.com/problems/continuous-subarray-sum/ */

public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        // 相同余数两次出现我们可以知道之间的 subarray 是可以被 k 整除的，因为余数没有变化
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // !
        int prefix = 0;

        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i];
            if (k != 0) {
                prefix %= k;
            }
            if (map.containsKey(prefix)) {
                if (i - map.get(prefix) > 1) {
                    return true;
                }
            } else {
                map.put(prefix, i);
            }
        }
        return false;
    }
}
