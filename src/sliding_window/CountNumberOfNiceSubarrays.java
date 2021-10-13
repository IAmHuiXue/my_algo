package sliding_window;

/** https://leetcode.com/problems/count-number-of-nice-subarrays/ */

public class CountNumberOfNiceSubarrays {
    public int numberOfSubarrays(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    public int atMost(int[] nums, int k) {
        int res = 0;
        int left = 0;
        int count = 0;
        for (int right = 0; right < nums.length; right++) {
            count += nums[right] % 2; // 只有奇数才会是 1
            while (count > k) {
                count -= nums[left++] % 2;
            }
            res += right - left + 1; // 高斯算法展开式求和
        }
        return res;
    }
}
