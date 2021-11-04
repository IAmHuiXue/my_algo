package bfs.bfs2;

import java.util.*;

/**
 * https://leetcode.com/problems/cheapest-flights-within-k-stops/
 */

public class CheapestFlightsWithinKStops {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // 1. build a graph
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] flight : flights) {
            graph.computeIfAbsent(flight[0], v -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
        }
        // 2. maintain a pq and perform BFS2
        PriorityQueue<Cell> minHeap = new PriorityQueue<>();
        minHeap.offer(new Cell(src, k, 0));
        while (!minHeap.isEmpty()) {
            Cell cur = minHeap.poll();
            // 当终点被 expand 出来的时候，根据 Dijkstra 的结论，此时终点的距离就是到起点的最短距离
            if (cur.city == dst) {
                return cur.price;
            }
            if (cur.stop >= 0) {
                for (int[] nei : graph.getOrDefault(cur.city, new ArrayList<>())) {
                    // 这道题虽然求的是点到点的最小距离，不需要一个 distance[] 来记录起点到每个点的最小距离
                    // 但是 maintain 一个 distance[] 的好处是，这样可以在每个邻居结点入 queue 之前，
                    // 可以将邻居结点的当前距离和 distance[nei] 相比较来避免重复入 queue，即当邻居结点已经被
                    // expanded 过了的时候，aka distance[] 已经不是起始值的时候
                    minHeap.offer(new Cell(nei[0], cur.stop - 1, nei[1] + cur.price));
                }
            }
        }
        return -1;

    }

    static class Cell implements Comparable<Cell> {
        int city;
        int stop;
        int price;

        Cell(int city, int stop, int price) {
            this.city = city;
            this.stop = stop;
            this.price = price;
        }

        @Override
        public int compareTo(Cell o) {
            return Integer.compare(this.price, o.price);
        }
    }
}
