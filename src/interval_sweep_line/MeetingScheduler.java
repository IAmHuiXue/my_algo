package interval_sweep_line;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** <a href="https://leetcode.com/problems/meeting-scheduler/">...</a> */

public class MeetingScheduler {
    public List<Integer> minAvailableDurationOptimized(int[][] slots1, int[][] slots2, int duration) {
        Arrays.sort(slots1, (a, b) -> a[0] - b[0]);
        Arrays.sort(slots2, (a, b) -> a[0] - b[0]);
        int i = 0, j = 0;
        while (i < slots1.length && j < slots2.length) {
            int intersectStart = Math.max(slots1[i][0], slots2[j][0]);
            int intersectEnd = Math.min(slots1[i][1], slots2[j][1]);
            if (intersectEnd - intersectStart >= duration) {
                return Arrays.asList(intersectStart, intersectStart + duration);
            }
            if (slots1[i][1] < slots2[j][1]) {
                i++;
            } else {
                j++;
            }
        }
        return new ArrayList<>();
    }
    public List<Integer> minAvailableDuration(int[][] slots1, int[][] slots2, int duration) {
        List<int[]> slots = new ArrayList<>();
        for (int[] s : slots1) {
            slots.add(s);
        }
        for (int[] s : slots2) {
            slots.add(s);
        }
        slots.sort((a, b) -> a[0] - b[0]);
        int[] prev = slots.get(0);
        for (int i = 1; i < slots.size(); i++) {
            int[] cur = slots.get(i);
            // no-overlap, skip
            if (prev[1] <= cur[0]) {
                prev = cur;
                continue;
            }
            // when overlap, intersection -> [max of the start time, min of the end time]
            List<Integer> res = Arrays.asList(Math.max(prev[0], cur[0]), Math.min(prev[1], cur[1]));
            if (res.get(1) - res.get(0) >= duration) {
                res.set(1, res.get(0) + duration);
                return res;
            }
            // if the intersection is shorter than the duration, leave the one which ends later
            prev = prev[1] > cur[1] ? prev : cur;
        }
        return new ArrayList<>();
    }
}
