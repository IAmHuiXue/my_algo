package recursion;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/** <a href="https://leetcode.com/problems/unique-binary-search-trees-ii/">...</a> */

public class UniqueBinarySearchTreesII {
    public List<TreeNode> generateTrees(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        return helper(1, n);
    }

    private List<TreeNode> helper(int start, int end) {
        List<TreeNode> answers = new ArrayList<>();
        if (start > end) {
            answers.add(null);
        }
        for (int i = start; i <= end; i++) {
            List<TreeNode> leftList = helper(start, i - 1);
            List<TreeNode> rightList = helper(i + 1, end);
            for (TreeNode left : leftList) {
                for (TreeNode right : rightList) {
                    TreeNode root = new TreeNode(i);
                    root.left = left;
                    root.right = right;
                    answers.add(root);
                }
            }
        }
        return answers;
    }

}
