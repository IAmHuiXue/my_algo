package data_structure;

import java.util.ArrayDeque;
import java.util.NoSuchElementException;
import java.util.Queue;

/**
 * https://leetcode.com/problems/flatten-2d-vector/
 */

public class Flatten2DVector {

    // this method is bad. One of the main purposes of an Iterator is to minimize the use of auxiliary space.
    // We should try to utilize the existing data structure as much as possible, only adding as much extra
    // space as needed to keep track of the next value.
    // In some situations, the data structure we want to iterate over is too large to even fit in memory anyway.

    private Queue<Integer> q;

    public Flatten2DVector(int[][] vec) {
        q = new ArrayDeque<>();
        for (int[] array : vec) {
            for (int num : array) {
                q.offer(num);
            }
        }
    }

    public int next() {
        return q.poll();
    }

    public boolean hasNext() {
        return !q.isEmpty();
    }
}

class Vector2D {

    private int[][] vector;
    private int inner = 0;
    private int outer = 0;

    public Vector2D(int[][] v) {
        vector = v;
    }

    // If the current outer and inner point to an integer, this method does nothing.
    // Otherwise, inner and outer are advanced until they point to an integer.
    // If there are no more integers, then outer will be equal to vector.length
    // when this method terminates.
    private void advanceToNext() {
        // While outer is still within the vector, but inner is over the
        // end of the inner list pointed to by outer, we want to move
        // forward to the start of the next inner vector.
        while (outer < vector.length && inner == vector[outer].length) { // if inner is pointing to any empty array, skip
            inner = 0;
            outer++;
        }
    }

    public int next() {
        // As per Java specs, throw an exception if there's no next.
        // This will also ensure the pointers point to an integer otherwise.
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        // Return current element and move inner so that is after the current
        // element.
        return vector[outer][inner++];
    }

    public boolean hasNext() {
        // Ensure the position pointers are moved such they point to an integer,
        // or put outer = vector.length.
        advanceToNext();
        // If outer = vector.length then there are no integers left, otherwise
        // we've stopped at an integer and so there's an integer left.
        return outer < vector.length;
    }
}