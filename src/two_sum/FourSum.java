package two_sum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** https://leetcode.com/problems/4sum/ */

public class FourSum {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < nums.length - 3; i++) {
            // if (i + 1 < nums.length - 3 && nums[i] == nums[i + 1]) {
            //     continue;
            // }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            for (int j = i + 1; j < nums.length - 2; j++) {
                // if (j + 1 < nums.length - 2 && nums[j] == nums[j + 1]) {
                //     continue;
                // }
                if (j > i + 1 && nums[j] == nums[j - 1]) {
                    continue;
                }
                int k = j + 1;
                int l = nums.length - 1;
                while (k < l) {
                    // if (k + 1 < l && nums[k] == nums[k + 1]) {
                    //     k++;
                    //     continue;
                    // }
                    if (k > j + 1 && nums[k] == nums[k - 1]) {
                        k++;
                        continue;
                    }
                    int curTarget = target - nums[i] - nums[j];
                    if (curTarget == nums[k] + nums[l]) {
                        result.add(Arrays.asList(nums[i], nums[j], nums[k++], nums[l--]));
                    } else if (curTarget < nums[k] + nums[l]) {
                        l--;
                    } else {
                        k++;
                    }
                }
            }
        }
        return result;
    }
}
