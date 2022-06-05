package binary_search.trick;

/**
 * <a href="https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/">...</a>
 */

public class CapacityToShipPackagesWithinDDays {
    // 二分法猜答案

    public int shipWithinDays(int[] weights, int days) {
        int minCap = 0, maxCap = 0;
        for (int w : weights) {
            minCap = Math.max(minCap, w);
            maxCap += w;
        }
        while (minCap < maxCap) {
            int midCap = minCap + (maxCap - minCap) / 2, daysNeeded = 1, capacity = 0;
            for (int w : weights) {
                if (capacity + w > midCap) { // if the current cap is more than the proposed cap
                    daysNeeded += 1;
                    capacity = 0;
                }
                capacity += w;
            }
            if (daysNeeded > days) { // midCap is too small, need to increase the cap
                minCap = midCap + 1;
            } else {
                maxCap = midCap; // midCap could be the answer, but the answer could be smaller
            }
        }
        return minCap;
    }
}
