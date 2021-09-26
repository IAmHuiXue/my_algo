package tree;

import util.TreeNode;

/** https://leetcode.com/problems/validate-binary-search-tree/ */

// this can effectively cover the edge cases where the values of all nodes are Integer.MIN_VALUE or Integer.MAX_VALUE

public class ValidateBinarySearchTree {
    public boolean isValidBST(TreeNode root) {
        return isValidBST(root, null, null);
    }
    private boolean isValidBST(TreeNode root, Integer min, Integer max) {
        if (root == null) {
            return true;
        }
        if (min != null && root.val <= min || max != null && root.val >= max) {
            return false;
        }
        return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
    }
}
