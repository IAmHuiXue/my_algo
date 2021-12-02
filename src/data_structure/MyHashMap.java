package data_structure;

import java.util.Arrays;

public class MyHashMap {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float SCALE_FACTOR = 1.5f;

    private int size;
    private Entry[] array;
    private float loadFactor;

    public static class Entry {
        Entry next;
        final String key;
        Integer value;

        public Entry(String key, Integer value) {
            this.key = key;
            this.value = value;
        }
    }

    public MyHashMap() {
        this(DEFAULT_LOAD_FACTOR, DEFAULT_CAPACITY);
    }

    public MyHashMap(float loadFactor, int capacity) {
        if (capacity <= 0 || loadFactor <= 0) {
            throw new IllegalArgumentException("Invalid input");
        }
        this.loadFactor = loadFactor;
        array = new Entry[capacity];
    }

    public Integer put(String k, int v) {
        int hashNum = hash(k);
        int index = getIndex(hashNum);
        Entry entry = array[index];
        while (entry != null) {
            if (equalsKey(entry.key, k)) {
                int preValue = entry.value;
                entry.value = v;
                return preValue;
            }
            entry = entry.next;
        }
        // cannot find k in the entry list
        entry = new Entry(k, v);
        // add it to the head of original
        entry.next = array[index];
        array[index] = entry;
        size++;
        if (needRehash()) {
            rehash();
        }
        return null;
    }

    public Integer get(String k) {
        int hashNum = hash(k);
        int index = getIndex(hashNum);
        Entry entry = array[index];
        while (entry != null) {
            if (equalsKey(entry.key, k)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    public Integer remove(String k) {
        int hashNum = hash(k);
        int index = getIndex(hashNum);
        Entry entry = array[index];
        Entry prev = null;
        while (entry != null) {
            if (equalsKey(entry.key, k)) {
                if (prev == null) {
                    array[index] = entry.next;
                } else {
                    prev.next = entry.next;
                }
                size--;
                entry.next = null;
                return entry.value;
            }
            prev = entry;
            entry = entry.next;
        }
        return null;
    }

    public boolean containsKey(String k) {
        Entry entry = array[getIndex(hash(k))];
        while (entry != null) {
            if (equalsKey(entry.key, k)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    public boolean containsValue(int value) {
        for (Entry entry : array) {
            while (entry != null) {
                if (entry.value == value) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    private int hash(String k) {
        if (k == null) {
            return 0;
        }
        return k.hashCode() & 0x7fffffff;
//        return Math.abs(k.hashCode()); // overflow potential
    }

    private int getIndex(int hashNum) {
        return hashNum % array.length;
    }

    private boolean equalsKey(String one, String two) {
        if (one == null) {
            return two == null;
        }
        return one.equals(two);
    }

    private void rehash() {
        // need to create a longer array size is 1.5 times
        // for each entry, rehash -> new index
        Entry[] oldArray = array;
        array = new Entry[(int) (array.length * SCALE_FACTOR)];
        for (Entry entry : oldArray) {
            while (entry != null) {
                Entry next = entry.next;
                int newIndex = getIndex(hash(entry.key));
                entry.next = array[newIndex];
                array[newIndex] = entry;
                entry = next;
            }
        }

        // another way
//        Entry[] newArray = new Entry[(int) (array.length * SCALE_FACTOR)];
//        for (Entry entry : array) {
//            while (entry != null) {
//                // record the next node
//                Entry nxt = entry.next;
//                int newIndex = hash(entry.key) % newArray.length; // !
//                entry.next = newArray[newIndex];
//                newArray[newIndex] = entry;
//                entry = nxt;
//            }
//        }
//        array = newArray;


    }

    private boolean needRehash() {
        return size == loadFactor * array.length;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void clear() {
        Arrays.fill(array, null);
        size = 0; //  remember to reset size
    }
}
