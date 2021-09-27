package tree.construct;

/** https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/ */

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    ListNode head = null, pre = null;
    public ListNode treeToDoublyList(ListNode root) {
        if (root == null) {
            return null;
        }
        inorder(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void inorder(ListNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);

        if (head == null) {
            head = root;
        }
        if (pre != null) {
            pre.right = root;
        }
        root.left = pre;
        pre = root;

        inorder(root.right);
    }
}
class ListNode {
    public int val;
    public ListNode left;
    public ListNode right;

    public ListNode(int _val) {
        val = _val;
    }
}
