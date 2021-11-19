package tree.pass_down;

import util.TreeNode;

/** https://app.laicode.io/app/problem/302?plan=16 */

public class TransformBinarySearchTreeToGreaterSumTree {

    // if we inorder BST, for each element, the sum of elements that are greater than the element itself
    // is the sumTotal - preSum[element]. So we solve the problem by in-order traverse the tree from RIGHT to LEFT.

    // Therefore, when we visit the current node, sum[0] is the value the node needs to be changed to, and do not forget
    // to add the original value of the node into sum[0] for the next function call.

    public TreeNode greaterSum(TreeNode root) {
        int[] sum = new int[1];
        inorderReverse(root, sum);
        return root;
    }

    private void inorderReverse(TreeNode root, int[] sum) {
        if (root == null) {
            return;
        }
        inorderReverse(root.right, sum);
        int rootValue = root.val;
        root.val = sum[0];
        sum[0] += rootValue;
        inorderReverse(root.left, sum);
    }
}
