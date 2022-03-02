package binary_search.trick;

/** https://leetcode.com/problems/split-array-largest-sum/ */

public class SplitArrayLargestSum {
    public int splitArray(int[] nums, int m) {
        // find the possible smallest sum and largest sum
        // perform bs within the range
        int sum = 0, max = 0;
        for (int num : nums) {
            sum += num;
            max = Math.max(max, num);
        }
        return binary(nums, m, sum, max);
    }

    private int binary(int[] nums, int m, int high, int low) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (valid(nums, m, mid)) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean valid(int[] nums, int m, int subArraySum) {
        int curSum = 0;
        int count = 1;
        for (int num : nums) {
            curSum += num;
            if (curSum > subArraySum) {
                curSum = num;
                count++;
                if (count > m) {
                    return false;
                }
            }
        }
        return true;
    }
}
