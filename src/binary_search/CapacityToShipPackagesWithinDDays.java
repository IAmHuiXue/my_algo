package binary_search;

/**
 * https://leetcode.com/problems/capacity-to-ship-packages-within-d-days/
 */

public class CapacityToShipPackagesWithinDDays {
    /*
The key is left = max(weights), right = sum(weights),
which are the minimum and maximum possible weight capacity of the ship.

Therefore, the question becomes Binary Search to find the minimum weight capacity of the ship between left and right.

We start from
mid = (left + right) / 2 as our current weight capacity of the ship.
need = days needed == 1
cur = current cargo in the ship == 0

Start put cargo into ship in order.
when need > D, it means the current ship is too small, we modify left = mid + 1 and continue
If all the cargo is successfully put into ships, we might have a chance to find a smaller ship, so let right = mid and continue.
Finally, when our left == right, we reach our answer
     */
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
