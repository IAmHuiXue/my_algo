package graph;

import java.util.*;

public class HasCycle {

    // directed graph 推荐使用 topological sort
    // undirected graph 推荐使用 bfs

    static class GraphNode {
        int key;
        List<GraphNode> neighbors;

        GraphNode(int key) {
            this.key = key;
            neighbors = new ArrayList<>();
        }
    }

    /**
     * 用拓扑排序可以查 有向图和无向图的成环情况，唯一区别是
     * 1. 建立 edges 的时候，有向图建立的是 incomingEdges，无向图建立的就是 edges
     * 2. 进 queue 的规则，有向图是 node 的 incomingEdges 是 0 的时候，而无向图是 node 的 edges <= 1 的时候
     */

    /** 用 DFS/BFS 可以查 有向图和无向图的成环情况，区别是
     * 1. 对于无向图，如果当前 node 存在 一个已经 visit 过的 neighbor node 且它不是 parent node，则有环 （避免 revisit parent node！）
     * 2. 对于有向图，
     */


    static class DAG {
        /*
                   3 <- 1 -> 2 -> 3 is a DAG (directed acyclic graph)
                   3 -> 1 -> 2 -> 3 is not a DAG (it has a cycle)
         */


        static class TopologicalSort {
            public static boolean hasCycle(int n, int[][] connections) {
                // here given n -> n nodes labeled from 0 - n - 1
                // connections[i] -> connections[i][0] points to connections[i][1]

                List<List<Integer>> graph = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    graph.add(new ArrayList<>());
                }
                int[] incomingEdges = new int[n];
                for (int[] edge : connections) {
                    int from = edge[0];
                    int to = edge[1];
                    graph.get(from).add(to);
                    incomingEdges[to]++;
                }

                Queue<Integer> q = new ArrayDeque<>();
                for (int node = 0; node < n; node++) {
                    if (incomingEdges[node] == 0) {
                        q.offer(node);
                    }
                }
                int numExpanded = 0;
                // 只有 incomingEdge 是 0 的才可以进 queue
                // 所以最后比较进过 queue 的 num 和 总 node 数做比较
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    numExpanded++;
                    for (int nei : graph.get(cur)) {
                        if (--incomingEdges[nei] == 0) {
                            q.offer(nei);
                        }
                    }
                }
                return numExpanded != n;
            }
        }

        //todo:
        static class DFS {

        }
    }

    static class UAG {
        /*
                    1
                 /    \
                2   -  3    is not a UAG

                    1
                 /    \
                2      3    is a UAG
         */

        static class TopologicalSort {
            public static boolean hasCycle(int n, int[][] connections) {
                // here given n -> n nodes labeled from 0 - n - 1
                // connections[i] -> connections[i][0] points to connections[i][1] & connections[i][1] points to connections[i][0]

                List<List<Integer>> graph = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    graph.add(new ArrayList<>());
                }
                int[] edges = new int[n];
                for (int[] edge : connections) {
                    int one = edge[0];
                    int two = edge[1];
                    graph.get(one).add(two);
                    edges[one]++;
                    graph.get(two).add(one);
                    edges[two]++;
                }

                Queue<Integer> q = new ArrayDeque<>();
                for (int node = 0; node < n; node++) {
                    // 0 为独立节点，1 为只有一个度的节点，都进 queue
                    if (edges[node] <= 1) {
                        q.offer(node);
                    }
                }
                int numExpanded = 0;
                // 只有 incomingEdge 是 0 的才可以进 queue
                // 所以最后比较进过 queue 的 num 和 总 node 数做比较
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    numExpanded++;
                    for (int nei : graph.get(cur)) {
                        if (--edges[nei] == 1) {
                            q.offer(nei);
                        }
                    }
                }
                return numExpanded != n;
            }
        }

        static class BFS {
            // if we find a node that has been visited before (except its parent), there is then a cycle

            public static boolean hasCycle(int n, int[][] connections) {
                // here given n -> n nodes labeled from 0 - n - 1
                // connections[i] -> connections[i][0] points to connections[i][1] & connections[i][1] points to connections[i][0]

                List<List<Integer>> graph = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    graph.add(new ArrayList<>());
                }
                for (int[] edge : connections) {
                    int one = edge[0];
                    int two = edge[1];
                    graph.get(one).add(two);
                    graph.get(two).add(one);
                }

                // need 2 more containers
                // 1. a set to record visited nodes
                // 2. a container to record the parent of a node
                // a hashmap can satisfy both requirements

                // key=visitedNode, value=visitedFrom
                Map<Integer, Integer> visited = new HashMap<>();
                for (int node = 0; node < n; node++) {
                    if (!visited.containsKey(node)) {
                        visited.put(node, -1); // different connected components
                        if (bfs(node, visited, graph)) {
                            return false;
                        }
                    }
                }
                return true;
            }

            static boolean bfs(int node, Map<Integer, Integer> visited, List<List<Integer>> graph) {
                Queue<Integer> q = new ArrayDeque<>();
                q.offer(node);
                while (!q.isEmpty()) {
                    int cur = q.poll();
                    for (int nei : graph.get(cur)) {
                        if (!visited.containsKey(nei)) {
                            q.offer(nei);
                            visited.put(nei, cur);
                        } else if (nei != visited.get(cur)) { // if nei has been visited but not the parent of cur
                            return true;
                        }
                    }
                }
                return false;
            }
        }

        static class DFS {

            // if we find a node that has been visited before (except its parent), there is then a cycle

            public static boolean hasCycle(int n, int[][] connections) {
                // here given n -> n nodes labeled from 0 - n - 1
                // connections[i] -> connections[i][0] points to connections[i][1] & connections[i][1] points to connections[i][0]

                List<List<Integer>> graph = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    graph.add(new ArrayList<>());
                }
                for (int[] edge : connections) {
                    int one = edge[0];
                    int two = edge[1];
                    graph.get(one).add(two);
                    graph.get(two).add(one);
                }
                Set<Integer> visited = new HashSet<>();
                for (int node = 0; node < n; node++) {
                    // pass 一个 parent pointer 来避免 dfs 里面重复考虑 parent node
                    if (visited.add(node) && dfs(graph, node, -1, visited)) {
                        return true;
                    }
                }
                return false;
            }

            private static boolean dfs(List<List<Integer>> graph, int curNode, int parent, Set<Integer> visited) {
                for (int nei : graph.get(curNode)) {
                    if (nei == parent) {
                        continue;
                    }
                    if (!visited.add(nei)) {
                        return true;
                    }
                    dfs(graph, nei, curNode, visited);
                }
                return false;
            }
        }

    }

    public static void main(String[] args) {
        int[][] edges = new int[][]{{0, 1}, {1, 2}, {0, 2}};
        System.out.println(UAG.DFS.hasCycle(3, edges));
        System.out.println(UAG.TopologicalSort.hasCycle(3, edges));
    }
}
