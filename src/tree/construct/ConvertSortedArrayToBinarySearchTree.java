package tree.construct;

import util.ConstructBalancedBinaryTree;
import util.TreeNode;

/** https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/ */

public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return ConstructBalancedBinaryTree.constructBalancedBinaryTree(nums);
    }
}
