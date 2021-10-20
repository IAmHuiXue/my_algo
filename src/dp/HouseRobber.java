package dp;

/**  https://leetcode.com/problems/house-robber/*/

public class HouseRobber {
    public int rob(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int cur = i - 2 >= 0 ? nums[i] + dp[i - 2] : nums[i];
            dp[i] = Math.max(dp[i - 1], cur);
        }
        return dp[nums.length - 1];
    }
}
