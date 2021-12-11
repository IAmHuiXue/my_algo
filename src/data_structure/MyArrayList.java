package data_structure;

import java.util.Arrays;

public class MyArrayList {
    private int[] array;
    private int size;

    public MyArrayList(int initialCapacity) {
        this.array = new int[initialCapacity];
        this.size = 0;
    }

    public void insert(int element) {
        // if the array is full, resize it
        if (size == array.length) {
            array = Arrays.copyOf(array, (int) (size * 1.5));
        }
        // add the new element at the end
        array[size] = element;
        size++;
    }

    @Override
    public String toString() {
        int[] content = Arrays.copyOfRange(array, 0, size);
        return Arrays.toString(content);
    }

    public void print() {
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }

    public void removeAT(int index) {
        // validate the index
        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("index is illegal");
        }
        // shift the items to the left to fill the hole
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    public int indexOf(int value) {
        for (int i = 0; i < size; i++) {
            if (array[i] == value) {
                return i;
            }
        }
        return -1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public static void main(String[] args) {
        System.out.println(new MyArrayList(3).toString());
    }
}
