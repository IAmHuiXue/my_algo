package graph;

import java.util.*;

/** https://leetcode.com/problems/redundant-connection/ */

public class RedundantConnection {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        Map<Integer, List<Integer>> map = new HashMap<>();
        for (int i = 1; i <= n; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int[] edge : edges) {
            // we check cycle as we build a map
            // before we add relationship between edge[0] and edge[1], if we run dfs and find out
            // edge[0] can already visit edge[1], this edge must be the answer, and it is the last one
            if (dfs(new HashSet<>(), map, edge[0], edge[1])) {
                return edge;
            }
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }

        return new int[]{-1,-1};
    }

    private boolean dfs(Set<Integer> visited, Map<Integer, List<Integer>> map,
                        int src, int target) {
        if (src == target) {
            return true;
        }
        visited.add(src);
        for (int next : map.get(src)) {
            if (!visited.contains(next)) {
                if (dfs(visited, map, next, target)) {
                    return true;
                }
            }
        }
        return false;
    }
}
