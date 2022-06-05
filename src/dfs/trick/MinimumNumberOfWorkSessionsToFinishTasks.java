package dfs.trick;

import java.util.Arrays;

/** <a href="https://leetcode.com/problems/minimum-number-of-work-sessions-to-finish-the-tasks/">...</a> */

public class MinimumNumberOfWorkSessionsToFinishTasks {
    public int minSessions(int[] tasks, int sessionTime) {
        Arrays.sort(tasks); // 剪枝
        int[] result = new int[]{tasks.length};
        // int[] sessions -> trick
        //  根据题意，max(tasks[i]) <= sessionTime <= 15，意味着 worst case 也不会超过 tasks.length 个 sessions
        // new int[tasks.length] 的 length 代表了一个 sessions 的 capacity
        // 通过 dfs 里面新增 session 后，每一个 sessions[i] 就代表对应 session 此时所花费的时间
        dfs(tasks, result, tasks.length - 1, sessionTime, new int[tasks.length], 0);
        return result[0];
    }

    private void dfs(int[] tasks, int[] result, int index, int sessionTime, int[] sessions, int sessionCount) {
        if (index < 0) {
            result[0] = Math.min(result[0], sessionCount);
            return;
        }

        if (sessionCount >= result[0]) { // 剪枝
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
