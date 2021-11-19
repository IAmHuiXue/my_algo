package tree;

import util.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/binary-tree-vertical-order-traversal/
 */

public class VerticalOrderTraversalOfBinaryTreeI {
    public List<List<Integer>> verticalOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // keep track of the values of the nodes that belong to each col position
        // compute only when the node is offered into queue
        Map<Integer, List<Integer>> colToNode = new HashMap<>();
        Queue<Cell> queue = new ArrayDeque<>();
        queue.offer(new Cell(root, 0));
        // maintain the min_col for later processing
        int min = 0;
        while (!queue.isEmpty()) {
            Cell cur = queue.poll();
            TreeNode curNode = cur.node;
            int col = cur.col;
            colToNode.computeIfAbsent(col, k -> new ArrayList<>()).add(curNode.val);
            if (curNode.left != null) {
                queue.offer(new Cell(curNode.left, col - 1));
            }
            if (curNode.right != null) {
                queue.offer(new Cell(curNode.right, col + 1));
            }
            min = Math.min(min, col);
        }
        // now we need to build the final result starting from the leftmost col --> min
        while (colToNode.containsKey(min)) {
            result.add(colToNode.get(min++));
        }
        return result;
    }

    static class Cell {
        TreeNode node;
        int col;

        Cell(TreeNode n, int c) {
            node = n;
            col = c;
        }
    }
}
