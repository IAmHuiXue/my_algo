package dfs;

/** https://leetcode.com/problems/campus-bikes-ii/ */

public class CampusBikesII {
    // level: each worker
    // branches: each bike
    // Maximum number of bikes is 10, this information is provided by the problem
    private boolean visited [] = new boolean[10];
    private int smallestDistanceSum = Integer.MAX_VALUE;

    public int assignBikes(int[][] workers, int[][] bikes) {
        minimumDistanceSum(workers, 0, bikes, 0);
        return smallestDistanceSum;
    }

    private void minimumDistanceSum(int[][] workers, int workerIndex, int[][] bikes, int currDistanceSum) {
        if (workerIndex >= workers.length) {
            smallestDistanceSum = Math.min(smallestDistanceSum, currDistanceSum);
            return;
        }
        // If the current distance sum is greater than the smallest result
        // found then stop exploring this combination of workers and bikes
        if (currDistanceSum >= smallestDistanceSum) {
            return;
        }
        for (int bikeIndex = 0; bikeIndex < bikes.length; bikeIndex++) {
            // If bike is available
            if (!visited[bikeIndex]) {
                visited[bikeIndex] = true;
                minimumDistanceSum(workers, workerIndex + 1, bikes,
                        currDistanceSum + findDistance(workers[workerIndex], bikes[bikeIndex]));
                visited[bikeIndex] = false;
            }
        }
    }

    // Manhattan distance
    private int findDistance(int[] worker, int[] bike) {
        return Math.abs(worker[0] - bike[0]) + Math.abs(worker[1] - bike[1]);
    }

    // time: O(m! / (m - n)!)
        // the first work has m choices, the second one has (m-1) choices
        // m * (m-1) * (m-2) * ... * (m-n+1)

}
