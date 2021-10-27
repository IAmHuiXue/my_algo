package bfs.bfs2;

import java.util.*;

/** https://leetcode.com/problems/network-delay-time/ */

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {

        // 这道题可以转化为，通过 bfs2 来求出从 k 点到所有点的最短距离
        // 1，如果有点没有 reach 到， 根据题意，return -1
        // 2，所有距离中，最大值就是结果，因为可以理解为，当 reach 到这个点时，其他点已经都 reach 到了
            // 而且通过 bfs2 的算法，我们已经可以确保 reach 每一个点已经是最小距离了


        // build the graph
        // the V of map needs to include 2 states: neighbor node and its cost
        Map<Integer, List<Cell>> graph = new HashMap<>();
        for (int[] edge : times) {
//            List<Cell> neighbors = graph.getOrDefault(edge[0], new ArrayList<>());
//            neighbors.add(new Cell(edge[1], edge[2]));
//            graph.put(edge[0], neighbors);
            graph.computeIfAbsent(edge[0], x -> new ArrayList<>()).add(new Cell(edge[1], edge[2]));
        }

        PriorityQueue<Cell> minHeap = new PriorityQueue<>();
        minHeap.offer(new Cell(k, 0));

        // to record the min distance from node k to all the other nodes
        // 这里用 map 而不是定长 array 的好处是，后面可以通过 map 的 size 来确定是否 reach 到了所有其他的点
        Map<Integer, Integer> costs = new HashMap<>();

        while (!minHeap.isEmpty()) {
            Cell cur = minHeap.poll();
            // this is BFS2, therefore we should check one's expand state as soon as we poll it out
            if (!costs.containsKey(cur.node)) { // it has not been expanded before
                costs.put(cur.node, cur.time);
                List<Cell> neighbors = graph.get(cur.node);
                // dest nodes will not have outgoing edges, so they will not have neighbors in graph map
                if (neighbors != null) {
                    for (Cell nei : neighbors) {
                        // it could be ignored because for BFS2, nodes could be generated multiple times but
                        // will only be expanded once. But here we perform this to reduce the times of heap operations
//                        if (!costs.containsKey(nei.node)) {
                            minHeap.offer(new Cell(nei.node, cur.time + nei.time)); // !
//                        }
                    }
                }
            }
        }

        // if not all the nodes can be traversed, aka visited, return -1 per the problem required
        if (costs.size() != n) {
            return -1;
        }

        // loop through the costs map to find the node that costs the most time -> becomes the result
        int res = 0;
        for (int cost : costs.values()) {
            res = Math.max(res, cost);
        }
        return res;
    }

    static class Cell implements Comparable<Cell> {
        int node;
        int time;

        Cell(int n, int t) {
            node = n;
            time = t;
        }

        @Override
        public int compareTo(Cell anotherC) {
            return Integer.compare(this.time, anotherC.time);
        }
    }

}
