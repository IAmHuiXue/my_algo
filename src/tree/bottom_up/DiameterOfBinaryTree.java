package tree.bottom_up;

import util.TreeNode;

/** https://leetcode.com/problems/diameter-of-binary-tree/ */

public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] dia = new int[1];
        helper(root, dia);
        return dia[0];
    }

    private int helper(TreeNode root, int[] dia) {
        if (root == null) {
            return 0;
        }
        // helper() -> return the longest dia from root to the leaf node in one side
        int left = helper(root.left, dia);
        int right = helper(root.right, dia);

        dia[0] = Math.max(left + right, dia[0]);
        return Math.max(left, right) + 1;
    }
}
