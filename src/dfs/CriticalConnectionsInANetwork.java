package dfs;

import javafx.util.Pair;

import java.util.*;

/**
 * https://leetcode.com/problems/critical-connections-in-a-network/
 */

public class CriticalConnectionsInANetwork {

    // An edge is a critical connection, if and only if it is not in a cycle.

    /*
        Edges that are not a part of the cycle end up being a single route from getting from one part
        of the graph to the other. Why, you ask? Well if there were multiple ways, then our edge would be a part
        of the cycle considering this is an undirected graph. Thus, edges not belonging to any cycle end up being
        a critical connection for the graph.

        Thus, the problem simply boils down to finding all the cycles in the graph and discarding all the edges
        belonging to such cycles. If we do that, we will only be left with edges that are critical connections in the graph.

    */

    // We record the timestamp when we visit each node. For each node, we check every neighbor except its parent
    // and return the smallest timestamp in all its neighbors.
    // If this timestamp is strictly less than the node's timestamp, we know that this node is somehow in a cycle.
    // Otherwise, this edge from the parent to this node is a critical connection

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        List<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();

        for (List<Integer> oneConnection : connections) {
            graph[oneConnection.get(0)].add(oneConnection.get(1));
            graph[oneConnection.get(1)].add(oneConnection.get(0));
        }
        boolean[] visited = new boolean[n]; // to record the visit path
        int[] ranks = new int[n];
        List<List<Integer>> results = new ArrayList<>();
        criticalConnectionsUtil(graph, -1, 0, 0, visited, results, ranks);
        return results;
    }


    public void criticalConnectionsUtil(List<Integer>[] graph, int parent, int curNode, int curRank, boolean[] visited,
                                        List<List<Integer>> results, int[] ranks) {
//        if (visited[curNode]) {
//            return;
//        }

        visited[curNode] = true;
        ranks[curNode] = curRank;

        for (int nei : graph[curNode]) {
            if (nei == parent) {
                continue;
            }
            if (!visited[nei]) {
                criticalConnectionsUtil(graph, curNode, nei, curRank + 1, visited, results, ranks);
            }
            if (curRank < ranks[nei]) {
                results.add(Arrays.asList(curNode, nei));
            }
            // do not forget to update ranks[curNode] to the minimum
            ranks[curNode] = Math.min(ranks[curNode], ranks[nei]);
        }
    }


//    private List<List<Integer>> ans = new ArrayList<>();
//
//    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
//        Map<Integer, List<Integer>> graph = new HashMap<>();
//        for (List<Integer> c : connections) {
//            graph.computeIfAbsent(c.get(0), (k -> new ArrayList<>())).add(c.get(1));
//            graph.computeIfAbsent(c.get(1), (k -> new ArrayList<>())).add(c.get(0));
//        }
//        int[] timestamps = new int[n];
//        dfs(graph, 0, 0, 1, timestamps);
//        return ans;
//    }
//
//    private int dfs(Map<Integer, List<Integer>> graph, int curr, int parent, int currTimestamp, int[] timestamps) {
//        timestamps[curr] = currTimestamp;
//        for (int nextNode : graph.getOrDefault(curr, new ArrayList<>())) {
//            if (nextNode == parent) continue;
//            if (timestamps[nextNode] > 0) timestamps[curr] = Math.min(timestamps[curr], timestamps[nextNode]);
//            else timestamps[curr] = Math.min(timestamps[curr], dfs(graph, nextNode, curr, currTimestamp + 1, timestamps));
//            if (currTimestamp < timestamps[nextNode]) ans.add(Arrays.asList(curr, nextNode));
//        }
//        return timestamps[curr];
//    }

    static class LCSolution {
        private Map<Integer, List<Integer>> graph;
        private Map<Integer, Integer> rank;
        private Map<Pair<Integer, Integer>, Boolean> connDict;

        public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {

            this.formGraph(n, connections);
            this.dfs(0, 0);

            List<List<Integer>> result = new ArrayList<>();
            for (Pair<Integer, Integer> criticalConnection : this.connDict.keySet()) {
                result.add(Arrays.asList(criticalConnection.getKey(), criticalConnection.getValue()));
            }

            return result;
        }

        private int dfs(int node, int discoveryRank) {

            // That means this node is already visited. We simply return the rank.
            if (this.rank.get(node) != null) {
                return this.rank.get(node);
            }

            // Update the rank of this node.
            this.rank.put(node, discoveryRank);

            // This is the max we have seen till now. So we start with this instead of INT_MAX or something.
            int minRank = discoveryRank + 1;

            for (Integer neighbor : this.graph.get(node)) {

                // Skip the parent.
                Integer neighRank = this.rank.get(neighbor);
                if (neighRank != null && neighRank == discoveryRank - 1) {
                    continue;
                }

                // Recurse on the neighbor.
                int recursiveRank = this.dfs(neighbor, discoveryRank + 1);

                // Step 1, check if this edge needs to be discarded.
                if (recursiveRank <= discoveryRank) {
                    int sortedU = Math.min(node, neighbor), sortedV = Math.max(node, neighbor);
                    this.connDict.remove(new Pair<>(sortedU, sortedV));
                }

                // Step 2, update the minRank if needed.
                minRank = Math.min(minRank, recursiveRank);
            }

            return minRank;
        }

        private void formGraph(int n, List<List<Integer>> connections) {

            this.graph = new HashMap<>();
            this.rank = new HashMap<>();
            this.connDict = new HashMap<>();

            // Default rank for unvisited nodes is "null"
            for (int i = 0; i < n; i++) {
                this.graph.put(i, new ArrayList<>());
                this.rank.put(i, null);
            }

            for (List<Integer> edge : connections) {

                // Bidirectional edges
                int u = edge.get(0), v = edge.get(1);
                this.graph.get(u).add(v);
                this.graph.get(v).add(u);

                int sortedU = Math.min(u, v), sortedV = Math.max(u, v);
                connDict.put(new Pair<>(sortedU, sortedV), true);
            }
        }
    }

}
