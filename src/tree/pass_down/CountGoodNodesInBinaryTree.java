package tree.pass_down;

import util.TreeNode;

/** https://leetcode.com/problems/count-good-nodes-in-binary-tree/ */

public class CountGoodNodesInBinaryTree {
    public int goodNodes(TreeNode root) {
        int[] result = new int[1];
        search(root, Integer.MIN_VALUE, result);
        return result[0];
    }

    private void search(TreeNode root, int max, int[] result) {
        if (root == null) {
            return;
        }
        if (root.val >= max) {
            result[0]++;
            max = root.val;
        }
        search(root.left, max, result);
        search(root.right, max, result);
    }
}
