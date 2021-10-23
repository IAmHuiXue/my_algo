package data_structure;

import java.util.HashMap;
import java.util.Map;

// we need to perform update operations on both ends
// so a doubly linked list is desired
// using HashMap because a cache is usually a key-value pair

public class LRUCacheGenerics<K, V> {
    private Map<K, Node<K, V>> records;
    private Node<K, V> head;  // the least recent one
    private Node<K, V> tail;  // the most recent one
    private int limit;

    // Storage layer --> linked list
    // Index layer --> HashMap

    static class Node<K, V> {
        K key;
        V value;
        Node<K, V> next;
        Node<K, V> prev;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCacheGenerics(int capacity) {
        records = new HashMap<>();
        limit = capacity;
    }

    public V get(K key) {
        Node<K, V> node = records.get(key);
        if (node == null) {
            return null;
        }
        evict(node);
        add(node);
        return node.value;
    }

    public void put(K key, V value) {
        Node<K, V> node = records.get(key);
        if (node == null) {
            if (records.size() < limit) {
                node = new Node<>(key, value);
            } else {
                // no need to creat a new node
                // just replace the content of the head node
                node = head;
                evict(node);
                node.key = key;
                node.value = value;
                // evict(node); put it here is wrong
                // because this way, the original key of the tail.prev is not removed from map
            }
        } else {
            node.value = value;
            evict(node);
        }
        add(node);
    }

    private void evict(Node<K, V> node) {
        records.remove(node.key);
        if (head == node) {
            head = head.next;
        }
        if (tail == node) {
            tail = tail.prev;
        }
        if (node.next != null) {
            node.next.prev = node.prev;
        }
        if (node.prev != null) {
            node.prev.next = node.next;
        }
        node.next = node.prev = null;
    }

    private void add(Node<K, V> node) {
        records.put(node.key, node);
        if (head == null) { // or records.size() == 1
            head = tail = node;
        } else {
            tail.next = node;
            node.prev = tail;
            tail = node;
        }
    }
}
