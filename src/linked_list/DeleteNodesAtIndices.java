package linked_list;

import util.ListNode;

/**
 * Given a linked list and a sorted array of integers as the indices in the list.
 * Delete all the nodes at the indices in the original list.
 *
 * Examples
 *
 * 1 -> 2 -> 3 -> 4 -> NULL, indices = {0, 3, 5}, after deletion the list is 2 -> 3 -> NULL.
 *
 * Assumptions
 *
 * the given indices array is not null, and it is guaranteed to contain non-negative integers sorted in ascending order.
 */

public class DeleteNodesAtIndices {
    int numOfDeleted = 0;
    boolean shouldStopEarly = false;

    public ListNode deleteNodes(ListNode head, int[] indices) {
        for (int index : indices) {
            head = deleteNodes(head, index - numOfDeleted);
            if (shouldStopEarly) {
                return head;
            }
        }
        // until we remove all the nodes required by indices[]
        return head;
    }

    private ListNode deleteNodes(ListNode head, int index) {
        if (head == null) {
            shouldStopEarly = true;
            return null;
        }
        ListNode node = head;
        if (index == 0) {
            head = head.next;
            node.next = null;
            numOfDeleted++;
            return head;
        }
        int count = 0;
        // find the previous node of the node to be deleted
        while (node != null && count++ < index - 1) {
            node = node.next;
        }
        // if position is more than the number of nodes
        if (node == null || node.next == null) {
            shouldStopEarly = true;
            return head;
        }
        ListNode deleted = node.next;
        node.next = deleted.next;
        deleted.next = null;
        numOfDeleted++;
        return head;
    }
}
