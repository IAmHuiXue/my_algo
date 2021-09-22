package tree;

import util.TreeNode;

import java.util.*;

/** https://leetcode.com/problems/binary-tree-vertical-order-traversal/ */
public class BinaryTreeVerticalOrderTraversal {
  public List<List<Integer>> verticalOrder(TreeNode root) {
    List<List<Integer>> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    // keep track of the col position of each node visited
    // compute only when the node is polled from queue
    Map<TreeNode, Integer> cols = new HashMap<>();
    // keep track of the values of the nodes that belong to each col position
    // compute only when the node is offered into queue
    Map<Integer, List<Integer>> colToNode = new HashMap<>();
    Queue<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);
    // set the position of root as col 0
    // so its left child node is at col -1
    // right child node is at col 1, and so on
    cols.put(root, 0);
    // maintain the min_col for later processing
    int min = 0;
    while (!queue.isEmpty()) {
      TreeNode cur = queue.poll();
      int col = cols.get(cur);
       colToNode.computeIfAbsent(col, k -> new ArrayList<>()).add(cur.val);
//      if (!colToNode.containsKey(col)) {
//        colToNode.put(col, new ArrayList<>());
//      }
//      colToNode.get(col).add(cur.val);
      if (cur.left != null) {
        queue.offer(cur.left);
        cols.put(cur.left, col - 1);
      }
      if (cur.right != null) {
        queue.offer(cur.right);
        cols.put(cur.right, col + 1);
      }
      min = Math.min(min, col);
    }
    // now we need to build the final result starting from the leftmost col --> min
    while (colToNode.containsKey(min)) {
      result.add(colToNode.get(min++));
    }
    return result;
  }
}
