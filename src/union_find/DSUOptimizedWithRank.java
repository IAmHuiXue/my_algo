package union_find;

/** rank 比 size 更好的是解决了 size 会存在的 corner case
 * union 两个 component 的时候，优化真正需要比较的是 height，而不是 size 的大小
 *
 *
 * */
public class DSUOptimizedWithRank {
    int[] parent;
    // 优化1
    int[] rank; // to count the height of each connected component

    public DSUOptimizedWithRank(int n) {
        parent = new int[n];
        rank = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            // initially, each set has a height of 1 (only contains itself)
            rank[i] = 1;
        }
    }

    int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    void union(int x, int y) { // connected if consecutive
        int rootX = find(x);
        int rootY = find(y);
        // 优化2 -> height 小的 component 加入 height 大的 component 里
        // 这样保证加入后 原来大的 component 的深度/高度不变
        if (rootX != rootY) {
            // 高度低的向高度高的 component 合并
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                // 只有在 rank 相等的时候才需要维护 rank
                // 任选一边作为 parent，然后加入后 parent 的高度 加 1
                parent[rootX] = rootY;
                rank[rootY]++;
            }
        }
    }
    // 优化后的时间复杂度是 O(log* n), 比log(n) 快很多，比 O(1) 稍慢一点, 可以理解为 amortized O(1)
    // find 性能和component 的深度有关系，像树的高度， 每一个 component 是一个多叉树
    // 加上size和path compression 的优化后，并查集的时间复杂度是 O(log* n)
}
