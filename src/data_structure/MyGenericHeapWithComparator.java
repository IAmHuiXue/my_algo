package data_structure;

import java.util.Arrays;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 * An example implementation of capacity limited min Heap
 * containing only int values,
 * with the capacity to do update and poll at a specific index.
 *
 */

// for demonstration of passing comparator
    // here we require passing in a comparator in order to use the data structure

public class MyGenericHeapWithComparator<E> {
    private E[] array;
    private int size;
    private static final byte DEFAULT_CAPACITY = 10;
    private final Comparator<E> comparator;

    public MyGenericHeapWithComparator(E[] array, Comparator<E> comparator) {
        if (array == null || array.length == 0) {
            throw new IllegalArgumentException("input array can not be null or empty");
        }
        this.array = array;
        this.comparator = comparator;
        size = array.length; // !
        heapify();
    }

    public MyGenericHeapWithComparator(Comparator<E> comparator) {
        this(DEFAULT_CAPACITY, comparator);
    }

    public MyGenericHeapWithComparator(int cap, Comparator<E> comparator) {
        if (cap <= 0) {
            throw new IllegalArgumentException("capacity can not be <= 0");
        }
        this.comparator = comparator;
        array = (E[])(new Object[cap]);
    }

    public void heapSort() {
        /** 概念就是先把一个 input array heapify (O(n))
         *  然后再依次 poll 出 top 元素 (O(log(n))
         *  所以 heapSort 的总时间复杂度是 O(nlog(n))
         */
    }

    public E peek() { // O(1)
        if (size == 0) {
            throw new NoSuchElementException("heap is empty");
        }
        return array[0];
    }

    public E poll() { // O(log(n))
        if (size == 0) {
            throw new NoSuchElementException("heap is empty");
        }
        E res = array[0];
        array[0] = array[--size];
        // we need to firstly size-- to make sure the array
        // now is legal before percolating
        percolateDown(0);
        return res;
    }

    public void offer(E ele) { // O(log(n))
        if (size == array.length) {
            expand();
        }
        array[size++] = ele;
        // we need to firstly size++ to make sure the array
        // now is legal before percolating
        // then the index of ele becomes size - 1
        percolateUp(size - 1);
    }

    public E update(int index, E ele) {
        // return the original value
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException("invalid index range");
        }
        E res = array[index];
        array[index] = ele;
        if (comparator.compare(ele, res) > 0) { // if ele 的优先级低
            percolateDown(index);
        } else {
            percolateUp(index);
        }
        return res;
    }

    public boolean replace(E source, E target) {
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
            if (comparator.compare(array[parentIndex], array[index]) > 0) {
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
            if (rightChild < size && comparator.compare(array[rightChild], array[childCandidate]) < 0) {
                childCandidate = rightChild;
            }
            if (comparator.compare(array[childCandidate], array[index]) < 0) {
                swap(childCandidate, index);
                index = childCandidate;
            } else {
                return;
            }
        }
    }

    private void swap(int i, int j) {
        E tmp = array[i];
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
