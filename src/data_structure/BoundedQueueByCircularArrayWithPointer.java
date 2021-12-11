package data_structure;

public class BoundedQueueByCircularArrayWithPointer {
    int[] array;
    int head;
    int tail;
    /** Initialize your data structure here. Set the size of the queue to be k. */
    public BoundedQueueByCircularArrayWithPointer(int k) {
        // in order to maintain the capacity without using size pointer
        // we need to make sure the internal array has a length of at least 2

        // if k <= 0, throw exception
        array = new int[k + 1];
        head = 0;
        tail = 1;

        // head + 1 = tail --> empty
        // head = tail --> full
        // head + 1 points to the top element
        // tail points to the next available position
    }

    /** Insert an element into the circular queue. Return true if the operation is successful. */
    public boolean enQueue(int value) {
        if (isFull()) {
            return false;
        }
        array[tail] = value;
        tail = tail + 1 == array.length ? 0 : tail + 1;
        return true;
    }

    /** Delete an element from the circular queue. Return true if the operation is successful. */
    public boolean deQueue() {
        if (isEmpty()) {
            return false;
        }
        head = head + 1 == array.length ? 0 : head + 1;
        return true;
    }

    /** Get the front item from the queue. */
    public int Front() {
        if (isEmpty()) {
            return -1;
        }
        int pointer = head + 1 == array.length ? 0 : head + 1;
        return array[pointer];
    }

    /** Get the last item from the queue. */
    public int Rear() {
        if (isEmpty()) {
            return -1;
        }
        int rear = tail - 1 == -1 ? array.length - 1 : tail - 1;
        return array[rear];
    }

    /** Checks whether the circular queue is empty or not. */
    public boolean isEmpty() {
        int pointer = head + 1 == array.length ? 0 : head + 1;
        return pointer == tail;
    }

    /** Checks whether the circular queue is full or not. */
    public boolean isFull() {
        return head == tail;
    }
}
