package pq;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * <a href="https://leetcode.com/problems/find-median-from-data-stream/">https://leetcode.com/problems/find-median-from-data-stream/</a>
 */

public class FindMedianFromDataStream {
    // MaxHeap stores the smaller half
    // MinHeap stores the larger half
    // balance the size of each heap
    // therefore, we can O(log(n)) get the result provided by minHeap.peek() and maxHeap.peek()
    private PriorityQueue<Integer> maxHeapSmallerHalf = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> minHeapLargerHalf = new PriorityQueue<>();
    private boolean even = true;

    public double findMedian() {
        if (even) {
            return (maxHeapSmallerHalf.peek() + minHeapLargerHalf.peek()) / 2.0;
        }
        return maxHeapSmallerHalf.peek() * 1.0;
    }

    public void addNum(int num) {
        if (even) {
            // 设计的是当 even 的时候将下一个元素放在 maxHeapSmallerHalf 里
            // 因为需要严格保证的每个 maxHeapSmallerHalf 里所有元素都要大于等于 minHeapLargerHalf
            // 因此先将下一个元素放进 minHeapLargerHalf 里，再从 minHeapLargerHalf 里面 poll 出最大的放回给 maxHeapSmallerHalf
            // 反之亦然
            minHeapLargerHalf.offer(num);
            maxHeapSmallerHalf.offer(minHeapLargerHalf.poll());
        } else {
            maxHeapSmallerHalf.offer(num);
            minHeapLargerHalf.offer(maxHeapSmallerHalf.poll());
        }
        even = !even;
    }
}
