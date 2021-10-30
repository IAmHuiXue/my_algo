package binary_search;

/** https://leetcode.com/problems/find-peak-element/ */

public class FindPeakElement {
    public int findPeakElement(int[] nums) {

        int l = 0, r = nums.length - 1;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1]) {
                r = mid;
            }
            else {
                l = mid + 1;
            }
        }
        return l;

//         if (nums.length == 1 || nums[1] < nums[0]) {
//             return 0;
//         }
//         if (nums[nums.length - 2] < nums[nums.length - 1]) {
//             return nums.length - 1;
//         }


//         int l = 0;
//         int r = nums.length - 1;
//         while (l < r - 1) {
//             int mid = l + (r - l) / 2;
//             if (nums[mid] > nums[mid - 1] && nums[mid] > nums[mid + 1]) {
//                 return mid;
//             }
//             if (nums[mid] > nums[mid - 1] && nums[mid] < nums[mid + 1]) {
//                 l = mid + 1;
//             } else if (nums[mid] < nums[mid - 1] && nums[mid] > nums[mid + 1]) {
//                 r = mid - 1;
//             } else if (nums[mid] < nums[mid - 1] && nums[mid] < nums[mid + 1]) {
//                 l = mid + 1;
//             }
//         }
//         return nums[l] < nums[r] ? r : l;
    }
}
