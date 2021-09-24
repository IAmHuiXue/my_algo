package tree.pass_down;

import util.TreeNode;

import java.util.ArrayList;
import java.util.List;

/** https://leetcode.com/problems/binary-tree-paths/ */

public class BinaryTreePaths {
    List<String> ans;
    public List<String> binaryTreePaths(TreeNode root) {
        ans = new ArrayList<>();
        solve(root, new StringBuilder());
        return ans;
    }
    public void solve(TreeNode root, StringBuilder res){
        if(root == null)
            return;

        // sometimes it is good to record the length first before appending new elements
        // in this case, the length of new element is not uniform, like it could be 1 -> '1', 11 -> '1','1'
        // or -1 -> '-','1'
        int len = res.length();
        res.append(root.val);
        if(root.left == null && root.right == null){
            ans.add(res.toString());
        }
        else{
            res.append("->");
            if(root.left != null)
                solve(root.left, res);
            if(root.right != null)
                solve(root.right, res);
        }
        // this API is easier to set the length back to initial stage
        res.setLength(len);
    }


//    public List<String> binaryTreePaths(TreeNode root) {
//         List<String> result = new ArrayList<>();
//         if (root == null) {
//             return result;
//         }
//         dfs(new ArrayList<>(), root, result);
//         return result;
//     }
//
//     private void dfs(List<Integer> list, TreeNode root, List<String> result) {
//         list.add(root.val);
//         if (root.left == null && root.right == null) {
//             StringBuilder sb = new StringBuilder();
//             for (int val : list) {
//                 sb.append(val).append("->");
//             }
//             result.add(sb.delete(sb.length() - 2, sb.length()).toString());
//             list.remove(list.size() - 1);
//             return;
//         }
//         if (root.left != null) {
//              dfs(list, root.left, result);
//         }
//         if (root.right != null) {
//              dfs(list, root.right, result);
//         }
//         list.remove(list.size() - 1);
//     }
}
