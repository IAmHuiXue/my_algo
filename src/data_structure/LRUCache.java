package data_structure;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lru-cache/
 */

public class LRUCache {
    private Map<Integer, Node> nodeMap;
    // 以下对于 head 和 tail 的用法就是
    // 两个 pointer 相当于两个 dummy
    private final Node head = new Node(-1, -1); // add to head
    private final Node tail = new Node(-1, -1); // evict from tail
    private final int capacity;

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
        nodeMap = new HashMap<>();
        this.capacity = capacity;
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        Node node = nodeMap.get(key);
        if (node == null) {
            return -1;
        }
        update(node);
        return node.value;
    }

    public void put(int key, int value) {
        Node node = nodeMap.get(key);
        if (node != null) {
            node.value = value;
            update(node);
            return;
        }
        if (nodeMap.size() == capacity) {
            evict(tail.prev);
        }
        node = new Node(key, value);
        addHead(node);
    }

    private void update(Node node) {
        evict(node);
        addHead(node);
    }

    private void evict(Node node) {
        nodeMap.remove(node.key); // O(1) remember to remove it from nodeMap
        node.next.prev = node.prev;
        node.prev.next = node.next;
        node.next = node.prev = null;
    }

    private void addHead(Node node) {
        nodeMap.put(node.key, node); // remember to put it in nodeMap
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