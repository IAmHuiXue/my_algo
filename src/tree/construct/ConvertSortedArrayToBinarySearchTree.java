package tree.construct;

import util.ConstructBST;
import util.TreeNode;

/** https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/ */

public class ConvertSortedArrayToBinarySearchTree {
    public TreeNode sortedArrayToBST(int[] nums) {
        return ConstructBST.constructBST(nums);
    }
}
