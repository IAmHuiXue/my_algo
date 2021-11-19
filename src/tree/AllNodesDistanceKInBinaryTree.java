package tree;

import util.TreeNode;

import java.util.*;

/**
 * https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/
 */

public class AllNodesDistanceKInBinaryTree {
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {

        // All the values Node.val are unique.
        // target is the value of one of the nodes in the tree.

        // 如果没有树结构的单向 pointer 的限制，那么这道题就可以用 bfs 图 的方法解决
        // 如何克服限制 - traverse the entire tree and record the parent node of each child node

        // traverse the entire tree and record the parent info of each node
        Map<TreeNode, TreeNode> relation = new HashMap<>();
        traverse(relation, root, null);
        return bfs(target, k, relation);
    }

    private void traverse(Map<TreeNode, TreeNode> relation, TreeNode root, TreeNode parent) {
        if (root == null) {
            return;
        }
        relation.put(root, parent);
        traverse(relation, root.left, root);
        traverse(relation, root.right, root);
    }

    private List<Integer> bfs(TreeNode target, int k, Map<TreeNode, TreeNode> relation) {
        List<Integer> result = new ArrayList<>();
        Queue<Cell> q = new ArrayDeque<>();
        q.offer(new Cell(target, 0));
        Set<TreeNode> seen = new HashSet<>();
        seen.add(target);
        while (!q.isEmpty()) {
            Cell cur = q.poll();
            TreeNode parent = relation.get(cur.node);
            TreeNode curNode = cur.node;
            int distance = cur.distance;
            if (distance == k) {
                result.add(curNode.val);
                // no need to go further this path
                continue;
            }
            if (curNode.left != null && seen.add(curNode.left)) {
                q.offer(new Cell(curNode.left, distance + 1));
            }
            if (curNode.right != null && seen.add(curNode.right)) {
                q.offer(new Cell(curNode.right, distance + 1));
            }
            if (parent != null && seen.add(parent)) {
                q.offer(new Cell(parent, distance + 1));
            }

        }
        return result;
    }

    static class Cell {
        TreeNode node;
        int distance;

        Cell(TreeNode n, int d) {
            node = n;
            distance = d;
        }
    }
}
