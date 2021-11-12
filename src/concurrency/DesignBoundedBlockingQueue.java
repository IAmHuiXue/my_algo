package concurrency;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * https://leetcode.com/problems/design-bounded-blocking-queue/
 */

public class DesignBoundedBlockingQueue {
    Queue<Integer> queue;
    int size;

    // Object lock;
    public DesignBoundedBlockingQueue(int capacity) {
        queue = new ArrayDeque<>();
        size = capacity;
        // lock = new Object();
    }

    public synchronized void enqueue(int element) throws InterruptedException {
        // synchronized(lock) {
        while (queue.size() == size) {
            wait();
        }
        queue.offer(element);
        notifyAll();
        // }
    }

    public synchronized int dequeue() {
        // synchronized(lock) {
        while (queue.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        // }
        int res = queue.poll();
        notifyAll();
        return res;
    }

    public synchronized int size() {
        return queue.size();
    }
}
