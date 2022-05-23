package array;

/**
 * <a href="https://app.laicode.io/app/problem/201?plan=3">https://app.laicode.io/app/problem/201?plan=3</a>
 */

public class LargestContainer {
    public int largest(int[] array) {
        // 左右挡板，谁小以谁为基准，计算面积，然后向中心移动
        int start = 0;
        int end = array.length - 1;
        int max = Integer.MIN_VALUE;
        while (start < end) {
            if (array[start] < array[end]) {
                max = Math.max(max, array[start] * (end - start));
                start++;
            } else {
                max = Math.max(max, array[end] * (end - start));
                end--;
            }
        }
        return max;
    }
}
