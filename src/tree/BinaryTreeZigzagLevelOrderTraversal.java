package tree;

import util.TreeNode;

import java.util.*;

/** https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/ */

public class BinaryTreeZigzagLevelOrderTraversal {
    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        boolean zigzag = true;
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> curLayer = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                if (zigzag) {
                    curLayer.add(cur.val);
                } else {
                    curLayer.add(0, cur.val);
                }
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            res.add(curLayer);
            zigzag = !zigzag;
        }
        return res;
    }
}
