package dp;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/maximum-profit-in-job-scheduling/
 */

public class MaximumProfitInJobScheduling {
    static class TopDownMemo {
//        If we sort jobs by start time, starting from job index cur = 0, we might either schedule the jobs[cur] or not.
//        If we schedule jobs[cur], the problem becomes profit of jobs[cur] + max profit of scheduling jobs starting from next available job index.
//        If we don't schedule jobs[cur], the problem becomes max profit of scheduling jobs starting from cur + 1.
//        We choose the one giving more profits.
//        After observation, there are overlapped sub-problems, so we can utilize either memoization or bottom-up DP.

        // key=job, value=profit including this job
        private Map<Integer, Integer> memo;

        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int[][] jobs = new int[startTime.length][3];
            for (int i = 0; i < startTime.length; i++) {
                jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
            }
            Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
            memo = new HashMap<>();
            return getProfitStartingFrom(0, jobs);
        }

        private int getProfitStartingFrom(int cur, int[][] jobs) {
            if (cur == jobs.length) {
                return 0;
            }

            if (memo.containsKey(cur)) {
                return memo.get(cur);
            }

            int next = findNext(cur, jobs);
            int inclProf = jobs[cur][2] + (next == -1 ? 0 : getProfitStartingFrom(next, jobs));
            int exclProf = getProfitStartingFrom(cur + 1, jobs);

            memo.put(cur, Math.max(inclProf, exclProf));
            return Math.max(inclProf, exclProf);
        }
    }

    static class BottomUp {
        public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
            int[][] jobs = new int[startTime.length][3];
            for (int i = 0; i < startTime.length; i++) {
                jobs[i] = new int[]{startTime[i], endTime[i], profit[i]};
            }
            Arrays.sort(jobs, (a, b) -> a[0] - b[0]);

            // dp[i] represents maxProfit for the jobs starting from i
            int[] dp = new int[jobs.length];
            dp[jobs.length - 1] = jobs[jobs.length - 1][2];
            for (int cur = jobs.length - 2; cur >= 0; cur--) {
                int next = findNext(cur, jobs);
                dp[cur] = Math.max(
                        jobs[cur][2] + (next == -1 ? 0 : dp[next]),
                        dp[cur + 1]
                );
            }
            return dp[0];
        }
    }

    private static int findNext(int cur, int[][] jobs) {
        for (int next = cur + 1; next < jobs.length; next++) {
            if (jobs[next][0] >= jobs[cur][1]) {
                return next;
            }
        }
        return -1;
    }
}
