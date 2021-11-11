package union_find;

/** https://leetcode.com/problems/number-of-provinces/ */

public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        DSU dsu = new DSU(isConnected.length);
        // adjacency matrix
        // each i is a labeled node, j is a labeled node
        // i-j represent the relationship between the two nodes
        // in this case, i-j = j-i
        // i-i represent node to itself (always == 1 in this case)

        // 1. union
        for (int i = 0; i < isConnected.length; i ++) {
            for (int j = 0; j < isConnected[i].length; j++) {
                // if node i and j are friends, union them
                if (isConnected[i][j] == 1) {
                    dsu.union(i, j);
                }
            }
        }
        // 2. find and count disjoint sets
        int res = 0;
        for (int i = 0; i < isConnected.length; i++) {
            // if i's parent is itself, means it is in the component rooted at itself
            if (dsu.find(i) == i) {
                res++;
            }
        }
        return res;
    }
}
