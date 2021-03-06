package two_sum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * <a href="https://leetcode.com/problems/two-sum/">...</a>
 */

public class TwoSum {

    /**
     * to clarify
     * 1. sorted?
     * 2. return what - index or elements or boolean; one pair or all
     * 3. duplicates?
     * 4. would prefer better time complexity or space?
     * 5. what is the size of the input
     */

    // 如果 return 的是 index，且使用的是 sort 方法的话，就要提前保存好原始数据的 index，因为 sort 之后 index 会改变

    static class WithMap {
        public int[] twoSum(int[] array, int target) {
            // key=ele, value=index
            Map<Integer, Integer> map = new HashMap<>();
            for (int i = 0; i < array.length; i++) {
                int diff = target - array[i];
                Integer res = map.get(diff);
                if (res != null) {
                    return new int[]{res, i};
                }
                // if only need one pair, it does not matter how we treat the duplicates here
                map.putIfAbsent(array[i], i);
            }
            return null;
        }
        // time: O(n)
        // space: O(n)
    }

    static class TwoPointers {

        /** followup:
         *
         * 数字容易越界怎么办
         *  - 用 long
         * target 是 double 怎么办
         *  - 用高精度去做
         *      - target - tmp < 0.01
         */

        public static int[] twoSum(int[] array, int target) {
            // why? because after sort, the indices got changed.
            // since the problem asks to return the indices of the pair
            // we need to firstly find the elements that suffice the values
            // and go back to the original array to find the indices accordingly
            int[] cur = array.clone();
            Arrays.sort(cur);
            int l = 0;
            int r = cur.length - 1;
            while (l < r) {
                if (cur[l] + cur[r] == target) {
                    break;
                }
                if (cur[l] + cur[r] < target) {
                    l++;
                } else {
                    r--;
                }
            }
            int[] res = new int[2];
            for (int i = 0; i < array.length; i++) {
                if (array[i] == cur[l]) {
                    res[0] = i;
                    break;
                }
            }
            for (int j = array.length - 1; j >= 0; j--) {
                if (array[j] == cur[r]) {
                    res[1] = j;
                    break;
                }
            }
            return res;
        }
        // time: O(nlog(n))
        // space: O(sort)
    }

    static class BruteForce {
        public static int[] twoSum(int[] array, int target) {
            for (int i = 0; i < array.length - 1; i++) {
                for (int j = i + 1; j < array.length; j++) {
                    if (array[j] == target - array[i]) {
                        return new int[]{i, j};
                    }
                }
            }
            return null;
        }
        // time: O(n^2)
        // space: O(1)
    }

    static class BinarySearch {
        // time: O(nlog(n))
    }
}
