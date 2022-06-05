package data_structure;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/lfu-cache/
 */

// in order to ensure O(1) operating get(), put(), we cannot use structure such as TreeMap or PQ

public class LFUCache {
    public static void main(String[] args) {
        LFUCache l = new LFUCache(0);
        l.put(0, 0);
        System.out.println(l.get(0));

    }

    // given the key, find the node via nodeMap;
    // from the node, we can find the original freq
    // from the original freq, we can find which DLList it resides via freqMap
    // we can then remove the node from the original DLList and add it to the new DLList incrementing the freq

    // with the leastFreq pointer, we can locate the corresponding DLList via freqMap and remove the LFU + LRU node from it


    private Map<Integer, Node> nodeMap;

    // 在 lfu 里，每一个 node 存在一个 list 里面，而有不同多个 list，按照 frequency 来区分：
    // 同时每个 list 里面 the least recently used node 在 tail
    // <key=count, value=list>
    private Map<Integer, DLList> freqMap;
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
        final Node head = new Node(-1, -1); // add to head
        final Node tail = new Node(-1, -1); // remove from tail
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
        freqMap = new HashMap<>();
        limit = capacity;
        leastFreq = Integer.MAX_VALUE;
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

        node = new Node(key, value);
        if (limit == nodeMap.size()) {
            DLList leastFreqList = freqMap.get(leastFreq);
            nodeMap.remove(leastFreqList.removeLast().key);
        }
        nodeMap.put(key, node);
        leastFreq = 1; // because the newly added node's freq is 1
        freqMap.computeIfAbsent(leastFreq, k -> new DLList()).addHead(node);
    }

    private void update(Node node) {
        // node needs to be updated with the new frequency -> switched to another DLList
        DLList oldList = freqMap.get(node.count);
        oldList.remove(node);
        if (node.count == leastFreq && oldList.size == 0) {
            leastFreq++;
            freqMap.remove(node.count);
        }
        node.count++;
        freqMap.computeIfAbsent(node.count, k -> new DLList()).addHead(node);
    }

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}

