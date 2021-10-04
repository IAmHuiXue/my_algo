package linked_list;

import util.ListNode;

public class ReverseNodesInKGroup {
    /**
     * Given a linked list, reverse the nodes of a linked list k at a time and return its modified list. If the number
     * of nodes is not a multiple of k then left-out nodes in the end should remain as it is.
     * You may not alter the values in the nodes, only nodes itself may be changed.
     *
     * Example
     *
     * Given this linked list: 1->2->3->4->5
     *
     * For k = 2, you should return: 2->1->4->3->5
     *
     * For k = 3, you should return: 3->2->1->4->5
     */

    public ListNode reverseKGroup(ListNode head, int k) {
        if (k <= 0) {
            return head;
        }
        return reverseKGroupHelper(head, k);
    }
    private ListNode reverseKGroupHelper(ListNode head, int k) {
        // find k nodes
        ListNode cur = head;
        int count = 1;
        while (cur != null && count < k) {
            cur = cur.next;
            count++;
        }
        // if cur = null -> k > the num of node partition
        if (cur == null) {
            // do not change this partition and return
            return head;
        }
        // partition and reverse
        // also perform the same to the next group
        ListNode tail = reverseKGroupHelper(cur.next, k);
        cur.next = null;
        reverse(head);
        head.next = tail;
        return cur;
    }
    private ListNode reverse(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }
}
