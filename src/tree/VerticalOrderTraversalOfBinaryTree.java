package tree;

import util.TreeNode;

import java.util.*;

/** https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/ */

public class VerticalOrderTraversalOfBinaryTree {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        Queue<Cell> queue = new ArrayDeque<>();
        Map<Integer, List<Cell>> colsToNode = new HashMap<>();
        queue.offer(new Cell(root, 0, 0));
        int min = 0;
        while (!queue.isEmpty()) {
            Cell cur = queue.poll();
            int row = cur.row;
            int col = cur.col;
            min = Math.min(min, col);
            colsToNode.computeIfAbsent(col, k -> new ArrayList<>()).add(new Cell(cur.node, row, col));
            if (cur.node.left != null) {
                queue.offer(new Cell(cur.node.left, row + 1, col - 1));
            }
            if (cur.node.right != null) {
                queue.offer(new Cell(cur.node.right, row + 1, col + 1));
            }
        }
        while (colsToNode.containsKey(min)) {
            List<Cell> list = colsToNode.get(min);
            // sort the list based on the pre-defined rule of class Cell
            Collections.sort(list);
            List<Integer> subResult = new ArrayList<>();
            // result needs node.val
            for (Cell c : list) {
                subResult.add(c.node.val);
            }
            result.add(subResult);
            min++;
        }
        return result;

    }
    static class Cell implements Comparable<Cell> {
        TreeNode node;
        int row;
        int col;

        Cell(TreeNode n, int r, int c) {
            node = n;
            row = r;
            col = c;
        }
        // rowIndex most priority
        // then value
        @Override
        public int compareTo(Cell c1) {
            if (this.row != c1.row) {
                return Integer.compare(this.row, c1.row);
            }
            return Integer.compare(this.node.val, c1.node.val);
        }
    }
}
