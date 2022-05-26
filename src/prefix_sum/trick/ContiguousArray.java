package prefix_sum.trick;

import java.util.HashMap;
import java.util.Map;

/** <a href="https://leetcode.com/problems/contiguous-array/">https://leetcode.com/problems/contiguous-array/</a> */

public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        // 巧解： 把 0 翻成 -1，当 sliding window 的和为0， 那么此时的 subarray 就是一个 candidate 解
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        int res = 0, prefixSum = 0;
        // key=prefixSum, value=index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // 为了不错过第一个 element
        for (int i = 0; i < nums.length; i++) {
            prefixSum += nums[i];
            if (map.containsKey(prefixSum)) {
                res = Math.max(res, i - map.get(prefixSum));
            } else {
                // we put it only if there is not this prefixSum in the map
                // because if there is one, it must be the leftmost one compared with the current one,
                // and it could potentially form a longer result
                map.put(prefixSum, i);
            }
        }
        return res;
    }
}
