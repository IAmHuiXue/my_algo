package tree.bst;

/**
 * https://app.laicode.io/app/problem/304?plan=16
 */

public class ValidPostOrderTraversalOfBST {
    public boolean validPostOrder(int[] post) {
        if (post.length == 0) {
            return true;
        }
        return solve(post, 0, post.length - 1);
    }

    private boolean solve(int[] arr, int start, int end) {
        if (start >= end) {
            return true;
        }

        int x = arr[end], index = end - 1;
        while (index >= start && arr[index] > x) {
            index--;
        }
        int temp = index;
        while (index >= start) {
            if (arr[index] > x) {
                return false;
            }
            index--;
        }
//        if (temp == start) {
//            return solve(arr, start, end - 1);
//        }
        return solve(arr, start, temp) && solve(arr, temp + 1, end - 1);
    }
}
