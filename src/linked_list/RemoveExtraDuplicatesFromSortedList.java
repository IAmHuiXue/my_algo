package linked_list;

import util.ListNode;

/**
 * Given a sorted linked list, delete all nodes that have duplicate numbers,
 * leaving only distinct numbers from the original list.
 *
 * Input:  1->2->3->3->4->4->5
 *
 * Output: 1->2->5
 *
 * Input:  1->1->1
 *
 * Output: NULL
 */

public class RemoveExtraDuplicatesFromSortedList {
    public ListNode removeDup(ListNode head) {
        // sentinel
        ListNode sentinel = new ListNode(0);
        sentinel.next = head;
        // predecessor = the last node
        // before the sublist of duplicates
        ListNode pred = sentinel;

        while (head != null) {
            // if it's a beginning of duplicates sublist
            // skip all duplicates
            if (head.next != null && head.value == head.next.value) {
                // move till the end of duplicates sublist
                while (head.next != null && head.value == head.next.value) {
                    head = head.next;
                }
                // skip all duplicates
                pred.next = head.next;
                // otherwise, move predecessor
            } else {
                pred = pred.next;
            }

            // move forward
            head = head.next;
        }
        return sentinel.next;
    }
}
