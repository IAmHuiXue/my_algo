package data_structure;

import java.util.Arrays;

public class BoundedStackByArrayWithSize {
    private final int[] array = new int[5];
    private int size;

    public void push(int element) {
        if (isFull()) {
            throw new StackOverflowError();
        }
        array[size] = element;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        int result = array[size - 1];
        size--;
        return result;
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException();
        }
        return array[size - 1];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    private boolean isFull() {
        return size == array.length;
    }

    @Override
    public String toString() {
        int[] content = Arrays.copyOfRange(array, 0, size);
        return Arrays.toString(content);
    }
}
