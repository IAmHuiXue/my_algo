package interval_sweep_line;

import java.util.ArrayList;
import java.util.List;

/** <a href="https://leetcode.com/problems/insert-interval/">...</a> */

public class InsertInterval {

    public int[][] insert(int[][] intervals, int[] newInterval) {
        // 因为 add 会导致最终的总个数变化，所以创建一个 list<int[]> 来辅助，最后再 convert 成 in[][]
        List<int[]> res = new ArrayList<>();

        // when we scan and visit an interval, there are a few cases
        // 1. interval.last < new.first -> pass interval
        // 2. interval.first > new.last -> find the right pos for new, add new and pass interval
        // 3. there are overlap between the two -> needs to merge based on the comparison of the two.

        for (int[] cur : intervals) {
            if (newInterval == null || cur[1] < newInterval[0]) {
                res.add(cur);
                continue;
            }
            if (cur[0] > newInterval[1]) {
                res.add(newInterval);
                res.add(cur);
                newInterval = null;
                continue;
            }
            // now cur has overlap with newInterval
            // update newInterval
            newInterval[0] = Math.min(newInterval[0], cur[0]);
            newInterval[1] = Math.max(newInterval[1], cur[1]);
            // do not add this updated newInterval yet, because we need to check if and the next interval
        }
        // edge case -> if in the end newInterval has not been added, it should then stay at the end.
        if (newInterval != null) {
            res.add(newInterval);
        }
        return res.toArray(new int[0][]);
    }
}
