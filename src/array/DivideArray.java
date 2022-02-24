package array;

/*
Given an array of integers a of even length, your task is to split it into two arrays of equal length such that all the numbers are unique in each of them.

There may be more than one possible answer, in which case you may return any of them. If there are no possible answers, return an empty array.

Hint: Count the number of occurrences of each integer in a. If there are integers occurring more than twice, then there is no solution. Next, put the integers occurring twice into both answer arrays. Finally, put all other numbers in the answer arrays, following the condition that they should have equal sizes.

Example

For a = [2, 1, 2, 3, 3, 4], the output can be divideArray(a) = [[2, 1, 3], [2, 3, 4]].

Answers like [[1, 2, 3], [2, 3, 4]] or [[4, 2, 3], [3, 2, 1]] would also be considered correct.

For a = [1, 2, 2, 1], the output can be divideArray(a) = [[1, 2], [2, 1]].

Again, there are other possible answers.

For a = [2, 2, 3, 3, 2, 2], the output should be divideArray(a) = [].

No matter how we try to split this array, there will be at least two 2s in at least one of the resulting arrays. So the answer is [].

Input/Output

[execution time limit] 3 seconds (java)

[input] array.integer a

An array of integers. It is guaranteed that a has even length.

Guaranteed constraints:
2 ≤ a.length ≤ 104,
1 ≤ a[i] ≤ 105.

[output] array.array.integer

Return an empty array if there is no solution. If a solution exists, return an array of two arrays - a distribution of a where each of these two arrays are of equal length and each contains unique elements.
 */

import java.util.*;

public class DivideArray {
    int[][] divideArray(int[] a) {
        if (a.length % 2 != 0) {
            return new int[0][];
        }
        Map<Integer, Integer> map = new HashMap<>();
        for (int i : a) {
            int count = map.getOrDefault(i, 0);
            // 如果一个元素出现了 3 次以以上，不会得到有效结果
            if (count == 2) {
                return new int[0][];
            }
            map.put(i, count + 1);
        }
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>(map.entrySet());

        // 出现两次的排在最高位，这样便于在最早的时候分配它的位置分别于两个 array 里
        list.sort((x, y) -> y.getValue().compareTo(x.getValue()));

        int[] left = new int[a.length / 2];
        int[] right = new int[a.length / 2];
        int il = 0;
        int ir = 0;

        for (Map.Entry<Integer, Integer> entry : list) {
            if (entry.getValue() == 2) {
                left[il++] = entry.getKey();
                right[ir++] = entry.getKey();
            } else {
                // 如果元素只出现一次，尽可能的放进比较空的 array 里
                if (il < ir) {
                    left[il++] = entry.getKey();
                } else {
                    right[ir++] = entry.getKey();
                }
            }
        }

        return new int[][]{left, right};
    }
}
