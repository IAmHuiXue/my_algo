package topK;

import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/kth-largest-element-in-a-stream/
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

    static class MySolution {
        private PriorityQueue<Integer> minHeap;
        private int k;

        public MySolution(int k, int[] nums) {
            // will maintain a minHeap of size k
            // this way we make sure minHeap.peek() is the kth the biggest element
            minHeap = new PriorityQueue<>();
            this.k = k;
            process(nums);
        }

        private void process(int[] nums) {
            for (int num : nums) {
                if (minHeap.size() < k) {
                    minHeap.offer(num);
                } else {
                    if (minHeap.peek() < num) {
                        minHeap.poll();
                        minHeap.offer(num);
                    }
                }
            }
        }

        public int add(int val) {
            if (minHeap.size() < k) {
                minHeap.offer(val);
            } else if (minHeap.peek() < val) {
                minHeap.poll();
                minHeap.offer(val);
            }
            return minHeap.peek();
        }
    }
}
