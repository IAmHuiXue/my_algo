package data_structure;

import java.util.Arrays;


/**
 * class name needs to have < >
 * constructor name does not need to have < >
 * initialize array needs to downcast (Entry<K, V>[]) (new Entry[capacity])
 * when equal or compare two generics need to avoid NPE
 */

public class MyThreadSafeGenericsHashMap<K, V> {
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;
    private static final int DEFAULT_CAPACITY = 16;
    private static final float SCALE_FACTOR = 1.5f;

    private int size;
    private Entry<K, V>[] array;
    private float loadFactor;

    public static class Entry<K, V> {
        Entry<K, V> next;
        final K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * Synchronize for these methods within a nested class?
         * No, as we need also to consider the efficiency of the map
         * considering these methods will not be as frequent as other public
         * methods, we choose not to synchronize them
         */
        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    public MyThreadSafeGenericsHashMap() {
        this(DEFAULT_LOAD_FACTOR, DEFAULT_CAPACITY);
    }

    public MyThreadSafeGenericsHashMap(float loadFactor, int capacity) {
        this.loadFactor = loadFactor;
        array = (Entry<K, V>[]) (new Entry[capacity]);
    }

    /** Synchronize for public and /or read-only methods? Yes */

    /**
     * synchronized
     */
    // return -1 if there is no key k before
    public synchronized V put(K k, V v) {
        int hashNum = hash(k);
        int index = getIndex(hashNum);
        Entry<K, V> entry = array[index];
        while (entry != null) {
            if (equalsKey(entry.key, k)) {
                V preValue = entry.value;
                entry.value = v;
                return preValue;
            }
            entry = entry.next;
        }
        // cannot find k in the entry list
        entry = new Entry<>(k, v);
        // add it to the head of original
        entry.next = array[index];
        array[index] = entry;
        size++;
        if (needRehash()) {
            rehash();
        }
        return null;
    }

    /**
     * synchronized
     */
    public synchronized V get(K k) {
        int hashNum = hash(k);
        int index = getIndex(hashNum);
        Entry<K, V> entry = array[index];
        while (entry != null) {
            if (equalsKey(entry.key, k)) {
                return entry.value;
            }
            entry = entry.next;
        }
        return null;
    }

    /**
     * synchronized
     */
    public synchronized V remove(K k) {
        int hashNum = hash(k);
        int index = getIndex(hashNum);
        Entry<K, V> entry = array[index];
        Entry<K, V> prev = null;
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

    /**
     * synchronized
     */
    public boolean containsKey(K k) {
        Entry<K, V> entry = array[getIndex(hash(k))];
        while (entry != null) {
            if (equalsKey(entry.key, k)) {
                return true;
            }
            entry = entry.next;
        }
        return false;
    }

    /**
     * synchronized
     */
    public boolean containsValue(V value) {
        for (Entry<K, V> entry : array) {
            while (entry != null) {
                if (equalsValue(entry.value, value)) {
                    return true;
                }
                entry = entry.next;
            }
        }
        return false;
    }

    /**
     * synchronized
     */
    public synchronized int size() {
        return size;
    }

    /**
     * synchronized
     */
    public synchronized boolean isEmpty() {
        return size == 0;
    }

    /**
     * synchronized
     */
    public void clear() {
        Arrays.fill(array, null);
        size = 0; //  remember to reset size
    }

    /**
     * Synchronize for private methods? No, as since public methods are synchronized
     * already, private methods will not cause data races
     */
    private int hash(K k) {
        if (k == null) {
            return 0;
        }
        return k.hashCode() & 0x7fffffff;
//        return Math.abs(k.hashCode()); // overflow potential
    }

    private int getIndex(int hashNum) {
        return hashNum % array.length;
    }

    private boolean equalsKey(K one, K two) {
        if (one == null) {
            return two == null;
        }
        return one.equals(two);
    }

    private boolean equalsValue(V one, V two) {
        if (one == null) {
            return two == null;
        }
        return one.equals(two);
    }

    private void rehash() {
        // need to create a longer array size is 1.5 times
        // for each entry, rehash -> new index
        Entry<K, V>[] newArray = (Entry<K, V>[]) (new Entry[(int) (array.length * SCALE_FACTOR)]);
        for (Entry<K, V> entry : array) {
            while (entry != null) {
                Entry<K, V> nxt = entry.next;
                int newIndex = hash(entry.key) % newArray.length; // !
                entry.next = newArray[newIndex];
                newArray[newIndex] = entry;
                entry = nxt;
            }
        }
        array = newArray;
    }

    private boolean needRehash() {
        return size == loadFactor * array.length;
    }

}
