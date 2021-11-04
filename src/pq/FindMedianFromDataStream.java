package pq;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/find-median-from-data-stream/
 */

public class FindMedianFromDataStream {
    // MaxHeap stores the smaller half
    // MinHeap stores the larger half
    // balance the size of each heap
    // therefore, we can O(1) get the result provided by minHeap.peek() and maxHeap.peek()
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>(Collections.reverseOrder());
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>();
    private boolean even = true;

    public double findMedian() {
        if (even) {
            return (minHeap.peek() + maxHeap.peek()) / 2.0;
        }
        return minHeap.peek();
    }

    public void addNum(int num) {
        if (even) {
            // 设计的是当 even 的时候将下一个元素放在 minHeap 里
            // 因为需要严格保证的每个minHeap 里所有元素都要大于等于 maxHeap
            // 因此先将下一个元素放进 maxHeap 里，再从max 里面 poll 出最大的放回给 min
            // 反之亦然
            maxHeap.offer(num);
            minHeap.offer(maxHeap.poll());
        } else {
            minHeap.offer(num);
            maxHeap.offer(minHeap.poll());
        }
        even = !even;
    }
}
