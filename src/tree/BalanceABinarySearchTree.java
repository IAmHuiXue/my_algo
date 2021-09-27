package tree;

import tree.construct.ConvertSortedArrayToBinarySearchTree;
import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/** https://leetcode.com/problems/balance-a-binary-search-tree/ */

public class BalanceABinarySearchTree {
    public TreeNode balanceBST(TreeNode root) {
        int[] array = deconstruct(root);
        return ConvertSortedArrayToBinarySearchTree.sortedArrayToBST(array);
    }

    private int[] deconstruct(TreeNode root) {
        List<Integer> array = new ArrayList<>();
        inorder(root, array);
        int[] result = new int[array.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = array.get(i);
        }
        return result;
    }

    private void inorder(TreeNode root, List<Integer> array) {
        if (root == null) {
            return;
        }
        inorder(root.left, array);
        array.add(root.val);
        inorder(root.right, array);
    }
}
