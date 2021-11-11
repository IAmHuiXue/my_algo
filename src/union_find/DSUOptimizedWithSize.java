package union_find;

public class DSUOptimizedWithSize {
    int[] parent;
    // 优化1
    int[] size; // to count the size of each connected component

    public DSUOptimizedWithSize(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            // initially, each set has a size of 1 (only contains itself)
            size[i] = 1;
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
        // 优化2 -> size 小的 component 加入 size 大的 component 里
        // 这样保证加入后 原来大的 component 的深度/高度不变
        if (rootX != rootY) {
            if (size[rootX] <= size[rootY]) {
                parent[rootX] = rootY;
                size[rootY] += size[rootX]; // remember to update size
            } else {
                parent[rootY] = rootX;
                size[rootX] += size[rootY]; // remember to update size
            }
        }
    }
    // 优化后的时间复杂度是 O(log* n), 比log(n) 快很多，比 O(1) 稍慢一点, 可以理解为 amortized O(1)
    // find 性能和component 的深度有关系，像树的高度， 每一个 component 是一个多叉树
    // 加上size和path compression 的优化后，并查集的时间复杂度是 O(log* n)


}
