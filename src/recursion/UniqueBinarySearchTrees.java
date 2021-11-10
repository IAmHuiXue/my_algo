package recursion;

/** https://leetcode.com/problems/unique-binary-search-trees/ */

public class UniqueBinarySearchTrees {
    class WithOutMemo {
        public int numTrees(int n) {
            if (n == 0 || n == 1) {
                return 1;
            }
            int sum = 0;
            for (int i = 1; i <= n; i++) {
                sum += numTrees(i - 1) * numTrees(n - i);
            }
            return sum;
        }
    }

    class WithMemo {
        public int numTrees(int n) {
            return numTrees(n, new Integer[n + 1]);
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
    }

}
