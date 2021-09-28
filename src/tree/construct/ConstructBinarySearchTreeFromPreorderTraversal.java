package tree.construct;

import util.TreeNode;

/**
 * https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/
 */

public class ConstructBinarySearchTreeFromPreorderTraversal {
    static class Solution1 {
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

    static class Solution2 {
        public TreeNode bstFromPreorder(int[] preorder) {
            return reconstruct(preorder, 0, preorder.length - 1);
        }

        private TreeNode reconstruct(int[] preorder, int start, int end) {
            if (start > end) {
                return null;
            }
            TreeNode root = new TreeNode(preorder[start]);
            // find the last node that belongs to left subtree
            int firstNodeInRightSubTree = start + 1;
            while (firstNodeInRightSubTree <= end && preorder[firstNodeInRightSubTree] < root.val) {
                firstNodeInRightSubTree++;
            }
            root.left = reconstruct(preorder, start + 1, firstNodeInRightSubTree - 1);
            root.right = reconstruct(preorder, firstNodeInRightSubTree, end);
            return root;
        }
    }

}
