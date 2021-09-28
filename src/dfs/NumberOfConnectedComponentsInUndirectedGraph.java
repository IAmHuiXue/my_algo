package dfs;

import java.util.ArrayList;
import java.util.List;

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
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                count++;
                dfs(i, graph, visited);
            }
        }
        return count;
    }

    private void dfs(int curNode, List<List<Integer>> graph, boolean[] visited) {
        visited[curNode] = true;
        for (int nei : graph.get(curNode)) {
            if (!visited[nei]) {
                dfs(nei, graph, visited);
            }
        }
    }
}
