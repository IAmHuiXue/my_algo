package dfs;

/** https://leetcode.com/problems/unique-binary-search-trees/ */

public class UniqueBinarySearchTrees {
    public int numTrees(int n) {
        Integer[] memo = new Integer[n + 1];
        return numTrees(n, memo);
    }

    private int numTrees(int n, Integer[] memo) {
        if (n == 0 || n == 1) {
            return 1;
        }
        if (memo[n] != null) {
            return memo[n];
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += numTrees(i - 1, memo) * numTrees(n - i, memo);
        }
        memo[n] = sum;
        return sum;
    }

    public int numTreesNaive(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        int sum = 0;
        for (int i = 1; i <= n; i++) {
            sum += numTreesNaive(i - 1) * numTreesNaive(n - i);
        }
        return sum;
    }

}
