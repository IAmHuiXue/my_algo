package linked_list;

import util.ListNode;

/** https://leetcode.com/problems/rotate-list/ */

public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0) {
            return head;
        }
        ListNode fast = head;
        int numNodes = 1;
        while (fast.next != null) {
            fast = fast.next;
            numNodes++;
        }
        // fast is at the last node
        k %= numNodes;
        if (k == 0) {
            return head;
        }
        ListNode slow = head;
        int diff = numNodes - k;
        while (--diff > 0) {
            slow = slow.next;
        }
        ListNode nxt = slow.next;
        slow.next = null;
        fast.next = head;
        return nxt;
    }
}
