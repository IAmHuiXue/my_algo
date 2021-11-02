package tree;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/** https://leetcode.com/problems/sum-of-root-to-leaf-binary-numbers/ */

public class SumOfRootToLeafBinaryNumbers {
    public int sumRootToLeaf(TreeNode root) {
        List<List<Integer>> list = new ArrayList<>();
        dfs(root, list, new ArrayList<>());
        int res = 0;
        for (List<Integer> num: list) {
            res += convert(num);
        }
        return res;
    }

    /** preOrder traversal */
    private void dfs(TreeNode root, List<List<Integer>> list, List<Integer> cur) {
        int size = cur.size();
        cur.add(root.val);
        if (root.left == null && root.right == null) {
            list.add(new ArrayList<>(cur));
        }
        if (root.left != null) {
            dfs(root.left, list, cur);
        }
        if (root.right != null) {
            dfs(root.right, list, cur);
        }
        cur.remove(cur.size() - 1);
    }

    /** convert a binary to decimal */
    private int convert(List<Integer> num) {
        double res = 0;
        int j = 0;
        for (int i = num.size() - 1; i >= 0; i--) {
            res += num.get(i) * Math.pow(2, j++);
        }
        return (int) res;
    }
}
