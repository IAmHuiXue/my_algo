package dfs;

import util.Swap;

/** https://leetcode.com/problems/beautiful-arrangement/ */

public class BeautifulArrangement {
    public int countArrangement(int n) {
        if (n == 1) {
            return 1;
        }
        int[] array = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            array[i] = i;
        }
        int[] num = new int[1];
        dfs(array, num, 1);
        return num[0];
    }

    private void dfs(int[] array, int[] num, int index) {
        if (index == array.length) {
            for (int i = 1; i <= array.length - 1; i++) {
                if (array[i] % i != 0 && i % array[i] != 0) {
                    return;
                }
            }
            num[0]++;
            return;
        }
        for (int i = index; i < array.length; i++) {
            Swap.swap(array, i, index);
            if (array[index] % index == 0 || index % array[index] == 0) { // 提前判断，剪枝
                dfs(array, num, index + 1);
            }
            Swap.swap(array, i, index);
        }
    }
}
