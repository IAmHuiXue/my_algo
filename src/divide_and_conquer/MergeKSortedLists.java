package divide_and_conquer;

import util.ListNode;

public class MergeKSortedLists {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return mergeKLists(lists, 0, lists.length - 1);
    }
    ListNode mergeKLists(ListNode[] lists, int left, int right) {
        if (left == right) {
            return lists[left];
        }
        int mid = left + (right - left) / 2;
        ListNode leftResult = mergeKLists(lists, left, mid);
        ListNode rightResult = mergeKLists(lists, mid + 1, right);
        return merge(leftResult, rightResult);
    }

    ListNode merge(ListNode leftResult, ListNode rightResult) {
        ListNode dummyHead = new ListNode(-1);
        ListNode cur = dummyHead;
        while (leftResult != null && rightResult != null) {
            if (leftResult.value < rightResult.value) {
                cur.next = leftResult;
                leftResult = leftResult.next;
            } else {
                cur.next = rightResult;
                rightResult = rightResult.next;
            }
            cur = cur.next;
        }
        while (rightResult != null) {
            cur.next = rightResult;
            rightResult = rightResult.next;
            cur = cur.next;
        }
        while (leftResult != null) {
            cur.next = leftResult;
            leftResult = leftResult.next;
            cur = cur.next;
        }
        return dummyHead.next;
    }
}
