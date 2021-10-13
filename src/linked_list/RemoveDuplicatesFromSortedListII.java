package linked_list;

import util.ListNode;

/** https://leetcode.com/problems/remove-duplicates-from-sorted-list-ii/ */

public class RemoveDuplicatesFromSortedListII {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(-1);
        dummy.next = head;
        ListNode cur = head;
        ListNode prev = dummy;
        while (cur != null) {
            if (cur.next != null && cur.value == cur.next.value) {
                while (cur.next != null && cur.next.value == cur.value) {
                    cur = cur.next;
                }
                prev.next = cur.next;
            } else {
                prev = prev.next;
            }
            cur = cur.next;

        }
        return dummy.next;
    }
}
