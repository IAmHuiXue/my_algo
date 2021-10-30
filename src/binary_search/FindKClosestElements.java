package binary_search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** https://leetcode.com/problems/find-k-closest-elements/ */

public class FindKClosestElements {
    static class SortWithCustomComparator {
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            // Convert from array to list first to make use of Collections.sort()
            List<Integer> sortedArr = new ArrayList<>();
            for (int num: arr) {
                sortedArr.add(num);
            }

            // Sort using custom comparator
            Collections.sort(sortedArr, (num1, num2) -> Math.abs(num1 - x) - Math.abs(num2 - x));

            // Only take k elements
            sortedArr = sortedArr.subList(0, k);

            // Sort again to have output in ascending order
            Collections.sort(sortedArr);
            return sortedArr;
        }

        // time: O(nlog(n) + klog(k))
        // space: O(n)
    }

    static class BSPlusSlidingWindow{
        public List<Integer> findClosestElements(int[] arr, int k, int x) {
            List<Integer> result = new ArrayList<>();
            if (arr.length == k) {
                for (int i = 0; i < k; i++) {
                    result.add(arr[i]);
                }
                return result;
            }
            // find the index whose value is closest to x, and if a[index] == x, index is the first occurrence
            int left = 0;
            int right = arr.length - 1;
            while (left < right - 1) {
                int mid = left + (right - left) / 2;
                if (arr[mid] <= x) {
                    left = mid;
                } else {
                    right = mid;
                }
            }
            int closest;
            if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x)) {
                closest = left;
            } else {
                closest = right;
            }

            // 中心开花
            left = closest;
            right = left + 1;
            while (result.size() != k) {
                if (left < 0 || right < arr.length && Math.abs(arr[right] - x) < Math.abs(arr[left] - x)) {
                    result.add(arr[right++]);
                } else {
                    result.add(arr[left--]);
                }
            }
            Collections.sort(result);
            return result;
        }
    }
}
