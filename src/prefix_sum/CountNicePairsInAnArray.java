package prefix_sum;

import java.util.HashMap;
import java.util.Map;

/** <a href="https://leetcode.com/problems/count-nice-pairs-in-an-array/">https://leetcode.com/problems/count-nice-pairs-in-an-array/</a>*/

public class CountNicePairsInAnArray {
    public int countNicePairs(int[] nums) {
        // i < j
        // nums[i] - rev(nums[i]) = nums[j] - rev(nums[j])

        int mod = (int) 1e9 + 7; // memorize this representation
        // int mod = (int) Math.pow(10, 9) + 7;
        int res = 0;
        // <nums[i] - rev[nums[i]], occurrence>
        Map<Integer, Integer> visited = new HashMap<>();
        for (int num : nums) {
            int freq = visited.getOrDefault(num - rev(num), 0);
            res = (freq + res) % mod; // !
            visited.put(num - rev(num), freq + 1);
        }
        return res;
    }

    private int rev(int num) {
        int res = 0;
        while (num != 0) {
            res = res * 10 + num % 10;
            num /= 10;
        }
        return res;
    }
}
