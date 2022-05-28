package two_sum;

import java.util.Arrays;

/** <a href="https://leetcode.com/problems/3sum-closest/">...</a> */

public class ThreeSumClosest {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int result = nums[0] + nums[1] + nums[2];
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int cur = nums[j] + nums[k] + nums[i];
                // update result at each step
                if (Math.abs(target - cur) < Math.abs(target - result)) {
                    result = cur;
                }
                if (cur < target) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
    }
}
