package two_sum;

import java.util.*;

/** find all possible k integers where their sum is target */

public class KSum {

    static class ClassicTwoPointer {
        public static  List<List<Integer>> kSum(int[] nums, int k, int target) {
            Arrays.sort(nums);
            return kSumHelper(nums, k, 0, target);
        }

        private static List<List<Integer>> kSumHelper(int[] nums, int k, int start, int target) {
            // 去重 [111111]
            Set<List<Integer>> res = new HashSet<>();
            if (start == nums.length) {
                return new ArrayList<>(res);
            }
            if (k == 2) {
                return twoSum(nums, start, target);
            }
            // 就是 two sum 的变种，只是由于是 k sum，用 for loop 加一个 start pointer 用来表示
            for (int i = start; i < nums.length; i++) {
                if (start > 0 && nums[start] == nums[start - 1]) {
                    start++;
                    continue;
                }
                List<Integer> tmp;
                for (List<Integer> subset : kSumHelper(nums, k - 1, i + 1, target - nums[i])) {
                    tmp = new ArrayList<>(subset);
                    tmp.add(nums[i]);
                    res.add(tmp);
                }
            }
            return new ArrayList<>(res);
        }

        private static List<List<Integer>> twoSum(int[] nums, int start, int target) {
            List<List<Integer>> res = new ArrayList<>();
            int i = start;
            int j = nums.length - 1;
            while (i < j) {
                if (i > start && nums[i] == nums[i - 1]) {
                    i++;
                    continue;
                }
                int sum = nums[i] + nums[j];
                if (sum == target) {
                    res.add(Arrays.asList(nums[i++], nums[j--]));
                } else if (sum < target) {
                    i++;
                } else {
                    j--;
                }
            }
            return res;
        }
        // time: O(n^k)
    }

    static class DFSBruteForce {
        public List<List<Integer>> kSum(int[] nums, int k, int target) {
            // 转化成 subset 问题， 选 size 为 k 的 subset 和为 target
            Arrays.sort(nums);
            List<List<Integer>> result = new ArrayList<>();
            dfs(nums, 0, k, target, new ArrayList<>(), result);
            return result;
        }

        private void dfs(int[] nums, int index, int k, int curTarget, List<Integer> cur, List<List<Integer>> result) {
            if (cur.size() == k) {
                if (curTarget == 0) {
                    result.add(new ArrayList<>(cur));
                }
                return;
            }
            if (index == nums.length) {
                return;
            }
            int num = nums[index];
            if (num > curTarget) { // 剪枝，因为 sort 过了，如果此时 num 已经大于剩下的 target，那么后面都不需要考虑
                return;
            }
            curTarget -= num;
            cur.add(num);
            dfs(nums, index + 1, k, curTarget, cur, result);
            curTarget += num;
            cur.remove(cur.size() - 1);
            dfs(nums, index + 1, k, curTarget, cur, result);
        }
        // time: O(2^n)
    }

    public static void main(String[] args) {
        System.out.println(ClassicTwoPointer.kSum(new int[]{1,1,1,1,1,1,1,1,1,1,1}, 3, 3));
    }
}
