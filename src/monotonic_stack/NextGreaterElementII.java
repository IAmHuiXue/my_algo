package monotonic_stack;

/** https://leetcode.com/problems/next-greater-element-ii/ */

public class NextGreaterElementII {

    // 不是很直观
    // public int[] nextGreaterElements(int[] nums) {
    //     int n = nums.length;
    //     int[] res = new int[n];
    //     Deque<Integer> stack = new ArrayDeque<>();
    //     for (int i = 2 * n - 1; i >= 0; i--) {
    //         while (!stack.isEmpty() && nums[i % n] >= stack.peekFirst()) {
    //             stack.pollFirst();
    //         }
    //         res[i % n] = stack.isEmpty() ? -1 : stack.peekFirst();
    //         stack.offerFirst(nums[i % n]);
    //     }
    //     return res;
    // }


    // brute force
    public int[] nextGreaterElements(int[] nums) {
        int[] res = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            res[i] = -1;
            for (int j = 1; j < nums.length; j++) {
                if (nums[(i + j) % nums.length] > nums[i]) {
                    res[i] = nums[(i + j) % nums.length];
                    break;
                }
            }
        }
        return res;
    }
}
