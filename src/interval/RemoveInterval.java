package interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** https://leetcode.com/problems/remove-interval/ */
public class RemoveInterval {
    public List<List<Integer>> removeInterval(int[][] intervals, int[] toBeRemoved) {
        List<List<Integer>> res = new ArrayList<>();
        for (int[] cur : intervals) {
            if (cur[1] <= toBeRemoved[0] || cur[0] >= toBeRemoved[1]) {
                res.add(Arrays.asList(cur[0], cur[1]));
            } else {
                // cur[1] > toBeRemoved[0] && cur[0] < toBeRemoved[1]
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
