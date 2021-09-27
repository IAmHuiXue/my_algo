package tree.construct;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/** https://app.laicode.io/app/problem/212?plan=16 */

public class ReconstructBinarySearchTreeWithLevelOrderTraversal {

    /** constructing binary search tree with levelorder[], we need to consider to
     * divid the elements into two sub-lists by their values
     */

    public TreeNode reconstruct(int[] level) {
        List<Integer> list = new ArrayList<>(level.length);
        for (int l : level) {
            list.add(l);
        }
        return reconstruct(list);
    }

    private TreeNode reconstruct(List<Integer> list) {
        if (list.size() == 0) {
            return null;
        }
        TreeNode root = new TreeNode(list.remove(0));
        List<Integer> left = new ArrayList<>();
        List<Integer> right = new ArrayList<>();
        for (int num : list) {
            if (num < root.val) {
                left.add(num);
            } else {
                right.add(num);
            }
        }
        root.left = reconstruct(left);
        root.right = reconstruct(right);
        return root;
    }
}
