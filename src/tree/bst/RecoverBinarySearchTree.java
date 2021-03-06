package tree.bst;

import util.TreeNode;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/
 */

public class RecoverBinarySearchTree {
    TreeNode first;
    TreeNode second;
    TreeNode prev;

    public void recoverTree(TreeNode root) {
        inOrderTraverse(root);
        int tmp = first.val;
        first.val = second.val;
        second.val = tmp;

    }

    private void inOrderTraverse(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrderTraverse(root.left);
        if (prev != null && prev.val >= root.val) {

            // code below is wrong
            // edge case is when the 2 digits are just close to each other

            // if (one == null) {
            //     one = prev;
            // } else {
            //     two = root;
            //     return;
            // }

            // the correct approach is whenever we find an abnormal case
            // we record the both prev and cur by first and second
            // set a rule to always let first record prev and let second record cur
            // so next when prev is not null, we know we have found both

            if (first == null) {
                first = prev;
                second = root;
            } else {
                second = root;
                return;
            }

            // or
        }
        prev = root;
        inOrderTraverse(root.right);
    }
}
