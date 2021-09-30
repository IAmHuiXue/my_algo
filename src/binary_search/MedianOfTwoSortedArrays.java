package binary_search;

/** https://leetcode.com/problems/median-of-two-sorted-arrays/ */

public class MedianOfTwoSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        if (m + n == 0) {
            return 0;
        }
        int mid = (m + n) / 2;
        // if total length is even, median is the avr of the middle two ele
        // otherwise, median is the middle ele
        if ((m + n) % 2 == 0) {
            int left = KthSmallestInTwoSortedArrays.kth(nums1, nums2, mid);
            int right = KthSmallestInTwoSortedArrays.kth(nums1, nums2, mid + 1);
            return (left + right) / 2.0;
        }
        return KthSmallestInTwoSortedArrays.kth(nums1, nums2, mid + 1);
    }
    // O(log(m + n));
}
