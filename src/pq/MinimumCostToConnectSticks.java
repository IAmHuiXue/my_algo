package pq;

import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/minimum-cost-to-connect-sticks/">https://leetcode.com/problems/minimum-cost-to-connect-sticks/</a>
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
            minHeap.offer(newStick); // !
        }
        return cost;
    }

    // sort array will not be as good as pq
    // because after a new stick gets created, it needs to be compared with the rest of the sticks to find the
    // shortest one at each step. By offering the newStick back into pq, this can be achieved.

    // time: O(nlog(n))
}
