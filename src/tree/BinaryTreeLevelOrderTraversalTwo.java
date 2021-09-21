package tree;

import util.TreeNode;

import java.util.*;

/** https://leetcode.com/problems/binary-tree-level-order-traversal-ii/ */

public class BinaryTreeLevelOrderTraversalTwo {
    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        // use LinkedList to implement because of better time for add(0, value) operation
        List<List<Integer>> result = new LinkedList<>();
        if (root == null) {
            return result;
        }
        Queue<TreeNode> q = new ArrayDeque<>();
        q.offer(root);
        while (!q.isEmpty()) {
            int size = q.size();
            List<Integer> curLayer = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                TreeNode cur = q.poll();
                curLayer.add(cur.val);
                if (cur.left != null) {
                    q.offer(cur.left);
                }
                if (cur.right != null) {
                    q.offer(cur.right);
                }
            }
            // we add the result of each layer always to the head of the result list
            result.add(0, curLayer);
        }
        return result;
    }
}
