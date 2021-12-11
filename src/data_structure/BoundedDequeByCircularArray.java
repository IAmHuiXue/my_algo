package data_structure;

public class BoundedDequeByCircularArray {
    int[] array;
    int head; // points to the top element
    int tail; // points to the next available position
    int size;
    /** Initialize your data structure here.
     * Set the size of the deque to be k. */
    public BoundedDequeByCircularArray(int k) {
        // assume k > 0
        array = new int[k];
        int head = tail = 0;
        size = 0;
    }

    /** Adds an item at the front of Deque.
     * Return true if the operation is successful. */
    public boolean insertFront(int value) {
        if (size == array.length) {
            return false;
        }
        head = head - 1 == -1 ? array.length - 1: head - 1;
        array[head] = value;
        size++;
        return true;
    }

    /** Adds an item at the rear of Deque.
     * Return true if the operation is successful. */
    public boolean insertLast(int value) {
        if (size == array.length) {
            return false;
        }
        array[tail] = value;
        tail = tail + 1 == array.length ? 0 : tail + 1;
        size++;
        return true;
    }

    /** Deletes an item from the front of Deque.
     * Return true if the operation is successful. */
    public boolean deleteFront() {
        if (size == 0) {
            return false;
        }
        head = head + 1 == array.length ? 0 : head + 1;
        size--;
        return true;
    }

    /** Deletes an item from the rear of Deque.
     * Return true if the operation is successful. */
    public boolean deleteLast() {
        if (size == 0) {
            return false;
        }
        tail = tail - 1 == -1 ? array.length - 1 : tail - 1;
        size--;
        return true;
    }

    /** Get the front item from the deque. */
    public int getFront() {
        if (size == 0) {
            return -1;
        }
        return array[head];
    }

    /** Get the last item from the deque. */
    public int getRear() {
        if (size == 0) {
            return -1;
        }
        int rear = tail - 1 == -1 ? array.length - 1: tail - 1;
        return array[rear];
    }

    /** Checks whether the circular deque is empty or not. */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Checks whether the circular deque is full or not. */
    public boolean isFull() {
        return size == array.length;
    }
}
