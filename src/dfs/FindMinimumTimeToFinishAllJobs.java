package dfs;

import java.util.Arrays;

/** https://leetcode.com/problems/find-minimum-time-to-finish-all-jobs/ */

public class FindMinimumTimeToFinishAllJobs {

    // 模型就是 subset
    // level 指 每一个 job
    // branches 指对于每一个 job 来说，有 k 种分配的选择

    // 然后在 dfs 过程中，通过 sort，early return，de-dup 来优化剪枝


    public int minimumTimeRequired(int[] jobs, int k) {
        Arrays.sort(jobs);
        int[] result = new int[]{Integer.MAX_VALUE}; // 剪枝：descending order 开始 dfs

        // trick -> int[i] -> represents the total working hours for worker i
        dfs(jobs, jobs.length - 1, result, new int[k]);
        return result[0];
    }

    private void dfs(int[] jobs, int index, int[] result, int[] sums) {
        int curMinSum = Integer.MIN_VALUE;
        for (int curSum : sums) {
            curMinSum = Math.max(curMinSum, curSum);
        }

        if (curMinSum >= result[0]) { // 剪枝：当前结果如果已经大于 global，停止 go further
            return;
        }

        if (index < 0) {
            result[0] = curMinSum;
            return;
        }

        for (int i = 0; i < sums.length; i++) { // 剪枝：当工人当前 working hours 一样的时候，避免重复选择 -> like de-dup in subset
            while (i < sums.length - 1 && sums[i] == sums[i + 1]) {
                i++;
            }
            sums[i] += jobs[index];
            dfs(jobs, index - 1, result, sums);
            sums[i] -= jobs[index];
        }
    }
    // time: O(nlog(n) for sort + k^n for dfs)
    // space: O(k for sums array + n for call stack)
}
