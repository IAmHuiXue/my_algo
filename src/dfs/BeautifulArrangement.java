package dfs;

import util.Swap;

/** <a href="https://leetcode.com/problems/beautiful-arrangement/">...</a> */

public class BeautifulArrangement {
    public int countArrangement(int n) {
        if (n == 1) {
            return 1;
        }
        int[] nums = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            nums[i] = i;
        }
        int[] res = new int[1];
        dfs(nums, res, 1);
        return res[0];
    }

    private void dfs(int[] nums, int[] res, int index) {
        if (index == nums.length) {
            res[0]++;
            return;
        }
        for (int i = index; i < nums.length; i++) {
            Swap.swap(nums, i, index);
            if (nums[index] % index == 0 || index % nums[index] == 0) {
                dfs(nums, res, index + 1);
            }
            Swap.swap(nums, i, index);
        }
    }
}
