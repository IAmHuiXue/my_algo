package two_sum;

import java.util.Arrays;

/** https://leetcode.com/problems/3sum-closest/ */

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Integer result = null;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int cur = nums[j] + nums[k] + nums[i];
                if (cur == target) {
                    return cur;
                } else if (cur < target) {
                    j++;
                } else {
                    k--;
                }
                if (result == null) {
                    result = cur;
                } else if (Math.abs(target - cur) < Math.abs(target - result)) {
                    result = cur;
                }
            }
        }
        return result;
    }
}
