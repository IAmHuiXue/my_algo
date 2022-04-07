package tree;

import util.TreeNode;

import java.util.*;

/** https://leetcode.com/problems/vertical-order-traversal-of-a-binary-tree/ */

public class VerticalOrderTraversalOfBinaryTreeII {
    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        // Cell can carry row and col info in addition to TreeNode
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
            List<Cell> list = colsToNode.get(min++);
            // sort the list based on the pre-defined rule of class Cell
            Collections.sort(list);
            List<Integer> subResult = new ArrayList<>();
            // result needs node.val
            for (Cell c : list) {
                subResult.add(c.node.val);
            }
            result.add(subResult);
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
        public int compareTo(Cell anotherC) {
            if (this.row != anotherC.row) {
                return Integer.compare(this.row, anotherC.row);
            }
            return Integer.compare(this.node.val, anotherC.node.val);
        }
    }

    public List<List<Integer>> verticalTraversalDFS(TreeNode root) {
        // key=col, value=list<int[] -> {node.val, row}>
        Map<Integer, List<int[]>> map = new HashMap<>();
        preOrder(root, map, 0, 0);
        List<Integer> keys = new ArrayList<>(map.keySet());

        Collections.sort(keys);
        List<List<Integer>> res = new ArrayList<>();
        for (int k : keys) {
            List<int[]> list = map.get(k);
            list.sort((a, b) -> {
                int rA = a[1];
                int rB = b[1];
                if (rA != rB) {
                    return Integer.compare(rA, rB);
                }
                return Integer.compare(a[0], b[0]);
            });
            List<Integer> tmp = new ArrayList<>();
            for (int[] i : list) {
                tmp.add(i[0]);
            }
            res.add(tmp);
        }
        return res;
    }

    void preOrder(TreeNode root, Map<Integer, List<int[]>> map, int row, int col) {
        if (root == null) {
            return;
        }
        map.computeIfAbsent(col, k -> new ArrayList<>()).add(new int[]{root.val, row});
        preOrder(root.left, map, row + 1, col - 1);
        preOrder(root.right, map, row + 1, col + 1);
    }
}
