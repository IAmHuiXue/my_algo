package two_sum;

import java.util.*;

/**
 * find all possible k integers where their sum is the target
 */

public class KSum {
    /**
     * All K Sum problem can be divided into two problems:
     * - 2 Sum Problem
     * - Reduce K Sum problem to K – 1 Sum Problem
     * <p>
     * 从4Sum和3Sum，我们可以看出对于KSum的通用套路：将KSum转化为K-1 Sum，最后用2Sum的Two Pointer求解。
     * 注意要点：先排序，去除/跳过重复元素
     */
    static class ClassicTwoPointer {
        public static List<List<Integer>> kSum(int[] nums, int k, int target) {
            // assume k > 1
            Arrays.sort(nums);
            // from 2Sum, 3Sum, to kSum
            // kSum 的时候 instead of tediously using i, j, k ...，使用一个 start pointer，recursively 的表示左挡板
            return kSumHelper(nums, k, 0, target);
        }

        private static List<List<Integer>> kSumHelper(int[] nums, int k, int start, int target) {
            // base case
            if (k == 2) {
                return twoSumWithPointer(nums, start, target);
            }
            List<List<Integer>> res = new ArrayList<>();
            // 就是 two sum 的变种，只是由于是 k sum，用 for loop 加一个 start pointer 用来表示
            for (int i = start; i < nums.length - k + 1; i++) { // !
                if (i > start && nums[i] == nums[i - 1]) {
                    continue;
                }
                List<List<Integer>> tmp = kSumHelper(nums, k - 1, i + 1, target - nums[i]);
                for (List<Integer> t : tmp) {
                    t.add(0, nums[i]);
                }
                res.addAll(tmp);
            }
            return res;
        }

        private static List<List<Integer>> twoSumWithPointer(int[] nums, int start, int target) {
            // to perform 2Sum in the range of nums[start, nums.length - 1]
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
                    // cannot use Arrays.asList() here, which returns a fixed size of list.
                    // However, we need to expand and build the list later recursively.
//                    res.add(Arrays.asList(nums[i++], nums[j--]));
                    List<Integer> cur = new ArrayList<>();
                    cur.add(nums[i++]);
                    cur.add(nums[j--]);
                    res.add(new LinkedList<>(cur));
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
            cur.add(num);
            dfs(nums, index + 1, k, curTarget - num, cur, result);
            cur.remove(cur.size() - 1);

            dfs(nums, index + 1, k, curTarget, cur, result);
        }
        // time: O(2^n)
    }

    public static void main(String[] args) {
        System.out.println(ClassicTwoPointer.kSum(new int[]{3, 4, 0, -1, 2, 0, 5}, 3, 5));
    }
}
