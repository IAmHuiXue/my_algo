package tree.bst;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Deque;

/** https://leetcode.com/problems/validate-binary-search-tree/ */

// this can effectively cover the edge cases where the values of all nodes are Integer.MIN_VALUE or Integer.MAX_VALUE

public class ValidateBinarySearchTree {
     static class Recursive {
        public static boolean isValidBST(TreeNode root) {
            return isValidBST(root, null, null);
        }

        private static boolean isValidBST(TreeNode root, Integer min, Integer max) {
            if (root == null) {
                return true;
            }
            if (min != null && root.val <= min || max != null && root.val >= max) {
                return false;
            }
            return isValidBST(root.left, min, root.val) && isValidBST(root.right, root.val, max);
        }
    }

    static class Iterative {
        public static boolean isValidBST(TreeNode root) {
            // maintain a prev pointer to record the node previous to the cur node in the inorder sequence
            TreeNode prev = null;
            Deque<TreeNode> stack = new ArrayDeque<>();
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.offerFirst(root);
                    root = root.left;
                }
                TreeNode cur = stack.pollFirst();
                if (prev != null && cur.val <= prev.val) {
                    return false;
                }
                prev = cur;
                root = cur.right;
            }
            return true;
        }
    }



}

