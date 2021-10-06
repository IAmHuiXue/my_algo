package dfs;

import util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.com/problems/find-leaves-of-binary-tree/
 */

public class FindLeavesOfBinaryTree {
    static class DFSWithSort {
        public List<List<Integer>> findLeaves(TreeNode root) {
            // [node.val, height of node]
            List<int[]> collection = new ArrayList<>();
            getHeight(root, collection);
            // sort by the height of each node in ascending order
            collection.sort((c1, c2) -> Integer.compare(c1[1], c2[1]));
            List<List<Integer>> result = new ArrayList<>();
            int i = 0;
            int curHeight = 0;
            while (i < collection.size()) {
                List<Integer> tmp = new ArrayList<>();
                while (i < collection.size() && collection.get(i)[1] == curHeight) {
                    tmp.add(collection.get(i)[0]);
                    i++;
                }
                result.add(tmp);
                curHeight++;
            }
            return result;
        }

        private int getHeight(TreeNode root, List<int[]> collection) {
            if (root == null) {
                return -1;
            }
            int leftHeight = getHeight(root.left, collection);
            int rightHeight = getHeight(root.right, collection);
            int currHeight = Math.max(leftHeight, rightHeight) + 1;
            collection.add(new int[]{root.val, currHeight});
            return currHeight;
        }
        // time: O(n + nlog(n))
    }

    static class DFSWithoutSort {
        private List<List<Integer>> result;

        public List<List<Integer>> findLeaves(TreeNode root) {
            result = new ArrayList<>();
            getHeight(root);
            return result;
        }

        private int getHeight(TreeNode root) {
            if (root == null) {
                return -1;
            }
            int leftHeight = getHeight(root.left);
            int rightHeight = getHeight(root.right);
            int currHeight = Math.max(leftHeight, rightHeight) + 1;

            // !
            if (result.size() == currHeight) {
                result.add(new ArrayList<>());
            }
            result.get(currHeight).add(root.val);

            return currHeight;
        }
    }
}
