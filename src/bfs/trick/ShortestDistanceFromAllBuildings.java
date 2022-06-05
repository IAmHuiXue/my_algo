package bfs.trick;

import java.util.ArrayDeque;

import java.util.Queue;

/**
 * <a href="https://leetcode.com/problems/shortest-distance-from-all-buildings/">...</a>
 */

public class ShortestDistanceFromAllBuildings {

    // need to think about -> is num of buildings more or num of free lands more?
    // bfs requires traversing the entire graph from one point
    // if fewer houses, bfs from house points, and vise versa

    private static final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    static class FromEmptySpace {
        public static int shortestDistance(int[][] grid) {
            int M = grid.length;
            int N = grid[0].length;
            int[] costs = new int[]{Integer.MAX_VALUE};
            int numBuildings = 0;
            // count num of buildings
            for (int[] ints : grid) {
                for (int j = 0; j < N; j++) {
                    if (ints[j] == 1) {
                        numBuildings++;
                    }
                }
            }
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    // start from free cells
                    if (grid[i][j] == 0) {
                        cal(grid, costs, i, j, M, N, numBuildings);
                    }
                }
            }
            return costs[0] == Integer.MAX_VALUE ? -1 : costs[0];
        }

        private static void cal(int[][] grid, int[] costs, int i, int j, int M, int N, int numBuildings) {
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[M][N];
            queue.offer(new int[]{i, j});
            visited[i][j] = true;
            int totalCost = 0;
            int cost = 1;
            int numBuildingsReached = 0;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int k = 0; k < size; k++) {
                    int[] cur = queue.poll();
                    for (int[] dir : DIRS) {
                        int ni = dir[0] + cur[0];
                        int nj = dir[1] + cur[1];
                        if (ni >= 0 && nj >= 0 && ni < M && nj < N && grid[ni][nj] != 2 && !visited[ni][nj]) {
                            if (grid[ni][nj] == 1) {
                                numBuildingsReached++;
                                totalCost += cost;
                            } else {
                                queue.offer(new int[]{ni, nj});
                            }
                            visited[ni][nj] = true;
                        }
                    }
                }
                cost++;
            }
            // only count it if the initial cell can eventually reach all buildings
            if (numBuildingsReached == numBuildings) {
                costs[0] = Math.min(costs[0], totalCost);
            } else {
                // else we know all the free cells visited in this path will not get the results,
                // so we mark all the cells visited this time as blocks
                for (i = 0; i < M; i++) {
                    for (j = 0; j < N; i++) {
                        if (grid[i][j] == 0 && visited[i][j]) {
                            grid[i][j] = 2;
                        }
                    }
                }
            }
        }

        // time: O(m*n*m*n)
    }

    static class FromBuilding {
        static class Cell {
            int cost;
            int numBuildingReached;
        }

        public static int shortestDistance(int[][] grid) {
            // We will need to store 2 more values at each cell position of empty cells:
            // total distance sum from all houses to this empty land and number of houses that can reach this empty land.

            // therefore, here we create a cell class to carry the information

            int M = grid.length;
            int N = grid[0].length;
            Cell[][] costs = new Cell[M][N];
            int numBuildings = 0;

            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    if (grid[i][j] == 1) {
                        cal(grid, costs, i, j, M, N);
                        numBuildings++;
                    }
                }
            }

            int result = Integer.MAX_VALUE;
            for (int i = 0; i < M; i++) {
                for (int j = 0; j < N; j++) {
                    // only if the landing cell is able to reach all buildings
                    if (grid[i][j] == 0 && costs[i][j] != null && costs[i][j].numBuildingReached == numBuildings) {
                        result = Math.min(result, costs[i][j].cost);
                    }
                }
            }
            return result == Integer.MAX_VALUE ? -1 : result;
        }

        private static void cal(int[][] grid, Cell[][] costs, int i, int j, int M, int N) {
            Queue<int[]> queue = new ArrayDeque<>();
            boolean[][] visited = new boolean[M][N];
            queue.offer(new int[]{i, j});
            visited[i][j] = true;
            int cost = 1;
            while (!queue.isEmpty()) {
                int size = queue.size();
                for (int k = 0; k < size; k++) {
                    int[] cur = queue.poll();
                    for (int[] dir : DIRS) {
                        int ni = dir[0] + cur[0];
                        int nj = dir[1] + cur[1];
                        if (ni >= 0 && nj >= 0 && ni < M && nj < N && grid[ni][nj] == 0 && !visited[ni][nj]) {
                            if (costs[ni][nj] == null) {
                                costs[ni][nj] = new Cell();
                            }
                            costs[ni][nj].cost += cost;
                            costs[ni][nj].numBuildingReached++;
                            queue.offer(new int[]{ni, nj});
                            visited[ni][nj] = true;
                        }
                    }
                }
                cost++;
            }
        }
    }

    public static void main(String[] args) {
        int[][] grid = new int[][]{{1, 0, 2, 0, 1}, {0, 0, 0, 0, 0}, {0, 0, 1, 0, 0}};
        System.out.println(ShortestDistanceFromAllBuildings.FromEmptySpace.shortestDistance(grid));
    }
}
