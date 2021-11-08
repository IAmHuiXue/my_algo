package pq;

import java.util.Arrays;
import java.util.HashSet;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * https://leetcode.com/problems/campus-bikes/
 */

public class CampusBikes {
    public int[] assignBikes(int[][] workers, int[][] bikes) {
        int n = workers.length;

        // order by Distance ASC, WorkerIndex ASC, BikeIndex ASC
        // int[]{a, b, c} -> distance, workerIndex, bikeIndex
        PriorityQueue<int[]> q = new PriorityQueue<>((a, b) -> {
            int comp = Integer.compare(a[0], b[0]);
            if (comp == 0) {
                if (a[1] == b[1]) {
                    return Integer.compare(a[2], b[2]);
                }
                return Integer.compare(a[1], b[1]);
            }
            return comp;
        });

        // loop through every possible pairs of bikes and people,
        // calculate their distance, and then throw it to the pq.
        for (int i = 0; i < workers.length; i++) { // O(m * n)
            int[] worker = workers[i];
            for (int j = 0; j < bikes.length; j++) {
                int[] bike = bikes[j];
                int dist = Math.abs(bike[0] - worker[0]) + Math.abs(bike[1] - worker[1]);
                q.add(new int[]{dist, i, j});
            }
        }

        // init the result array with state of 'unvisited'.
        int[] res = new int[n];
        Arrays.fill(res, -1);

        // assign the bikes.
        Set<Integer> bikeAssigned = new HashSet<>();

        while (bikeAssigned.size() < n) { // worse case: poll all m * n nodes? -> m * n * log(m * n)
            int[] workerAndBikePair = q.poll();
            if (res[workerAndBikePair[1]] == -1 // the worker has not been assigned with a bike
                    && !bikeAssigned.contains(workerAndBikePair[2])) { // the specified bike has not been assigned
                res[workerAndBikePair[1]] = workerAndBikePair[2];
                bikeAssigned.add(workerAndBikePair[2]);
            }
        }
        return res;
    }
}
