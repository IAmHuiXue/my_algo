package bfs;

import java.util.*;

/** https://app.laicode.io/app/problem/497?plan=25 */

public class GraphValidTree {
    /**
    Given n nodes labeled from 0 to n - 1 and a list of undirected edges (each edge is a pair of nodes),
    write a function to check whether these edges make up a valid tree.
    <p>
    Note: you can assume that no duplicate edges will appear in edges. Since all edges are undirected,
    [0, 1] is the same as [1, 0]and thus will not appear together in edges.
     */

    public boolean validTree(int n, int[][] edges) {

        /* a tree is an undirected graph in which any two vertices are connected by exactly one path.
          In other words, any connected graph without simple cycles is a tree */

        // 1. no cycle
        // 2. node from 0 to n - 1 should be all connected

        if (edges.length == 0) {
            return n == 1;
        }

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        int created = 0;
        for (int[] edge : edges) {
            int one = edge[0];
            int two = edge[1];
            if (graph.get(one).size() == 0) {
                created++;
            }
            graph.get(one).add(two);
            if (graph.get(two).size() == 0) {
                created++;
            }
            graph.get(two).add(one);
        }
        // there should not be a node that does not have any edges
        if (created != n) {
            return false;
        }

        // BFS
        Queue<Integer> q = new ArrayDeque<>();

        // need 2 more containers
        // 1. a set to record visited nodes
        // 2. a container to record the parent of a node
        // a hashmap can satisfy both requirements

        // key=visitedNode, value=visitedFrom
        Map<Integer, Integer> visited = new HashMap<>();
        visited.put(0, -1);
        q.offer(0);
        while (!q.isEmpty()) {
            int cur = q.poll();
            for (int nei : graph.get(cur)) {
                if (!visited.containsKey(nei)) {
                    q.offer(nei);
                    visited.put(nei, cur);
                } else if (nei != visited.get(cur)) { // if nei has been visited but not the parent of cur
                    return false;
                }
            }
        }
        // make sure all the nodes have been traversed -> no isolated components
        return visited.size() == n;
    }
}
