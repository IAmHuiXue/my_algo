package monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;

/** https://leetcode.com/problems/daily-temperatures/ */

public class DailyTemperatures {

    /** next greater element 的小变种
     *  stack 里面存 index，这样方便计算 days 的长度
     *
     * */

    public int[] dailyTemperatures(int[] temperatures) {
        int[] res = new int[temperatures.length];

        // store index
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = temperatures.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && temperatures[i] >= temperatures[stack.peekFirst()]) {
                stack.pollFirst();
            }
            res[i] = stack.isEmpty() ? 0 : stack.peekFirst() - i;
            stack.offerFirst(i);
        }
        return res;
    }
}
