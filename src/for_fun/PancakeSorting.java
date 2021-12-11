package for_fun;

import java.util.ArrayList;
import java.util.List;

/** https://leetcode.com/problems/pancake-sorting/ */

public class PancakeSorting {

    // key：
    // 每一轮找最大值，以它为右边界做一次 pancake swap
    // 再以它正确的位置作为右边界再做一次 pancake swap，它就被放到了正确的位置
    // 以此类推

    public List<Integer> pancakeSort(int[] arr) {
        List<Integer> res = new ArrayList<>();
        helper(res, arr, arr.length - 1);
        return res;
    }

    private void helper(List<Integer> res, int[] arr, int rightBound) {
        if (rightBound == 0) {
            return;
        }
        int index = findLargest(arr, rightBound);
        int num = arr[index];
        res.add(index + 1);
        swap(arr, index);
        res.add(num);
        swap(arr, num - 1);
        helper(res, arr, rightBound - 1);
    }

    private int findLargest(int[] arr, int right) {
        int index = -1;
        int largest = Integer.MIN_VALUE;
        for (int i = 0; i <= right; i++) {
            if (largest < arr[i]) {
                index = i;
                largest = arr[i];
            }
        }
        return index;
    }

    private void swap(int[] arr, int j) {
        int i = 0;
        while (i < j) {
            int tmp = arr[i];
            arr[i++] = arr[j];
            arr[j--] = tmp;
        }
    }
}
