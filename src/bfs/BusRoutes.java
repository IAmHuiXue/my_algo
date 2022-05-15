package bfs;

import java.util.*;

/** https://leetcode.com/problems/bus-routes/ */
public class BusRoutes {
    public int numBusesToDestination(int[][] routes, int source, int target) {
        // in this problem, a bus is unique equivalent to a route
        // 0th ~ (n-1)th bus
        int n = routes.length;
        // routes[i] represents the routes for i-th bus
        // <key=stop, value=set of buses that stop heret >
        Map<Integer, Set<Integer>> to_routes = new HashMap<>();
        for (int i = 0; i < routes.length; i++) {
            for (int j : routes[i]) {
                to_routes.computeIfAbsent(j, k -> new HashSet<>()).add(i);
            }
        }
        // int[]{stop, numTravel}
        Queue<int[]> q = new ArrayDeque<>();
        q.offer(new int[]{source, 0});
        HashSet<Integer> stopVisited = new HashSet<>(); // record the stops visited
        stopVisited.add(source);
        boolean[] busTaken = new boolean[n]; // record the bus taken
        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int stop = cur[0];
            int numTravel = cur[1];
            if (stop == target) {
                return numTravel;
            }
            for (int i : to_routes.get(stop)) {
                if (busTaken[i]) {
                    continue;
                }
                for (int j : routes[i]) {
                    if (stopVisited.add(j)) {
                        q.offer(new int[]{j, numTravel + 1});
                    }
                }
                busTaken[i] = true;
            }
        }
        return -1;
    }
}
