package tree.bst;

import util.TreeNode;

/**
 * https://leetcode.com/problems/inorder-successor-in-bst/
 */

public class InorderSuccessorInBST {
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        if (p == null || root == null) {
            return null;
        }
        if (root.val <= p.val) {
            return inorderSuccessor(root.right, p);
        }
        // root.val > p.val
        TreeNode left = inorderSuccessor(root.left, p);
        if (left == null || left.val <= p.val) {
            return root;
        }
        return left;
    }

    public TreeNode inorderSuccessorIterative(TreeNode root, TreeNode p) {
        if (p == null || root == null) {
            return null;
        }
        TreeNode successor = null;
        while (root != null) {
            if (p.val >= root.val) {
                root = root.right;
            } else {
                successor = root;
                root = root.left;
            }
        }
        return successor;
    }
}
