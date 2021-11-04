package interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** https://leetcode.com/problems/remove-interval/ */
public class RemoveInterval {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] cur : intervals) {
            // cur 和 interval 没有重合
            if (cur[1] <= toBeRemoved[0] || cur[0] >= toBeRemoved[1]) {
                res.add(Arrays.asList(cur[0], cur[1]));
            } else {
                // cur[1] > toBeRemoved[0] && cur[0] < toBeRemoved[1]

                // 两个 if 不是 if-else 的关系，当 cur entirely covers toBeRemoved 的时候，两个 if
                // 条件都满足，这时候就需要保留 cur 的两端
                if (cur[0] < toBeRemoved[0])  { // left end extra
                    res.add(Arrays.asList(cur[0], toBeRemoved[0]));
                }
                if (cur[1] > toBeRemoved[1]) {
                    res.add(Arrays.asList(toBeRemoved[1], cur[1]));
                }
            }
        }
        return res;
    }
}
