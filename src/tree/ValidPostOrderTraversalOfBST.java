package tree;

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

    private boolean solve(int[] arr, int strt, int end) {
        if (strt >= end) {
            return true;
        }

        int x = arr[end], index = end - 1;
        while (index >= strt && arr[index] > x) {
            index--;
        }
        int temp = index;
        while (index >= strt) {
            if (arr[index] > x) {
                return false;
            }
            index--;
        }
//        if (temp == strt) {
//            return solve(arr, strt, end - 1);
//        }
        return solve(arr, strt, temp) && solve(arr, temp + 1, end - 1);
    }
}
