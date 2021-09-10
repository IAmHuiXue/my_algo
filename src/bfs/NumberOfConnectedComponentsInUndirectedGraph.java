package bfs;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/** https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/ */

public class NumberOfConnectedComponentsInUndirectedGraph {
    public int countComponents(int n, int[][] edges) {
        // build graph
        List<List<Integer>> graph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            int a = edge[0];
            int b = edge[1];
            graph.get(a).add(b);
            graph.get(b).add(a);
        }

        int count = 0;
        // Queue<Integer> q = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                visited[i] = true;
                bfs(i, graph, visited);
            }
        }
        return count;
    }

    private void bfs(int curNode, List<List<Integer>>  graph, boolean[] visited) {
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(curNode);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nei : graph.get(cur)) {
                if (!visited[nei]) {
                    visited[nei] = true;
                    q.offer(nei);
                }
            }
        }
    }
}
