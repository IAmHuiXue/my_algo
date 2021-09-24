package tree.bottom_up;

import util.TreeNode;

/** https://leetcode.com/problems/maximum-average-subtree/ */

/* this question requires multi answers from subtrees, so one way is to create a class to wrap it, or use array such
*  as int[] {a, b} if a and b are the same data type */

public class MaximumAverageSubtree {
    public double maximumAverageSubtree(TreeNode root) {
        // root is not null
        double[] max = new double[] {Double.MIN_VALUE};
        helper(root, max);
        return max[0];
    }

    private Answer helper(TreeNode root, double[] max) {
        if (root == null) {
            return new Answer();
        }

        Answer left = helper(root.left, max);
        Answer right = helper(root.right, max);

        max[0] = Math.max(max[0], (left.sum + right.sum + root.val) / (left.numNodes + right.numNodes + 1));

        return new Answer((left.sum + right.sum + root.val), (left.numNodes + right.numNodes + 1));
    }
    class Answer {
        double sum;
        int numNodes;

        Answer(double a, int b) {
            sum = a;
            numNodes = b;
        }
        Answer() {}
    }
}
