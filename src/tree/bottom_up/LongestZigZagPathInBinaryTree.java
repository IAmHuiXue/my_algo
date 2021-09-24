package tree.bottom_up;

import util.TreeNode;

/** https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/ */

public class LongestZigZagPathInBinaryTree {
    public int longestZigZag(TreeNode root) {
        int[] longest = new int[1];
        dfs(root, longest);
        return longest[0];
    }
    // return {a, b} -> a represents the num of nodes starting from left, b is the other way.
    private int[] dfs(TreeNode root, int[] longest) {
        int[] res = new int[2];
        if (root == null) {
            return res;
        }
        if (root.left == null && root.right == null) {
            return res;
        }

        // if I go left, need to link left[1]
        if (root.left != null) {
            // we can update only when left is not null
            int[] left = dfs(root.left, longest);
            res[0] = 1 + left[1];
        }
        // if I go right, need to link right[0]
        if (root.right != null) {
            // we can update only when right is not null
            int[] right = dfs(root.right, longest);
            res[1] = 1 + right[0];
        }

        longest[0] = Math.max(longest[0], Math.max(res[0], res[1]));
        return res;
    }
}
