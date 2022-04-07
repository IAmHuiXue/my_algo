package trick;

import java.util.Collections;
import java.util.PriorityQueue;

/** https://leetcode.com/problems/minimize-deviation-in-array/ */

public class MinimizeDeviationInArray {
    /*
Step 1: Initialize a max-heap evens.

For an even number in nums, put it directly into evens; for the odd number in nums, multiply by 2 and put it into evens.
Step 2: Maintain an integer minimum to keep tracking the smallest element in evens.

Step 3: Take out the maximum number in evens. Use the maximum and the maintained minimum to update the minimum deviation.
If the maximum number is even, divide by 2 and push into evens.

Step 4: Repeat Step 3 until the maximum number in evens is odd.

Step 5: Return the minimum deviation.
     */
    public int minimumDeviation(int[] nums) {
        int min = Integer.MAX_VALUE;
        int minDev = Integer.MAX_VALUE;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for (int n : nums) {
            if (n % 2 == 1) {
                n *= 2;
            }
            min = Math.min(min, n);
            maxHeap.offer(n);
        }
        while (!maxHeap.isEmpty()) {
            int curMax = maxHeap.poll();
            minDev = Math.min(minDev, curMax - min);
            if (curMax % 2 == 0) {
                maxHeap.offer(curMax / 2);
                min = Math.min(min, curMax / 2);
            } else {
                // when the curMax polled from pq is odd, it will be the final max
                break;
            }
        }
        return minDev;
    }
}
