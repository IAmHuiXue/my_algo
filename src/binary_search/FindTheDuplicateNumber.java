package binary_search;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 */

public class FindTheDuplicateNumber {
    static class Sort {
        public int findDuplicate(int[] nums) {
            Arrays.sort(nums);
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    return nums[i];
                }
            }
            return -1;
        }
        // time: o(nlog(n))
    }

    static class BS {

        // [3, 1, 3, 4, 2]

        // in the example above, integer range is [1, len - 1] -> [1, 4]
        // the duplicate num is the smallest num such that the count of numbers less than or equal
        // to it is greater than the number itself

        //   value  [1, 2, 3, 4]
        //    count 1, 2, 4, 5 -> num3 is the one

        public int findDuplicate(int[] nums) {
            // 'low' and 'high' represent the range of values of the target
            int low = 1, high = nums.length - 1;
            int duplicate = -1;

            while (low <= high) {
                int cur = low + (high - low) / 2;

                // Count how many numbers in 'nums' are less than or equal to 'cur'
                int count = 0;
                for (int num : nums) { //n
                    if (num <= cur)
                        count++;
                }

                if (count > cur) {
                    duplicate = cur;
                    high = cur - 1;
                } else {
                    low = cur + 1;
                }
            }
            return duplicate;
        }
        // time: o(nlog(n))
    }
}
