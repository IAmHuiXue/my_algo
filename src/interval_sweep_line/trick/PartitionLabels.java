package interval_sweep_line.trick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** <a href="https://leetcode.com/problems/partition-labels/">...</a> */

public class PartitionLabels {
    public List<Integer> partitionLabels(String s) {
        // key=letter, value=[indexOfFirstOccurrence, indexOfLastOccurrence]
        Map<Character, int[]> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.get(c)[1] = i;
            } else {
                map.put(c, new int[]{i, i});
            }
        }
        List<int[]> list = new ArrayList<>(map.values());
        list.sort((a, b) -> Integer.compare(a[0], b[0]));
        List<int[]> mergedIntervals = new ArrayList<>();
        for (int[] cur : list) {
            if (mergedIntervals.isEmpty()) {
                mergedIntervals.add(cur);
                continue;
            }
            int[] last = mergedIntervals.get(mergedIntervals.size() - 1);
            if (last[1] <= cur[0]) {
                mergedIntervals.add(cur);
            } else {
                last[1] = Math.max(last[1], cur[1]);
            }
        }
        List<Integer> res = new ArrayList<>();
        for (int[] i : mergedIntervals) {
            res.add(i[1] - i[0] + 1);
        }
        return res;
    }
}
