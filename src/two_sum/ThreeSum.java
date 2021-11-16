package two_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/3sum/
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
}
