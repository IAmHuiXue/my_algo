package union_find;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/number-of-islands-ii/
 */

public class NumberOfIslandsII {
    static final int[][] DIRS = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};

    public List<Integer> numIslands2(int m, int n, int[][] positions) {
        DSU dsu = new DSU(m * n);
        List<Integer> res = new ArrayList<>();
        boolean[][] isIsland = new boolean[m][n];
        int count = 0;
        for (int[] po : positions) {
            // if the same po[] has shown up before, the count this time will not change,
            // so just continue
            if (isIsland[po[0]][po[1]]) {
                res.add(count);
                continue;
            }
            isIsland[po[0]][po[1]] = true;
            // 先加上
            count++;
            // 在附近找能 reach 到的岛屿，然后找那个岛屿和当前岛屿的 parent
            // 如果不同，就 union 起来并且 count--
            for (int[] dir : DIRS) {
                int nr = po[0] + dir[0];
                int nc = po[1] + dir[1];
                if (nr >= 0 && nc >= 0 && nr < m && nc < n && isIsland[nr][nc]) {
                    // 每个元素是在 find() 的过程中才更新它的最新 parent 的
                    int parent1 = dsu.find(nr * n + nc);
                    int parent2 = dsu.find(po[0] * n + po[1]);
                    // 下面的 if condition 很关键，因为如果他们的 parent 已经一样了，就说明之前肯定
                    // 已经 union 过 且已经更新过 count 了，所以就不应该再更新 count
                    if (parent1 != parent2) {
                        dsu.union(parent1, parent2);
                        count--;
                    }
                }
            }
            res.add(count);
        }
        return res;

    }


}


