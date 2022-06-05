package bfs;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/keys-and-rooms/">...</a>
 */

public class KeysAndRooms {
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        Set<Integer> unlocked = new HashSet<>();
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(0);
        unlocked.add(0);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int key : rooms.get(cur)) {
                if (unlocked.add(key)) {
                    q.offer(key);
                }
            }
        }
        return unlocked.size() == rooms.size();
    }
    // time: O(n + e) -> n is num of rooms, e is total num of keys
    // space: O(n) -> if in a room there is n keys
}
