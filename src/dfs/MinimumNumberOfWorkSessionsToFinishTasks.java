package dfs;

import java.util.Arrays;

/** https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/ */

public class MinimumNumberOfWorkSessionsToFinishTasks {
    public int minSessions(int[] tasks, int sessionTime) {
        // max(tasks[i]) <= sessionTime <= 15
        Arrays.sort(tasks); // 剪枝
        int[] result = new int[]{tasks.length};
        // int[] sessions -> trick
        dfs(tasks, result, tasks.length - 1, sessionTime, new int[tasks.length], 0);
        return result[0];
    }

    private void dfs(int[] tasks, int[] result, int index, int sessionTime, int[] sessions, int sessionCount) {
        if (sessionCount >= result[0]) { // 剪枝
            return;
        }

        if (index < 0) {
            result[0] = sessionCount;
            return;
        }

        for (int i = 0; i < sessionCount; i++) { // 剪枝
            // todo: 剪枝，当每个 session 目前的 hours 一样的时候，可以 de-dup
            if (sessions[i] + tasks[index] <= sessionTime) {
                sessions[i] += tasks[index];
                dfs(tasks, result, index - 1, sessionTime, sessions, sessionCount);
                sessions[i] -= tasks[index];
            }
        }
        // need a new session
        sessions[sessionCount] = tasks[index];
        dfs(tasks, result, index - 1, sessionTime, sessions, sessionCount + 1);
        sessions[sessionCount] = 0;
    }
}