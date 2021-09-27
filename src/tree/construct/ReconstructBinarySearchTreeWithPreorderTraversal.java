package tree.construct;

import util.TreeNode;

/** https://app.laicode.io/app/problem/210?plan=16 */

public class ReconstructBinarySearchTreeWithPreorderTraversal {
    public TreeNode reconstruct(int[] pre) {
        return reconstruct(pre, 0, pre.length - 1);
    }

    private TreeNode reconstruct(int[] pre, int start, int end) {
        if (start > end) {
            return null;
        }
        TreeNode root = new TreeNode(pre[start]);
        // find the last node that belongs to left subtree
        int firstNodeInRightSubTree = start + 1;
        while (firstNodeInRightSubTree <= end && pre[firstNodeInRightSubTree] < root.val) {
            firstNodeInRightSubTree++;
        }
        root.left = reconstruct(pre, start + 1, firstNodeInRightSubTree - 1);
        root.right = reconstruct(pre, firstNodeInRightSubTree, end);
        return root;
    }
}
