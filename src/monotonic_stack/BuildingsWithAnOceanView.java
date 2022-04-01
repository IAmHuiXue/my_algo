package monotonic_stack;

import java.util.*;

/** https://leetcode.com/problems/buildings-with-an-ocean-view/ */

public class BuildingsWithAnOceanView {
    static class Optimized {
        public int[] findBuildings(int[] heights) {
            // height[i] -> [1, 10^5]
            int curMax = 0;
            List<Integer> tmp = new ArrayList<>();
            for (int i = heights.length - 1; i >= 0; i--) {
                if (heights[i] > curMax) {
                    tmp.add(i);
                    curMax = heights[i];
                }
            }
            int[] res = new int[tmp.size()];
            int j = tmp.size() - 1;
            for (int i = 0; i < res.length; i++) {
                res[i] = tmp.get(j--);
            }
            return res;
        }
    }

    static class MonotonicDecreasingStack {
        public int[] findBuildings(int[] heights) {
            List<Integer> tmp = new ArrayList<>();
            Deque<Integer> stack = new ArrayDeque<>();
            for (int i = heights.length - 1; i >= 0; i--) {
                // If the building to the right is smaller, we can pop it.
                while (!stack.isEmpty() && heights[stack.peekFirst()] < heights[i]) {
                    stack.pollFirst();
                }
                // If the stack is empty, it means there is no building to the right
                // that can block the view of the i building.
                if (stack.isEmpty()) {
                    tmp.add(i);
                }
                // Push the i building in the stack.
                stack.offerFirst(i);
            }
            // Push elements from tmp to res array in reverse order.
            int[] res = new int[tmp.size()];
            for (int i = 0; i < tmp.size(); i++) {
                res[i] = tmp.get(tmp.size() - 1 - i);
            }
            return res;
        }
    }

    static class Stack {
        public int[] findBuildings(int[] heights) {
            // stack to store indices of the element which is the highest visited so far
            Deque<Integer> stack = new ArrayDeque<>();
            List<Integer> tmp = new ArrayList<>();
            for (int i = heights.length - 1; i >= 0; i--) {
                if (stack.isEmpty() || heights[i] > heights[stack.peekFirst()]) {
                    stack.offerFirst(i);
                }
            }
            int[] res = new int[stack.size()];
            for (int i = 0; i < res.length; i++) {
                res[i] = stack.pollFirst();
            }
            return res;
        }
    }
}
