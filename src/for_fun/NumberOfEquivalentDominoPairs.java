package for_fun;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * https://leetcode.com/problems/number-of-equivalent-domino-pairs/
 */

public class NumberOfEquivalentDominoPairs {
    public int numEquivDominoPairs(int[][] dominoes) {
        Map<Cell, Integer> map = new HashMap<>();
        int res = 0;
        for (int[] d : dominoes) {
            Cell cur = new Cell(d[0], d[1]);
            int count = map.getOrDefault(cur, 0);
            if (count >= 1) {
                res += count;
            }
            map.put(cur, count + 1);
        }
        return res;
    }

    static class Cell {
        int r;
        int c;

        Cell(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Cell cell = (Cell) o;
            return (this.r == cell.r && this.c == cell.c) || (this.r == cell.c && this.c == cell.r);
        }

        @Override
        public int hashCode() {
            // ! pay attention o here
            return Objects.hash(r, c) + Objects.hash(c, r);
        }
    }
}
