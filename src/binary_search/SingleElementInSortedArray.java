package binary_search;

/** https://leetcode.com/problems/single-element-in-a-sorted-array/*/

public class SingleElementInSortedArray {
    public int singleNonDuplicate(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left < right) {
            int mid = left + (right - left) / 2;
            // 利用其中一个 half 的 size 的单复数来确定落单的元素到底在哪边
            boolean halvesAreEven = (right - mid) % 2 == 0;
            if (nums[mid] == nums[mid + 1]) {
                if (halvesAreEven) {
                    left = mid + 2;
                } else {
                    right = mid - 1;
                }
            } else if (nums[mid] == nums[mid - 1]) {
                if (halvesAreEven) {
                    right = mid - 2;
                } else {
                    left = mid + 1;
                }
            } else {
                return nums[mid];
            }
        }
        return nums[left];
    }
}
