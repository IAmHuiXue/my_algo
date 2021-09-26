package tree.construct;

import java.util.*;

/**
 * https://leetcode.com/problems/serialize-and-deserialize-n-ary-tree/
 */

public class SerializeAndDeserializeNnaryTree {

    // use preOrder is easier for deserialize

    // Encodes a tree to a single string.
    public String serialize(Node root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(sb, root);
        return sb.deleteCharAt(sb.length() - 1).toString();
    }

    private void serializeHelper(StringBuilder sb, Node root) {
        if (root == null) {
            sb.append('#').append(',');
            return;
        }
        sb.append(root.val).append(',');
        /** need to memorize the size of the child nodes here for later deserialization! */
        sb.append(root.children.size()).append(',');
        for (Node child : root.children) {
            serializeHelper(sb, child);
        }
    }

    // Decodes your encoded data to tree.
    public Node deserialize(String data) {
        if (data == null || data.length() == 0) {
            return null;
        }
        Queue<String> queue = new ArrayDeque<>(Arrays.asList(data.split(","))); // !
        // head, left, right
        return deserializeHelper(queue);
    }

    private Node deserializeHelper(Queue<String> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        String cur = queue.poll(); // first poll -> nodeValue
        if (cur.equals("#")) {
            return null;
        }
        /** if cur is not null, there must be another queue.poll() followed representing the size of cur's child nodes */
        Node root = new Node(Integer.parseInt(cur), new ArrayList<>());
        int size = Integer.parseInt(queue.poll()); // second poll -> childSize
        for (int i = 0; i < size; i++) {
            root.children.add(deserializeHelper(queue));
        }
        return root;
    }
}

class Node {
    public int val;
    public List<Node> children;

    public Node(int val) {
        this.val = val;
    }

    public Node(int val, List<Node> children) {
        this.val = val;
        this.children = children;
    }
}
