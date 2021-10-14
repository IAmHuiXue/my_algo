package data_structure;

import java.util.Arrays;
import java.util.NoSuchElementException;

/**
 * An example implementation of capacity limited min Heap
 * containing only int values,
 * with the capacity to do update and poll at a specific index.
 *
 */

public class MyMinHeap {
    private int[] array;
    private int size;
    private static final byte DEFAULT_CAPACITY = 10;

    public MyMinHeap(int[] array) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("input array can not be null or empty");
        }
        this.array = array;
        size = array.length; // !
        heapify();
    }

    public MyMinHeap() {
        this(DEFAULT_CAPACITY);
    }

    public MyMinHeap(int cap) {
        if (cap <= 0) {
            throw new IllegalArgumentException("capacity can not be <= 0");
        }
        array = new int[cap];
    }

    public void heapSort() {
        /** 概念就是先把一个 input array heapify (O(n))
         *  然后再依次 poll 出 top 元素 (O(log(n))
         *  所以 heapSort 的总时间复杂度是 O(nlog(n))
         */
    }

    public int peek() { // O(1)
        if (size == 0) {
            throw new NoSuchElementException("heap is empty");
        }
        return array[0];
    }

    public int poll() { // O(log(n))
        if (size == 0) {
            throw new NoSuchElementException("heap is empty");
        }
        int res = array[0];
        array[0] = array[--size];
        // we need to firstly size-- to make sure the array
        // now is legal before percolating
        percolateDown(0);
        return res;
    }

    public void offer(int ele) { // O(log(n))
        if (size == array.length) {
            expand();
        }
        array[size++] = ele;
        // we need to firstly size++ to make sure the array
        // now is legal before percolating
        // then the index of ele becomes size - 1
        percolateUp(size - 1);
    }

    public int update(int index, int ele) {
        // return the original value
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("invalid index range");
        }
        int res = array[index];
        array[index] = ele;
        if (ele > res) {
            percolateDown(index);
        } else {
            percolateUp(index);
        }
        return res;
    }

    public boolean replace(int source, int target) {
        if (size == 0) {
            throw new NoSuchElementException("heap is empty");
        }
        for (int i = 0; i < size; i++) {
            if (array[i] == source) {
                array[i] = target;
                update(i, target);
                return true;
            }
        }
        return false;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == array.length;
    }

    private void percolateUp(int index) {
        // compare the index node with parents
        // keep percolating up until either reach the top or
        // the heap property is maintained
        while (index > 0) { // index == 0 --> becomes parent
            int parentIndex = (index - 1) / 2;
            if (array[parentIndex] > array[index]) {
                swap(index, parentIndex);
                index = parentIndex;
            } else {
                return; // need to terminate the loop!
            }
        }
    }

    private void percolateDown(int index) {
        // check if index is legal
        if (size < 2) {
            // to handle the corner case where size == 1
            return;
        }
        // compare the index node with children
        // keep percolating down until either reach the leaf or
        // the heap property is maintained
        // the last parentIndex = (size - 1 - 1) / 2 = size / 2 - 1
        while (index <= size / 2 - 1) { // when index > size / 2 - 1, it becomes leaf node
            int childCandidate = 2 * index + 1;
            int rightChild = 2 * index + 2;
            // determine if right child exists
            // then compare with rChild and determine whether to swap
            // the ONLY possible case where rChild does not exist is when the last node is a left child
            if (rightChild < size && array[rightChild] < array[childCandidate]) {
                childCandidate = rightChild;
            }
            if (array[childCandidate] < array[index]) {
                swap(childCandidate, index);
                index = childCandidate;
            } else {
                return;
            }
        }
    }

    private void swap(int i, int j) {
        int tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    /** convert an array into a heap */
    private void heapify() { // O(n)
        // percolate down from the parent of the last leaf node
        int parentIndex = size / 2 - 1;
        for (int i = parentIndex; i >= 0; i--) {
            // the index of the parent of the last leaf node
            // ((size - 1) - 1) / 2 = size / 2 - 1
            // i == 0 need to do loop as while
            // that is the root node
            percolateDown(parentIndex);
        }
    }

    private void expand() {
        array = Arrays.copyOf(array, 2 * array.length);
    }
}
