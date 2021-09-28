package tree.construct;

import util.TreeNode;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-binary-tree/
 */

public class SerializeAndDeserializeBinaryTree {

    // use preOrder is easier for deserialize

    public String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(sb, root);
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private void serializeHelper(StringBuilder sb, TreeNode root) {
        if (root == null) {
            // use '#' to represent null
            sb.append('#').append(',');
            return;
        }
        // sb.append(int) <=> sb.append((char)(int + '0'))!
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
        return deserializeHelper(queue);
    }

    private TreeNode deserializeHelper(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String cur = queue.poll();
        if (cur.equals("#")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(cur));
        root.left = deserializeHelper(queue);
        root.right = deserializeHelper(queue);
        return root;
    }
}
