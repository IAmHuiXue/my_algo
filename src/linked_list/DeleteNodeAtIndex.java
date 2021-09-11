package linked_list;

import util.ListNode;

public class DeleteNodeAtIndex {
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
