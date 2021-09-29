package bfs;

import java.util.*;

/**
 * https://leetcode.com/problems/keys-and-rooms/
 */

public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> unlocked = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        unlocked.add(0);
        while (!q.isEmpty()) {
            int cur = q.poll();
            List<Integer> keys = rooms.get(cur);
            if (keys != null) { // there could be none in a room
                for (int key : keys) {
                    if (unlocked.add(key)) {
                        q.offer(key);
                    }
                }
            }
        }
        return unlocked.size() == rooms.size();
    }
    // time: O(n + e) -> n is num of rooms, e is total num of keys
    // space: O(n) -> if in a room there is n keys
}
