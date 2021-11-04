package interval;

import java.util.*;

/**
 * https://leetcode.com/problems/meeting-rooms-ii/
 */

public class MeetingRoomsII {
    static class PQ {
        public int minMeetingRooms(int[][] intervals) {
            // Check for the base case. If there are no intervals, return 0
            if (intervals.length == 0) {
                return 0;
            }
            // pq 存的是所有 meeting room 的结束时间，最早结束的时间为 pq 栈顶元素
            PriorityQueue<Integer> allocator = new PriorityQueue<>();
            // Sort the intervals by start time
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            // Add the end time of the first meeting
            allocator.add(intervals[0][1]);
            // Iterate over remaining intervals
            for (int i = 1; i < intervals.length; i++) {
                // If the room due to free up the earliest is free, assign that room to this meeting.
                if (intervals[i][0] >= allocator.peek()) {
                    allocator.poll();
                }
                // If a new room is to be assigned, then also we add to the heap,
                // If an old room is allocated, then also we have to add to the heap with updated end time.
                allocator.add(intervals[i][1]);
            }
            // The size of the heap tells us the minimum rooms required for all the meetings.
            return allocator.size();
        }
    }

    static class CountEnds {
        // 就是数飞机的那道题。实际上是求最多 overlap 的个数
        public int minMeetingRooms(int[][] intervals) {
            List<Integer> starts = new ArrayList<>();
            List<Integer> ends = new ArrayList<>();
            for (int[] interval : intervals) {
                starts.add(interval[0]);
                ends.add(interval[1]);
            }
            Collections.sort(starts);
            Collections.sort(ends);
            int room = 0;
            int end = 0;
            for (Integer start : starts) {
                if (start < ends.get(end)) {
                    room++;
                } else {
                    end++;
                }
            }
            return room;
        }
    }

}
