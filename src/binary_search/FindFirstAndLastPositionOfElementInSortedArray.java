package binary_search;

/** https://leetcode.com/problems/find-first-and-last-position-of-element-in-sorted-array/ */

public class FindFirstAndLastPositionOfElementInSortedArray {
    public int[] searchRange(int[] nums, int target) {
        // firstly find the index of the first occurrence
        // if not exists, return -1, -1
        // otherwise, find the index of the last occurrence while changing the left boundary as the first occurrence
        if (nums.length == 0) {
            return new int[]{-1, -1};
        }
        int l = 0;
        int r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] < target) {
                l = mid + 1;
            } else {
                r = mid;
            }
        }
        // l = r = m
        if (nums[l] != target) {
            return new int[]{-1, -1};
        }

        int leftFound = l;
        r = nums.length - 1;
        while (l < r - 1) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > target) {
                r = mid;
            } else {
                l = mid;
            }
        }
        // l = r - 1
        return nums[r] == target ? new int[]{leftFound, r} : new int[]{leftFound, l};
    }
}
