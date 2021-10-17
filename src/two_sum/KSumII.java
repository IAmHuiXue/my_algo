package two_sum;

import java.util.HashMap;
import java.util.Map;

public class KSumII {
    public static int kSumCount(int[][] lists) {
        Map<Integer, Integer> m = new HashMap<>();
        addToHash(lists, m, 0, 0);
        return countComplements(lists, m, lists.length / 2, 0);
    }

    private static void addToHash(int[][] lists, Map<Integer, Integer> m, int i, int sum) {
        if (i == lists.length / 2) {
            m.put(sum, m.getOrDefault(sum, 0) + 1);
            return;
        }
        for (int a : lists[i])
            addToHash(lists, m, i + 1, sum + a);
    }

    private static int countComplements(int[][] lists, Map<Integer, Integer> m, int i, int complement) {
        if (i == lists.length) {
            return m.getOrDefault(complement, 0);
        }
        int cnt = 0;
        for (int a : lists[i]) {
            cnt += countComplements(lists, m, i + 1, complement - a);
        }
        return cnt;
    }
}
