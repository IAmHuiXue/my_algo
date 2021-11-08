package linked_list;

import util.ListNode;

/**
 * https://app.laicode.io/app/problem/319?plan=14
 */

public class DeleteNodeAtIndex {

    public ListNode deleteNodeWithDummyNode(ListNode head, int index) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        int count = 0;
        ListNode pre = dummy;
        while (head != null && count++ < index) {
            pre = head;
            head = head.next;
        }
        if (head == null) {
            return dummy.next;
        }
        pre.next = head.next;
        head.next = null;
        return dummy.next;
    }

    public ListNode deleteNode(ListNode head, int index) {
        if (head == null) {
            return null;
        }
        ListNode node = head;
        if (index == 0) {
            head = head.next;
            node.next = null;
            return head;
        }
        int count = 0;
        // find the previous node of the node to be deleted
        while (node != null && count++ < index - 1) {
            node = node.next;
        }
        // if position is more than the number of nodes
        if (node == null || node.next == null) {
            return head;
        }
        ListNode deleted = node.next;
        node.next = deleted.next;
        deleted.next = null;
        return head;
    }
}
