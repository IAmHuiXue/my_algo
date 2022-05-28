package two_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/3sum/">...</a>
 */

public class ThreeSum {
    public List<List<Integer>> threeSum(int[] array) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(array);
        for (int i = 0; i < array.length - 2; i++) { // pay attention to range of i!
            if (i > 0 && array[i] == array[i - 1]) {
                continue;
            }
            int j = i + 1; // pay attention to range of j
            int k = array.length - 1; // pay attention to range of k
            while (j < k) {
                if (j > i + 1 && array[j] == array[j - 1]) { // j > i + 1!
                    j++;
                    continue;
                }
                int sum = array[j] + array[k];
                int curTarget = -array[i];
                if (sum == curTarget) {
                    result.add(Arrays.asList(array[i], array[j++], array[k--]));
                } else if (sum < curTarget) {
                    j++;
                } else {
                    k--;
                }
            }
        }
        return result;
        // O(n^2) -> iterate over array, for each element, perform twoSum -> n*n
    }

    public List<List<Integer>> threeSumByTwoSum(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        Arrays.sort(nums);
        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            List<List<Integer>> tmp = twoSum(nums, i + 1, -nums[i]);
            for (List<Integer> r : tmp) {
                r.add(0, nums[i]);
            }
            res.addAll(tmp);
        }
        return res;
    }

    private List<List<Integer>> twoSum(int[] nums, int index, int target) {
        List<List<Integer>> res = new ArrayList<>();
        int start = index;
        int end = nums.length - 1;
        while (start < end) {
            if (start > index && nums[start] == nums[start - 1]) {
                start++;
                continue;
            }
            if (nums[start] + nums[end] == target) {
                res.add(new ArrayList<>(Arrays.asList(nums[start++], nums[end--])));
            } else if (nums[start] + nums[end] < target) {
                start++;
            } else {
                end--;
            }
        }
        return res;
    }
}
