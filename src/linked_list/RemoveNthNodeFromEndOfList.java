package linked_list;

import util.ListNode;

/**
 * https://leetcode.com/problems/remove-nth-node-from-end-of-list/
 */

public class RemoveNthNodeFromEndOfList {

    public ListNode removeNthFromEndBetter(ListNode head, int n) {
        // assume n <= the size of list
        if (head == null) {
            return null;
        }
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        // trick: we want to let slow stops at the previous position of the to-be-deleted
        // but if we let both pointers start from head, NPE will occur if n = size of list
        // setting both pointers at dummy can avoid the potential issue
        ListNode first = dummy;
        ListNode second = dummy;
        // Advances first pointer so that the gap between first and second is n nodes apart
        for (int i = 1; i <= n + 1; i++) {
            first = first.next;
        }
        // Move first to the end, maintaining the gap
        while (first != null) {
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next;
        return dummy.next;
    }

//    public ListNode removeNthFromEnd(ListNode head, int n) {
//        // assume n <= the size of list
//        // if (head == null) {
//        //     return null;
//        // }
//        // we need to have the prev position of the to be deleted node
//        ListNode dummy = new ListNode(-1);
//        dummy.next = head;
//        ListNode fast = head;
//        ListNode slow = head;
//
//        while (n-- != 0) {
//            fast = fast.next;
//        }
//        // edge case -> n == size of list
//        if (fast == null) {
//            dummy.next = dummy.next.next;
//        } else {
//            // let fast move one step further
//            fast = fast.next;
//            while (fast != null) {
//                slow = slow.next;
//                fast = fast.next;
//            }
//            slow.next = slow.next.next;
//        }
//        return dummy.next;
//    }
}
