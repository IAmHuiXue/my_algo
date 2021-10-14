package monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;

/** https://leetcode.com/problems/largest-rectangle-in-histogram/ */

public class LargestRectangleInHistogram {

    /**
     * 当图形处在上升期 (height[i] < height[i+1]，不用计算面积。因为在这种情况下再往前移动一格所能得到的面积必然更大。
     * 当图形处在下降期，就要开始计算当前矩形的面积了。
     *
     * 因为需要 维护一个 monotonic-increasing stack
     *
     * */

    public int largestRectangleArea(int[] heights) {
        Deque<Integer> stack = new ArrayDeque<>();
        int area = 0;
        for (int i = 0; i < heights.length; i++) {
            while (!stack.isEmpty() && heights[stack.peekFirst()] >= heights[i]) {
                // 当 heights[i] <= heights[stack.peekFirst()] 的时候，也就是 该计算以 heights[stack.peekFirst()] 为高度的
                // 矩形的时候了，因为 heights[i] 比它小，没法形成更大的矩形，所以 poll 出来，计算面积
                int heightToCal = heights[stack.pollFirst()];
                // width
                int width = i - (stack.isEmpty() ? 0 : stack.peekFirst() + 1);
                area = Math.max(area, heightToCal * width);
            }
            stack.offerFirst(i);
        }
        // 清空栈里的剩余元素并更新 global area
        while (!stack.isEmpty()) {
            int heightToCal = heights[stack.pollFirst()];
            int width = heights.length - (stack.isEmpty() ? 0 : stack.peekFirst() + 1); // ！
            area = Math.max(area, heightToCal * width);
        }
        return area;
    }
}
