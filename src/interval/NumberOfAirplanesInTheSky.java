package interval;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://www.lintcode.com/problem/391/
 */

public class NumberOfAirplanesInTheSky {

    static class Interval {
        int start;
        int end;

        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public int countOfAirplanesBetter(List<Interval> airplanes) {

        List<int[]> list = new ArrayList<>();
        for (Interval interval : airplanes) {
            list.add(new int[]{interval.start, 1}); // 1 represents takeoff
            list.add(new int[]{interval.end, -1}); // -1 represents land
        }

        Collections.sort(list, (a, b) -> a[0] == b[0] ? Integer.compare(a[1], b[1]) : Integer.compare(a[0], b[0]));
        int max = 0;
        int count = 0;
        for (int[] point : list) {
            if (point[1] == 1) {
                count++;
                max = Math.max(max, count);
            } else {
                count--;
            }
        }
        return max;
    }

    public int countOfAirplanes(List<Interval> airplanes) {

        // 把所有的 start 和 end 分别放进两个数组
        // 分别 排序
        // 从第一个 start 开始统计
            // 碰到 start 就 +1
            // 碰到 end 就 -1
        // 同时维护一个 max

        List<Integer> starts = new ArrayList<>();
        List<Integer> ends = new ArrayList<>();
        for (Interval interval : airplanes) {
            starts.add(interval.start);
            ends.add(interval.end);
        }
        Collections.sort(starts);
        Collections.sort(ends);
        int count = 0;
        int curCount = 0;
        int i = 0, j = 0;
        while (i < starts.size()) {
            if (starts.get(i) < ends.get(j)) {
                i++;
                curCount++;
                count = Math.max(count, curCount);
            } else if (starts.get(i) > ends.get(j)) {
                j++;
                curCount--;
            } else {
                i++;
                j++;
            }
        }
        return count;
    }
}
