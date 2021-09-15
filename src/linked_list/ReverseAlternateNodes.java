package linked_list;

import util.ListNode;

/**
 * Given a linked list, reverse alternate nodes and append at the end.
 *
 * Examples:
 *
 * Input List:    1 -> 2 -> 3 -> 4 -> 5 -> 6
 *
 * Output List: 1 -> 3 -> 5 -> 6 -> 4 -> 2
 *
 * Input List:    1 -> 2 -> 3 -> 4 -> 5
 *
 * Output List: 1 -> 3 -> 5 -> 4 -> 2
 */

// TODO: another approach:
//    1. The idea is to maintain two linked lists, one list of all odd positioned nodes and other
//  list of all even positioned nodes .
//    2. Traverse the given linked list which is considered as an odd list or oddly positioned nodes.
//    3. If the node is even node, remove it from the odd list and add it to the front of even node list.
//  Nodes are added at front to keep the reverse order.
//    4. Append the even node list at the end of odd node list.

public class ReverseAlternateNodes {
    ListNode oddTail;
    ListNode evenHead;
    public ListNode reverseAlternate(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return head;
        }
        // step 1: separate odd and even
        oddEvenList(head);
        // step 2: reverse the even
        oddTail.next = reverse(evenHead);

        return head;
    }

    private void oddEvenList(ListNode head) {
        oddTail = head;
        ListNode evenTail = head.next;
        evenHead = head.next;
        // draw examples to figure out the terminating condition
        // evenTail is always oddTail.next, so we only need to pay
        // attention to the status of eveTail
        while (evenTail != null && evenTail.next != null) {
            oddTail.next = evenTail.next;
            oddTail = oddTail.next;
            evenTail.next = oddTail.next;
            evenTail = evenTail.next;
        }
        oddTail.next = evenHead;
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
