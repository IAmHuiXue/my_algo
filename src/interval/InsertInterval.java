package interval;

import java.util.ArrayList;
import java.util.List;

/** https://leetcode.com/problems/insert-interval/ */

public class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        List<int[]> res = new ArrayList<>();
        for (int[] cur : intervals) {
            if (newInterval == null || cur[1] < newInterval[0]) {
                res.add(cur);
            } else if (cur[0] > newInterval[1]) {
                res.add(newInterval);
                res.add(cur);
                newInterval = null;
            } else {
                // now cur has overlap with newInterval
                // update newInterval
                newInterval[0] = Math.min(newInterval[0], cur[0]);
                newInterval[1] = Math.max(newInterval[1], cur[1]);
            }
        }
        // edge case -> newInterval should be added in the end
        if (newInterval != null) {
            res.add(newInterval);
        }
        return res.toArray(new int[0][]);
    }
}
