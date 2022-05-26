package interval_sweep_line.trick;

import java.util.Arrays;

/** <a href="https://leetcode.com/problems/non-overlapping-intervals/">...</a> */

public class NonOverlappingIntervals {
    public int eraseOverlapIntervals(int[][] intervals) {
        // sort by the end time!
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[1], b[1]));
        int count = 0, prev = Integer.MIN_VALUE;
        for (int[] interval : intervals) {
            if (prev <= interval[0]) {
                prev = interval[1];
                continue;
            }
            // greedy, when prev has overlapping with cur, always remove prev
            count++;
        }
        return count;
    }
}
