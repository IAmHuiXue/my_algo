package topK;

import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/kth-largest-element-in-a-stream/">...</a>
 */

public class KthLargestElementInAStream {
    private PriorityQueue<Integer> minHeap;
    private int k;

    public KthLargestElementInAStream(int k, int[] nums) {
        // will maintain a minHeap of size k
        // this way we make sure minHeap.peek() is the-kth biggest element
        minHeap = new PriorityQueue<>();
        this.k = k;
        for (int num : nums) {
            add(num);
        }
    }

    public int add(int val) {
        if (minHeap.size() < k || val > minHeap.peek()) {
            minHeap.offer(val);
        }
        if (minHeap.size() > k) {
            minHeap.poll();
        }
        return minHeap.peek();
    }

}
