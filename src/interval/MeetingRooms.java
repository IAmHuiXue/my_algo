package interval;

import java.util.Arrays;

/** https://leetcode.com/problems/meeting-rooms/ */

public class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (i1, i2) -> Integer.compare(i1[0], i2[0]));
        for (int i = 1; i < intervals.length; i++) {
            int[] cur = intervals[i];
            int[] prev = intervals[i - 1];
            if (cur[0] < prev[1]) {
                return false;
            }
        }
        return true;
    }
}
