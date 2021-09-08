package bfs;

import java.util.*;

/** https://leetcode.com/problems/network-delay-time/ */

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        // build the graph
        // the V of map needs to include 2 states: neighbor node and its cost
        Map<Integer, List<Cell>> graph = new HashMap<>();
        for (int[] edge : times) {
            List<Cell> neighbors = graph.getOrDefault(edge[0], new ArrayList<>());
            neighbors.add(new Cell(edge[1], edge[2]));
            graph.put(edge[0], neighbors);
        }

        PriorityQueue<Cell> minHeap = new PriorityQueue<>();
        minHeap.offer(new Cell(k, 0));
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
                        // will not be expanded once. But here we perform this to reduce the times of heap operations
                        if (!costs.containsKey(nei.node)) {
                            minHeap.offer(new Cell(nei.node, cur.time + nei.time)); // !
                        }
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

        // I think the reason we do have to override these two methods becuase
        // in the map, the key stored is Integer, not Cell, and we only use map methods related to Key

//         @Override
//         public boolean equals(Object o) {
//             if (this == o) return true;
//             if (o == null || getClass() != o.getClass()) return false;
//             Cell cell = (Cell) o;
//             return node == cell.node && time == cell.time;
//         }

//         @Override
//         public int hashCode() {
//             return Objects.hash(node, time);
//         }
    }
}
