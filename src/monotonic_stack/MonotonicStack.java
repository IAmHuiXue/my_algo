package monotonic_stack;

import java.util.ArrayDeque;
import java.util.Deque;

public class MonotonicStack {
    // please find the previous less element of each element in an array with O(n) time

    static int[] previousLessElement(int[] arr) {

        // Instead of directly pushing the element itself, here for simplicity, we push the index.
        // We do some record when the index is pushed into the stack.
        // previousLess[i] = j means A[j] is the previous less element of A[i].
        // previousLess[i] = -1 means there is no previous less element of A[i].

        //  arr = [3, 7, 8, 4]
        //  res = [-1, 3, 7, 3]

        int[] res = new int[arr.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peekFirst()] >= arr[i]) {
                stack.pollFirst();
            }
            res[i] = stack.isEmpty() ? - 1 : stack.peekFirst();
            stack.offerFirst(i); // remember to offer the current index
        }
        return res;
    }

    // please find the next less element of each element in an array with O(n) time

    static int[] nextLessElement(int[] arr) {

        //  arr = [3, 7, 8, 4]
        //  res = [-1, 4, 4,-1]

        int[] res = new int[arr.length];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peekFirst()] >= arr[i]) {
                stack.pollFirst();
            }
            res[i] = stack.isEmpty() ? - 1 : stack.peekFirst();
            stack.offerFirst(i); // remember to offer the current index
        }
        return res;
    }
}
