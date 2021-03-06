package binary_search;

public class KthSmallestInTwoSortedArrays {
    /**
     * Given two sorted arrays of integers, find the Kth-smallest number.
     *
     * Assumptions
     *
     * The two given arrays are not null and at least one of them is not empty
     *
     * K >= 1, K <= total lengths of the two sorted arrays
     *
     * Examples
     *
     * A = {1, 4, 6}, B = {2, 3}, the 3rd smallest number is 3.
     *
     * A = {1, 2, 3, 4}, B = {}, the 2nd smallest number is 2.
     */

    public static int kth(int[] a, int[] b, int k) {
        // Assume a,b are not null, at least one of them
        // is not empty, k <= a.length + b.length, k >= 1
        return kth(a, 0, b, 0, k);
    }

    // in the subarray of a starting from index a-left, and subarray
    // of b starting from index b-left, find the kth-smallest element
    // among these two subarray
    // so both a[a-left] & b[b-left] elements are the ones we are considering right now
    private static int kth(int[] a, int aLeft, int[] b, int bLeft, int k) {
        // aLeft points to the first unselected number in A
        // bLeft points to the first unselected number in B

        // three base cases:
        // 1. we already eliminate all elements in a
        if (aLeft >= a.length) {
            // find the kth element in b[] starting from bLeft
            return b[bLeft + k - 1];
        }
        // 2. we already eliminate all elements in b
        if (bLeft >= b.length) {
            // find the kth element in a[] starting from aLeft
            return a[aLeft + k - 1];
        }
        // 3. when k is reduced to 1, do not miss this base case
        // because in the following logic we need k >= 2 to make it work
        if (k == 1) {
            return Math.min(a[aLeft], b[bLeft]);
        }
        // we compare the k/2 th element in a's subarray,
        // and the k/2 the element in b's subarray
        // to determine which k/2 partition can be surely
        // included in the smallest k elements
        int aMId = aLeft + k / 2 - 1;
        int bMid = bLeft + k / 2 - 1;
        // check if aMId or bMid has already been out of bound
        int aVal = aMId >= a.length ? Integer.MAX_VALUE : a[aMId];
        int bVal = bMid >= b.length ? Integer.MAX_VALUE : b[bMid];
        if (aVal <= bVal) {
            return kth(a, aMId + 1, b, bLeft, k - k / 2);
        } else {
            return kth(a, aLeft, b, bMid + 1, k - k / 2);
        }
    }
    // time: O(log(k)) --> each time we reduce the searching range by half --> binary search
}
