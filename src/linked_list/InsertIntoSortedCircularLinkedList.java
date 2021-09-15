package linked_list;

import util.ListNode;

/** https://leetcode.com/problems/insert-into-a-sorted-circular-linked-list/ */

public class InsertIntoSortedCircularLinkedList {
    public ListNode insert(ListNode head, int insertVal) {
        ListNode node = new ListNode(insertVal);
        if (head == null) {
            node.next = node; // !
            return node;
        }
        // as long as head is not null, it is a cyclic list and not null
        ListNode prev = head;
        ListNode curr = head.next;
        boolean toInsert = false;
        do {
            if (prev.value <= insertVal && insertVal <= curr.value) {
                toInsert = true;
            } else if (prev.value > curr.value) { // need to be inserted between max and min
                if (insertVal >= prev.value || insertVal <= curr.value)
                    toInsert = true;
            }

            if (toInsert) {
                prev.next = node;
                node.next = curr;
                return head;
            }

            prev = curr;
            curr = curr.next;
        } while (prev != head);

        // Case 3) -> where the list only contains uniform values
        prev.next = node;
        node.next = curr;
        return head;
    }
}
