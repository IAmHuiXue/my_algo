package tree;

import util.TreeNode;

/** Construct Binary Search Tree from Preorder Traversal */

public class ConstructBinarySearchTreeFromPreorderTraversal {
    int index = 0;
    public TreeNode bstFromPreorder(int[] preorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        return helper(preorder, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode helper(int[] preorder, int min, int max) {
        if (index == preorder.length) {
            return null;
        }
        int cur = preorder[index];
        if (cur < min || cur > max) {
            return null;
        }
        index++;
        TreeNode root = new TreeNode(cur);
        root.left = helper(preorder, min, cur);
        root.right = helper(preorder, cur, max);
        return root;
    }
}
