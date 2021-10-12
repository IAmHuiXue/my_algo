package bfs;

import java.util.*;

/**
 * https://leetcode.com/problems/open-the-lock/
 */

public class OpenTheLock {
    public int openLock(String[] deadEnds, String target) {
        // a pattern of 4 digits represents as a graph node
        // starting from node 0000, BFS the entire graph until reaching target
        Set<String> deads = new HashSet<>(Arrays.asList(deadEnds));
        if (deads.contains("0000")) {
            return -1;
        }
        Queue<String> q = new ArrayDeque<>();
        q.offer("0000");
        Set<String> seen = new HashSet<>();
        seen.add("0000");
        int steps = 0;
        StringBuilder sb;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.equals(target)) {
                    return steps;
                }
                sb = new StringBuilder(cur);
                for (int j = 0; j < sb.length(); j++) {
                    char org = sb.charAt(j);
                    // 2 cases
                    char next = org == '9' ? '0' : (char) (org + 1);
                    sb.setCharAt(j, next);
                    String nxt = sb.toString();
                    if (!deads.contains(nxt) && !seen.contains(nxt)) {
                        q.offer(nxt);
                        seen.add(nxt);
                    }
                    char prev = org == '0' ? '9' : (char) (org - 1);
                    sb.setCharAt(j, prev);
                    String prv = sb.toString();
                    if (!deads.contains(prv) && !seen.contains(prv)) {
                        q.offer(prv);
                        seen.add(prv);
                    }
                    sb.setCharAt(j, org);
                }
            }
            steps++;
        }
        return -1;
    }
}
