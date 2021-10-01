package pq;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/minimum-cost-to-connect-sticks/
 */

public class MinimumCostToConnectSticks {
    /*
        Always pick two of the smallest sticks to connect and continue doing this
        until you get only one stick.
     */
    public int connectSticks(int[] sticks) {
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        for (int stick : sticks) {
            minHeap.offer(stick);
        }
        int cost = 0;
        while (minHeap.size() > 1) { // !
            int smallest = minHeap.poll();
            int secondSmallest = minHeap.poll();
            int newStick = smallest + secondSmallest;
            cost += newStick;
            minHeap.offer(newStick);
        }
        return cost;
    }
    // time: O(nlog(n))
}
