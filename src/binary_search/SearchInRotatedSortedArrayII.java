package binary_search;

/** https://leetcode.com/problems/search-in-rotated-sorted-array-ii/ */

public class SearchInRotatedSortedArrayII {
    public boolean search(int[] nums, int target) {
        /*
        We are comparing the middle index with the end index for all the 3 possible scenarios -
        value at mid < end
        value at mid > end
        value at mid == end
        And making the decisions accordingly
        */

        int start = 0;
        int end = nums.length - 1;

        while (start <= end){
            int mid = start + (end - start) / 2;
            // If the target == value at mid, return true as value found
            if (target == nums[mid])
                return true;

            // If value at mid < end, it means the right half is sorted
            if (nums[mid] < nums[end]){
                // Check if target lies in the right half and accordingly update start and end indices
                if (target > nums[mid] && target <= nums[end])
                    start = mid + 1;
                else
                    end = mid - 1;
            }
            // If value at mid > end, it means the left half is sorted
            else if (nums[mid] > nums[end]){
                // Check if target lies in the left half and accordingly update start and end indices
                if (target >= nums[start] && target < nums[mid])
                    end = mid - 1;
                else
                    start = mid + 1;
            }
            // If mid and end are same, then we reduce the end index
            else {
                end--;
            }
        }
        return false;
    }
}
