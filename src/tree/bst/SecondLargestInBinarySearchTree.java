package tree.bst;

import util.TreeNode;

/** https://app.laicode.io/app/problem/347?plan=16 */

public class SecondLargestInBinarySearchTree {
    public int secondLargest(TreeNode root) {
        int largest[] = new int[] {Integer.MIN_VALUE};
        int secondLargest[] = new int[] {Integer.MIN_VALUE};
        inorder(root, largest, secondLargest);
        return secondLargest[0];
    }

    private void inorder(TreeNode root, int[] largest, int[] secondLargest) {
        if (root == null) {
            return;
        }
        inorder(root.left, largest, secondLargest);
        if (largest[0] != Integer.MIN_VALUE) {
            secondLargest[0] = largest[0];
        }
        largest[0] = root.val;
        inorder(root.right, largest, secondLargest);
    }
}
