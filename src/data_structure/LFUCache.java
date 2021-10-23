package data_structure;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lfu-cache/
 */

// in order to ensure O(1) operating get(), put(), we cannot use structure such as TreeMap or PQ

public class LFUCache {
    private Map<Integer, Node> nodeMap;

    // 在 lfu 里，每一个 node 存在一个 list 里面，而有不同多个 list，按照 frequency 来区分：
    // 同时每个 list 里面 the least recently used node 在 tail
    private Map<Integer, DLList> countMap;
    private int limit;
    private int leastFreq;

    static class Node {
        int key;
        int value;
        int count; // ！
        Node next;
        Node prev;

        Node(int k, int v) {
            key = k;
            value = v;
            count = 1;
        }
    }

    static class DLList {
        // 以下对于 head 和 tail 的用法就是
        // 两个 pointer 相当于两个 dummy, size 来控制实际 node 个数
        final Node head = new Node(0, 0); // add to head
        final Node tail = new Node(0, 0); // remove from tail
        int size;

        public DLList() {
            head.next = tail;
            tail.prev = head;
        }

        void addHead(Node node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
            size++;
        }

        void remove(Node node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = node.prev = null;
            size--;
        }

        Node removeLast() {
            if (size != 0) {
                Node node = tail.prev;
                remove(node);
                return node;
            }
            return null;
        }
    }

    public LFUCache(int capacity) {
        nodeMap = new HashMap<>();
        countMap = new HashMap<>();
        limit = capacity;
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
        } else {
            node = new Node(key, value);
            nodeMap.put(key, node);
            if (nodeMap.size() > limit) {
                DLList leastFreqList = countMap.get(leastFreq);
                nodeMap.remove(leastFreqList.removeLast().key);
            }
            leastFreq = 1; // because the newly added node's freq is 1
            countMap.computeIfAbsent(leastFreq, k -> new DLList()).addHead(node);
//            DLList leastFreqList = countMap.getOrDefault(leastFreq, new DLList());
//            leastFreqList.addHead(node);
//            countMap.putIfAbsent(leastFreq, leastFreqList);
        }
    }

    private void update(Node node) {
        // node needs to be updated with the new frequency -> switched to another DLList
        DLList oldList = countMap.get(node.count);
        oldList.remove(node);
        if (node.count == leastFreq && oldList.size == 0) {
            leastFreq++; // update leastFreq is needed
        }
        node.count++;
        countMap.computeIfAbsent(node.count, k -> new DLList()).addHead(node);
//        DLList newList = countMap.getOrDefault(node.count, new DLList());
//        newList.addHead(node);
//        countMap.put(node.count, newList);
    }

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}

