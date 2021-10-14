package two_sum;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/two-sum-iii-data-structure-design/
 */

public class TwoSumIIIDataStructureDesign {

    /**
     * 需要和面试官 clarify which method is more heavy-loaded
     * - find trade-off
     */


    static class TwoSumMoreFind {
        private Set<Integer> sums;
        private Set<Integer> nums;

        public TwoSumMoreFind() {
            sums = new HashSet<>();
            nums = new HashSet<>();
        }

        public void add(int number) { // O(n^2)
            for (int num : nums) {
                sums.add(num + number);
            }
            nums.add(number);
        }

        public boolean find(int value) { // O(1)
            return sums.contains(value);
        }
    }

    static class TwoSumMoreAdd {
        private Map<Integer, Integer> nums;

        public TwoSumMoreAdd() {
            nums = new HashMap<>();
        }

        public void add(int number) { // O(1)
            nums.put(number, nums.getOrDefault(number, 0) + 1);
        }

        public boolean find(int value) { // O(n)
            for (int key : nums.keySet()) {
                int diff = value - key;
                if (key != diff && nums.containsKey(diff)) {
                    return true;
                }
                if (key == diff && nums.get(key) > 1) {
                    return true;
                }
            }
            return false;
        }
    }

/**
 * Your TwoSum object will be instantiated and called as such:
 * TwoSum obj = new TwoSum();
 * obj.add(number);
 * boolean param_2 = obj.find(value);
 */
}
