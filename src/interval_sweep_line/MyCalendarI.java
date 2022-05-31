package interval_sweep_line;

import java.util.ArrayList;
import java.util.List;

public class MyCalendarI {
    List<int[]> calendar;

    MyCalendarI() {
        calendar = new ArrayList<>();
    }

    public boolean book(int start, int end) {
        for (int[] iv: calendar) {
            /** 2 events do not conflict if and only if start1 >= end2 || start2 <= end1 */
            if (iv[0] < end && start < iv[1]) {
                return false;
            }
        }
        calendar.add(new int[]{start, end});
        return true;
    }
}
