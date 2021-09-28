package util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class PrintTree {
    private PrintTree() {}

    static final List<Integer> result = new ArrayList<>();

    public static void printTree(TreeNode root) {
        if (root != null) {
            levelOrder(root);
            while (result.get(result.size() - 1) == null) { // trim the trailing null
                result.remove(result.size() - 1);
            }
        }
        System.out.println(result);
    }

    private static void levelOrder(TreeNode root) {

        Queue<TreeNode> q = new LinkedList<>();
        q.offer(root);
        while (!q.isEmpty()) {
            TreeNode cur = q.poll();
            if (cur != null) {
                result.add(cur.val);
            } else {
                result.add(null);
            }
            if (cur != null) {
                q.offer(cur.left);
                q.offer(cur.right);
            }
        }
    }
}
