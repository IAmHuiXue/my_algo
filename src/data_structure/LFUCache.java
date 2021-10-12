package data_structure;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * https://leetcode.com/problems/lfu-cache/
 */

// in order to ensure O(1) operating get(), put(), we cannot use structure such as TreeMap or PQ

public class LFUCache {
    private Map<Integer, Node> nodeMap;
    private Map<Integer, DLList> countMap;
    private int limit;
    private int leastFreq;

    static class Node {
        int key;
        int value;
        int count;
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
        static final Node HEAD = new Node(0, 0);// add to head
        static final Node TAIL = new Node(0, 0); // remove from tail
        int size;

        public DLList() {
            HEAD.next = TAIL;
            TAIL.prev = HEAD;
        }

        void addHead(Node node) {
            node.next = HEAD.next;
            HEAD.next.prev = node;
            HEAD.next = node;
            node.prev = HEAD;
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
                Node node = TAIL.prev;
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
                DLList oldList = countMap.get(leastFreq);
                nodeMap.remove(oldList.removeLast().key);
            }
            leastFreq = 1;
            DLList newList = countMap.getOrDefault(node.count, new DLList());
            newList.addHead(node);
            countMap.put(node.count, newList);
        }
    }

    private void update(Node node) {
        DLList oldList = countMap.get(node.count);
        oldList.remove(node);
        if (node.count == leastFreq && oldList.size == 0) {
            leastFreq++;
        }
        node.count++;
        DLList newList = countMap.getOrDefault(node.count, new DLList());
        newList.addHead(node);
        countMap.put(node.count, newList);
    }

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}

class StockPrice {
    Map<Integer, Node> map;
    PriorityQueue<Node> minHeap;
    PriorityQueue<Node> maxHeap;
    int latestTime;

    static class Node {
        int time;
        int price;

        Node (int t, int p) {
            time = t;
            price = p;
        }
    }


    public StockPrice() {
        map = new HashMap<>();
        minHeap = new PriorityQueue<>((a, b) -> Integer.compare(a.price, b.price));
        maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b.price, a.price));

    }

    public void update(int timestamp, int price) {
        Node node = map.get(timestamp);
        if (node != null) {
            node.price = price;
        } else {
            node = new Node(timestamp, price);
            map.put(timestamp, node);
            minHeap.offer(node);
            maxHeap.offer(node);
            latestTime = Math.max(latestTime, timestamp);
        }

    }

    public int current() {
        return map.get(latestTime).price;
    }

    public int maximum() {
        return maxHeap.peek().price;
    }

    public int minimum() {
        return minHeap.peek().price;

    }

    public static void main(String[] args) {
        StockPrice t = new StockPrice();
        t.update(1, 10);
        t.update(2, 5);
        t.update(1, 3);
        System.out.println(t.maximum());


    }
}
