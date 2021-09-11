package linked_list;

import util.ListNode;

/**
 * Given a list, rotate the list to the right by k places, where k is non-negative.
 *
 * Input: 1->2->3->4->5->NULL, k = 2
 *
 * Output: 4->5->1->2->3->NULL
 *
 * Input: 1->2->3->4->5->NULL, k = 12
 *
 * Output: 4->5->1->2->3->NULL
 *
 */

public class RotateListByKPlaces {
    public ListNode rotateKPlace(ListNode head, int n) {
        // step 1: count the num of nodes
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        int count = 0;
        while (fast != null) {
            count++;
            fast = fast.next;
        }
        n = n % count;
        // handle this corner case
        if (n == 0) {
            return head;
        }
        // step 2: set slow & fast pointers and move them to the corresponding positions
        ListNode slow = head;
        fast = head;
        while (n-- != 0) {
            fast = fast.next;
        }
        while (fast.next != null) {
            slow = slow.next;
            fast = fast.next;
        }
        ListNode newHead = slow.next;
        slow.next = null;
        fast.next = head;
        return newHead;
    }

}
