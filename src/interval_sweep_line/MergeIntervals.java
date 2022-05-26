package interval_sweep_line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** <a href="https://leetcode.com/problems/merge-intervals/">...</a> */
public class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        // SORT the intervals by the starting value of each interval
        // no need to worry about the ending values

        // 因为 merge 会导致最终的总个数变化，所以创建一个 list<int[]> 来辅助，最后再 convert 成 in[][]
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty()) {
                merged.add(interval);
            } else {
                // check cur interval and the previously-added interval
                int[] lastAdded = merged.get(merged.size() - 1);
                if (lastAdded[1] < interval[0]) { // no overlap
                    merged.add(interval);
                } else {
                    // like [1, 3], [2, 4] -> [1, 4] vs [1, 4], [2, 3] -> [1, 4]
                    lastAdded[1] = Math.max(lastAdded[1], interval[1]);
                }
            }
        }
        return merged.toArray(new int[0][]);
    }
    // time: O(nlog(n))
    // space: O(sort)
}
