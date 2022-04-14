package tree.pass_down;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/** https://leetcode.com/problems/binary-tree-right-side-view/ */

public class BinaryTreeRightSideView {
    public List<Integer> rightSideViewDFS(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(root, res, 0);
        return res;
    }

    void dfs(TreeNode root, List<Integer> res, int depth) {
        if (root == null) {
            return;
        }
        // when the root is the first node visited for this depth, it is the answer for this level
        if (res.size() == depth) {
            res.add(root.val);
        }
        // always choose the right subtree first
        dfs(root.right, res, depth + 1);
        dfs(root.left, res, depth + 1);
    }

    public List<Integer> rightSideViewBFS(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while(!queue.isEmpty()) {
            int curSize = queue.size();
            for (int i = 0; i < curSize; i++) {
                TreeNode cur = queue.poll();
                if (i == 0) {
                    result.add(cur.val);
                }
                // always choose the right subtree first
                if (cur.right != null) {
                    queue.offer(cur.right);
                }
                if (cur.left != null) {
                    queue.offer(cur.left);
                }
            }
        }
        return result;
    }
}
