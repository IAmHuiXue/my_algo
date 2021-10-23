package data_structure;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/
 */

public class LRUCache {
    private Map<Integer, Node> records;
    // 以下对于 head 和 tail 的用法就是
    // 两个 pointer 相当于两个 dummy
    private final Node head = new Node(0, 0); // add to head
    private final Node tail = new Node(0, 0); // evict from tail
    private int capacity;

    static class Node {
        int key;
        int value;
        Node next;
        Node prev;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public LRUCache(int capacity) {
        records = new HashMap<>();
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = records.get(key);
        if (node == null) {
            return -1;
        }
        evict(node);
        addHead(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = records.get(key);
        if (node == null) {
            if (records.size() < capacity) {
                node = new Node(key, value);
            } else {
                node = tail.prev;
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
        addHead(node);
    }

    private void evict(Node node) {
        records.remove(node.key);
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.next = node.prev = null;
    }

    private void addHead(Node node) {
        records.put(node.key, node);
        head.next.prev = node;
        node.next = head.next;
        head.next = node;
        node.prev = head;
    }

}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */