package union_find;

/** Union Find (aka Disjoint Set Union) */
public class DSU {
    int[] parent;

    DSU(int n) {
        // for int n, initialize a parent array of length n, assigned the initial parent to each int itself
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }
    }

    /** outputs a unique id so that two nodes have the same id if and only if they are in the same connected component */
    int find(int x) { // find the parent to x
        if (parent[x] != x) {
            // Path compression
            parent[x] = find(parent[x]);
        }
        return parent[x];
    }

    /** draws an edge (x, y) in the graph, connecting the components with id find(x) and find(y) together */
    void union(int x, int y) { // join/union the parents of x and y
        parent[find(x)] = find(y);
    }
}
