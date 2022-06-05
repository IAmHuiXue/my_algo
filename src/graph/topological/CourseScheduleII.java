package graph.topological;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/course-schedule-ii/">...</a>
 */

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = buildGraph(numCourses, prerequisites);
        int[] incomingEdges = buildIncomingEdges(numCourses, prerequisites);
        return topological(graph, incomingEdges);
    }

    Map<Integer, List<Integer>> buildGraph(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            graph.put(i, new ArrayList<>());
        }
        for (int[] pair : prerequisites) {
            graph.get(pair[1]).add(pair[0]);
        }
        return graph;
    }

    int[] buildIncomingEdges(int numCourses, int[][] prerequisites) {
        int[] incomingEdges = new int[numCourses];
        for (int[] pair : prerequisites) {
            incomingEdges[pair[0]]++;
        }
        return incomingEdges;
    }

    int[] topological(Map<Integer, List<Integer>> graph, int[] incomingEdges) {
        Queue<Integer> q = new ArrayDeque<>();
        for (int i = 0; i < incomingEdges.length; i++) {
            if (incomingEdges[i] == 0) {
                q.offer(i);
            }
        }
        int[] res = new int[incomingEdges.length];
        int index = 0;
        while (!q.isEmpty()) {
            int cur = q.poll();
            res[index++] = cur;
            for (int nei : graph.getOrDefault(cur, new ArrayList<>())) {
                if (--incomingEdges[nei] == 0) {
                    q.offer(nei);
                }
            }
        }
        return index == res.length ? res : new int[0];
    }
}
