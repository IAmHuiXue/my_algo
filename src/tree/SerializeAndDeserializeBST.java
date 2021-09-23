package tree;


import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/** https://leetcode.com/problems/serialize-and-deserialize-bst/  */
public class SerializeAndDeserializeBST {

    // use preOrder is easier for deserialize

    /** Because of BST, we do not need to store symbols to represent null, instead when we
     * deserialize it, we could determine the position of the node by the passed value range
     */
    public String serialize(TreeNode root) {
        if (root == null) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        serializeHelper(sb, root);
        return sb.deleteCharAt(sb.length() - 1).toString();
    }
    private void serializeHelper(StringBuilder sb, TreeNode root) {
        if (root == null) {
            // following operation not required
            // sb.append('#').append(',');
            return;
        }
        sb.append(root.val).append(',');
        serializeHelper(sb, root.left);
        serializeHelper(sb, root.right);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        Queue<String> queue = new ArrayDeque<>(Arrays.asList(data.split(","))); // !
        // head, left, right
        // pass value range
        return deserializeHelper(queue, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private TreeNode deserializeHelper(Queue<String> queue, int min, int max) {
        if (queue.isEmpty()) {
            return null;
        }
        // do not poll, firstly check if the top node should be constructed here
        String cur = queue.peek();
        if (cur == null) {
            return null;
        }
        int val = Integer.parseInt(cur);
        if (val <= min || val >= max) {
            return null;
        }
        // otherwise, we construct it here
        queue.poll();
        TreeNode root = new TreeNode(val);
        root.left = deserializeHelper(queue, min, val);
        root.right = deserializeHelper(queue, val, max);
        return root;
    }
}
