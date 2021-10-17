package two_sum;

import java.util.Arrays;

/** https://leetcode.com/problems/3sum-smaller/ */

public class ThreeSumSmaller {
    public int threeSumSmaller(int[] nums, int target) {
        int result = 0;
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            int j = i + 1;
            int k = nums.length - 1;
            while (j < k) {
                int cur = nums[j] + nums[k] + nums[i];
                if (cur >= target) {
                    k--;
                } else {
                    // if k is qualified, then k -> [j + 1, k] will also be qualified
                    result += k - j; // !
                    j++;
                }
            }
        }
        return result;
    }
}
