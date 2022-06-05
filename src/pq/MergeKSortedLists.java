package pq;

import util.ListNode;

import java.util.PriorityQueue;

/** <a href="https://leetcode.com/problems/merge-k-sorted-lists/">...</a> */

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((l1, l2) -> Integer.compare(l1.value, l2.value));
        for (ListNode head : lists) {
            if (head != null) {
                minHeap.offer(head);
            }
        }
        ListNode dummy = new ListNode(-1);
        ListNode cur = dummy;
        while (!minHeap.isEmpty()) {
            ListNode node = minHeap.poll();
            cur.next = node;
            cur = cur.next;
            if (node.next != null) {
                minHeap.offer(node.next);
            }
        }
        return dummy.next;
    }
}
