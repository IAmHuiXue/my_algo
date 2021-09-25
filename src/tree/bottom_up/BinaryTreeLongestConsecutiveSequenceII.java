package tree.bottom_up;

import util.TreeNode;

/** https://leetcode.com/problems/binary-tree-longest-consecutive-sequence-ii/ */

public class BinaryTreeLongestConsecutiveSequenceII {
    public int longestConsecutive(TreeNode root) {
        int[] maxval = new int[1];
        longestPath(root, maxval);
        return maxval[0];
    }

    public int[] longestPath(TreeNode root, int[] maxval) {
        if (root == null) {
            return new int[] {0,0};
        }

        int inr = 1, dcr = 1;
        if (root.left != null) {
            int[] left = longestPath(root.left, maxval);
            if (root.val == root.left.val + 1) {
                dcr = left[1] + 1;
            } else if (root.val == root.left.val - 1) {
                inr = left[0] + 1;
            }
        }

        if (root.right != null) {
            int[] right = longestPath(root.right, maxval);
            if (root.val == root.right.val + 1) {
                dcr = Math.max(dcr, right[1] + 1);
            } else if (root.val == root.right.val - 1) {
                inr = Math.max(inr, right[0] + 1);
            }
        }

        maxval[0] = Math.max(maxval[0], dcr + inr - 1);
        return new int[] {inr, dcr};
    }
}
