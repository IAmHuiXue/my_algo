package prefix_sum;

public class PrefixSum {
    public static int[] prefixSum(int[] nums) {
        // prefix[i] is the sum of all numbers [0, i].
        // prefix[i] - prefix[j] represents the sum of the subarray starting at i + 1 and ending at j
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        return prefixSum;
    }
    // time: O(n)
}
