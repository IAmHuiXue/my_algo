package interval_sweep_line;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/meeting-rooms-ii/">...</a>
 */

public class MeetingRoomsII {
    static class PQ {
        public int minMeetingRooms(int[][] intervals) {
            if (intervals.length == 0) {
                return 0;
            }
            // pq 存的是所有 meeting room 的结束时间，最早结束的时间为 pq 栈顶元素, pq 的 size 就是当前时间下需要开设的最少 room 数
            PriorityQueue<Integer> allocator = new PriorityQueue<>();
            // Sort the intervals by start time
            Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
            // Add the end time of the first meeting
            allocator.offer(intervals[0][1]);
            // Iterate over remaining intervals
            for (int i = 1; i < intervals.length; i++) {
                // If the room due to free up the earliest is free, assign that room to this meeting.
                if (intervals[i][0] >= allocator.peek()) {
                    allocator.poll();
                }
                // If a new room is to be assigned, then also we add to the heap,
                // If an old room is allocated, then also we have to add to the heap with updated end time.
                allocator.offer(intervals[i][1]);
            }
            // The size of the heap tells us the minimum rooms required for all the meetings.
            return allocator.size();
        }
    }

    static class ClassicSweepLine {
        public int minMeetingRooms(int[][] intervals) {
            List<int[]> list = new ArrayList<>();
            for (int[] i : intervals) {
                list.add(new int[]{i[0], 1});
                list.add(new int[]{i[1], -1});
            }
            // Key: if a[0] == b[0], we need to return a[1] - b[1] not b[1] - a[1]!
            // Due to the logic, if a meeting ends at time i and another meeting starts at time i, we do not need to open
            // a new meeting room. In this case we should firstly calculate the end time before counting the start time
            list.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            // 当两个 meeting 有 overlap，那么就要开设一个新的 room
            // 所以 count 这里记录的是当下时刻的 overlap 数，也就是当下需要开设的 room 数
            int max = 0;
            int count = 0;
            for (int[] i : list) {
                if (i[1] > 0) {
                    count++;
                    max = Math.max(count, max);
                } else {
                    count--;
                }
            }
            return max;
        }
    }

    static class FancySweepLine {
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
