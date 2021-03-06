package bfs.bfs2;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/cheapest-flights-within-k-stops/">...</a>
 */

public class CheapestFlightsWithinKStops {
    static class WorkableSolution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, List<int[]>> map = new HashMap<>();
            Map<Integer, Integer> visited = new HashMap<>();
            for (int[] flight : flights) {
                map.computeIfAbsent(flight[0], v -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
            }
            PriorityQueue<Cell> minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.price, b.price));
            minHeap.offer(new Cell(src, k, 0));

            while (!minHeap.isEmpty()) {
                Cell cur = minHeap.poll();
                if (cur.city == dst) {
                    return cur.price;
                }
                visited.put(cur.city, cur.stop);

                if (cur.stop >= 0) {
                    for (int[] next : map.getOrDefault(cur.city, new ArrayList<>())) {
                        if (!visited.containsKey(next[0]) || cur.stop > visited.get(next[0])) { // why?
                            minHeap.offer(new Cell(next[0], cur.stop - 1, next[1] + cur.price));
                        }
                    }
                }
            }
            return -1;
        }

    }

    static class WrongSolution {
        public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
            Map<Integer, List<int[]>> map = new HashMap<>();
            for (int[] flight : flights) {
                map.computeIfAbsent(flight[0], v -> new ArrayList<>()).add(new int[]{flight[1], flight[2]});
            }
            int[] prices = new int[n];
            Arrays.fill(prices, Integer.MAX_VALUE);

            PriorityQueue<Cell> minHeap = new PriorityQueue<>();
            minHeap.offer(new Cell(src, k, 0));
            prices[src] = 0;

            while (!minHeap.isEmpty()) {
                Cell cur = minHeap.poll();
                if (cur.city == dst) {
                    return cur.price;
                }
                if (prices[cur.city] < cur.price) {
                    continue;
                }
                prices[cur.city] = cur.price;
                if (cur.stop >= 0) {
                    for (int[] next : map.getOrDefault(cur.city, new ArrayList<>())) {
                        if (prices[next[0]] > next[1] + cur.price) {
                            minHeap.offer(new Cell(next[0], cur.stop - 1, next[1] + cur.price));
                        }
                    }
                }
            }
            return -1;

        }
    }

    static class Solution2 {
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
                // ???????????? expand ???????????????????????? Dijkstra ???????????????????????????????????????????????????????????????
                if (cur.city == dst) {
                    return cur.price;
                }
                if (cur.stop >= 0) {
                    for (int[] nei : graph.getOrDefault(cur.city, new ArrayList<>())) {
                        // ?????????????????????????????????????????????????????????????????? distance[] ??????????????????????????????????????????
                        // ?????? maintain ?????? distance[] ??????????????????????????????????????????????????? queue ?????????
                        // ??????????????????????????????????????? distance[nei] ??????????????????????????? queue??????????????????????????????
                        // expanded ??????????????????aka distance[] ??????????????????????????????
                        minHeap.offer(new Cell(nei[0], cur.stop - 1, nei[1] + cur.price));
                    }
                }
            }
            return -1;

        }
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
