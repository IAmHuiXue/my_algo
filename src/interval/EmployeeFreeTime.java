package interval;

import java.util.ArrayList;
import java.util.List;

/** https://leetcode.com/problems/employee-free-time/ */

public class EmployeeFreeTime {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> total = new ArrayList<>();
        for (List<Interval> s : schedule) {
            total.addAll(s);
        }
        total.sort((a, b) -> {
            int diff = Integer.compare(a.start, b.start);
            if (diff != 0) {
                return diff;
            }
            return Integer.compare(a.end, b.end);
        });
        // merge intervals to get the busy times
        List<Interval> merge = new ArrayList<>();
        for (Interval cur : total) {
            if (merge.isEmpty()) {
                merge.add(cur);
                continue;
            }
            Interval lastAdded = merge.get(merge.size() - 1);
            if (lastAdded.end >= cur.start) {
                lastAdded.end = Math.max(lastAdded.end, cur.end);
            } else {
                merge.add(cur);
            }
        }
        // get the time slots between the adjacent busy times
        total.clear();
        for (int i = 1; i < merge.size(); i++) {
            total.add(new Interval(merge.get(i - 1).end, merge.get(i).start));
        }
        return total;
    }
}
