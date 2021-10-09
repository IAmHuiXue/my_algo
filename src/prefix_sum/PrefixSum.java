package prefix_sum;

public class PrefixSum {
    public static int[] prefixSum(int[] nums) {
        // prefixSum[i] represents the prefixSum to index i including i
        int[] prefixSum = new int[nums.length];
        prefixSum[0] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i];
        }
        return prefixSum;
    }
    // time: O(n)
}
