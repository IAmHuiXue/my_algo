package tree;

import util.TreeNode;

/** https://leetcode.com/problems/diameter-of-binary-tree/ */

public class DiameterOfBinaryTree {
    public int diameterOfBinaryTree(TreeNode root) {
        int[] dia = new int[1];
        dfs(root, dia);
        return dia[0];
    }

    private int dfs(TreeNode root, int[] dia) {
        if (root == null) {
            return 0;
        }
        // dfs() -> return the longest dia from root to the leaf node in one side
        int left = dfs(root.left, dia);
        int right = dfs(root.right, dia);

        dia[0] = Math.max(left + right, dia[0]);
        return Math.max(left, right) + 1;
    }
}
