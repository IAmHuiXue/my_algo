package util;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

public class ConstructTree {
    /** construct a tree via a preOrder list */
    public static TreeNode constructTree(Integer[] preOrder) {
        if (preOrder == null || preOrder.length == 0) {
            return null;
        }
        // use LinkedList to store null
        // need to parse array to Collections before putting into queue
        Queue<Integer> queue = new LinkedList<>(Arrays.asList(preOrder));
        return constructTree(queue);
    }

    private static TreeNode constructTree(Queue<Integer> queue) {
        if (queue.isEmpty()) {
            return null;
        }
        Integer cur = queue.poll();
        if (cur == null) {
            return null;
        }
        TreeNode root = new TreeNode(cur);
        root.left = constructTree(queue);
        root.right = constructTree(queue);
        return root;
    }
}
