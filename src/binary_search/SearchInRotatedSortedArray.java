package binary_search;

/** https://leetcode.com/problems/search-in-rotated-sorted-array/ */

public class SearchInRotatedSortedArray {
    public int search(int[] nums, int target) {
        // sorted in ascending order
        // distinct values
        // rotated
        // need to use BS
        if (nums == null || nums.length == 0) {
            return -1;
        }

        // 1. find the pivot

        // trick1: only compare mid with one side
        // trick2: it is hard (maybe impossible) to find the smallest by checking left and to find the largest by checking right!
        // so the correct practice is to check left searching largest or check right searching smallest
        // trick3: comparing the two scenarios I find out due to the nature of fact that mid always equals left when 2 elements
        // the best practice is to check right searching smallest -> this way while loop condition could simply be left < right

        // find the index of the smallest element
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        // left = right = mid = the index of the smallest element
        int start = left;
        left = 0;
        right = nums.length - 1;

        // 2. find the proper range the target is standing within
        if (nums[start] <= target && target <= nums[right]) {
            return ClassicBS.ascendingBS(nums, start, right, target);
        }
        return ClassicBS.ascendingBS(nums, left, start - 1, target);
    }


}
