package dfs;


import java.util.HashSet;
import java.util.Set;

/** https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/ */

public class MostStonesRemoved {
    public int removeStones(int[][] stones) {
        // in the entire graph, each set of stones who share the same rows or cols consists of one island
        // all the nodes on this island can be removed eventually except for the last one
        // so in the end, there will be numOfIslands stones left
        // therefore, the max num of removed stones = # of stones - # of islands

        // here is how to calculate # of islands

        int numIslands = 0;
        Set<int[]> seen = new HashSet<>();
        for (int[] stone : stones) {
            if (seen.add(stone)) {
                numIslands++;
                dfs(stone, stones, seen);
            }
        }
        return stones.length - numIslands;
    }

    private void dfs(int[] curStone, int[][] stones, Set<int[]> seen) {
        int rowIndex = curStone[0];
        int colIndex = curStone[1];
        for (int[] anotherStone : stones) {
            if (curStone == anotherStone) {
                continue;
            }
            if ((rowIndex == anotherStone[0] || colIndex == anotherStone[1]) && seen.add(anotherStone)) {
                dfs(anotherStone, stones, seen);
            }
        }
    }
}
