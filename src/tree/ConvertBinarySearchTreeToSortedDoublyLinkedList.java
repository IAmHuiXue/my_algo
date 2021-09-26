package tree;

/** https://leetcode.com/problems/convert-binary-search-tree-to-sorted-doubly-linked-list/ */

public class ConvertBinarySearchTreeToSortedDoublyLinkedList {
    Node head = null, pre = null;
    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        inorder(root);
        head.left = pre;
        pre.right = head;
        return head;
    }

    private void inorder(Node root) {
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
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node(int _val) {
        val = _val;
    }
}
