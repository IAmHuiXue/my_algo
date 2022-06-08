package graph;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/critical-connections-in-a-network/">...</a>
 */

public class CriticalConnectionsInANetwork {

    /** Amazon OA
     *
     * this problem is to find all critical edges
     *
     * Amazon may have another problem to find all "cut node"
     *
     * SCC -> strongly connected components
     *
     * articulation points and bridges
     *
     * */

    // An edge is a critical connection, if and only if it is not in a cycle.

    /*
        Edges that are not a part of the cycle end up being a single route from getting from one part
        of the graph to the other. Why? Well if there were multiple ways, then our edge would be a part
        of the cycle considering this is an undirected graph. Thus, edges not belonging to any cycle end up being
        a critical connection for the graph.

        Thus, the problem simply boils down to finding all the cycles in the graph and discarding all the edges
        belonging to such cycles. If we do that, we will only be left with edges that are critical connections in the graph.

    */

    // We record the timestamp when we visit each node. For each node, we check every neighbor except its parent
    // and return the smallest timestamp in all its neighbors.
    // If this timestamp is strictly less than the node's timestamp, we know that this node is somehow in a cycle.
    // Otherwise, this edge from the parent to this node is a critical connection

    static class SimplifiedVersion {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> timeMap = new HashMap<>();
        List<List<Integer>> res = new ArrayList<>();

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> edges) {
            for (List<Integer> edge : edges) {
                graph.computeIfAbsent(edge.get(0), k -> new ArrayList<>()).add(edge.get(1));
                graph.computeIfAbsent(edge.get(1), k -> new ArrayList<>()).add(edge.get(0));
            }
            // need a timestamp, visited, graph, parent, curNode, curTime
            dfs(-1, 0, 0, new HashSet<>());
            return res;
        }
        private void dfs(int parent, int cur, int time, Set<Integer> visited) {
            visited.add(cur);
            timeMap.put(cur, time);

            for (int nei : graph.get(cur)) {
                // 1.
                if (nei == parent) {
                    continue;
                }
                // 2.
                if (!visited.contains(nei)) {
                    dfs(cur, nei, time + 1, visited);
                }
                // 3.
                timeMap.put(cur, Math.min(timeMap.get(cur), timeMap.get(nei)));
                // 4. here is time, not timeMap.get(cur) !
                if (time < timeMap.get(nei)) {
                    res.add(Arrays.asList(cur, nei));
                }
            }
        }
        // time: O(V + E) 每个节点入栈一次出栈一次，每条边访问一次
        // space: O(E)
    }
}
