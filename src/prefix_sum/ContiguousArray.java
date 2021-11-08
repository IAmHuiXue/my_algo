package prefix_sum;

import java.util.HashMap;
import java.util.Map;

/** https://leetcode.com/problems/contiguous-array/ */

public class ContiguousArray {
    public int findMaxLength(int[] nums) {
        // 巧解： 把 0 翻成 -1，当 sliding window 的和为0， 那么此时的 subarray 就是一个 candidate 解
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                nums[i] = -1;
            }
        }

        int res = 0, prefix = 0;
        // key=prefix, value=index
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1); // 为了不错过第一个 element
        for (int i = 0; i < nums.length; i++) {
            prefix += nums[i]; // 两个 prefix sum == 0， 所以中间夹的部分一定和为 0
            if (map.containsKey(prefix)) {
                res = Math.max(res, i - map.get(prefix));
            } else {
                // we put it only if there is not this prefix in the map
                // because if there is one, it must be the leftmost one compared with the current one,
                // and it could potentially form a longer result
                map.put(prefix, i);
            }
        }
        return res;
    }
}
