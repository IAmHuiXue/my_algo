package stack;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/** <a href="https://leetcode.com/problems/asteroid-collision/">...</a> */

public class AsteroidCollision {
    public int[] asteroidCollision(int[] asteroids) {
        // maintain a stack, only store asteroids moving towards right
        // if a[i] > 0, push to stack
        // if a[i] < 0,
            // if stack is empty, add it to res list
            // else if stack.top > abs(a[i]), continue
            // else pop and keep checking abs(a[i]) and stack.top

        Deque<Integer> stack = new ArrayDeque<>();
        List<Integer> res = new ArrayList<>();

        for (int a : asteroids) {
            // a != 0
            if (a > 0) {
                stack.offerFirst(a);
                continue;
            }
            boolean bothExploded = false;
            while (!stack.isEmpty()) {
                if (stack.peekFirst() > Math.abs(a)) {
                    break;
                }
                // stack.top <= abs(a)
                int p = stack.pollFirst();
                // if p = Math.abs(a), we need to stop comparing further
                if (p == Math.abs(a)) {
                    bothExploded = true;
                    break;
                }
            }
            if (bothExploded) {
                continue;
            }
            if (stack.isEmpty()) {
                res.add(a);
            }
        }

        Deque<Integer> buffer = new ArrayDeque<>();
        while (!stack.isEmpty()) {
            buffer.offerFirst(stack.pollFirst());
        }
        while (!buffer.isEmpty()) {
            res.add(buffer.pollFirst());
        }

        int[] ans = new int[res.size()];
        for (int i = 0; i < ans.length; i++) {
            ans[i] = res.get(i);
        }
        return ans;
    }
}
