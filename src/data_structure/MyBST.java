package data_structure;

public class MyBST {
    // there is no duplicate keys in the tree
    static class TreeNode {
        public int key;
        public TreeNode left;
        public TreeNode right;

        public TreeNode(int key) {
            this.key = key;
        }
    }
    public TreeNode root;
    // there is no need to have a specified constructor

    /* Insert a key in a binary search tree if the binary search tree does not already contain the key.
    Return the root of the binary search tree. */
    public TreeNode insert(int value) {
        /** this method may change the property of the tree */
        root = insert(root, value);
        return root;
    }

    private TreeNode insert(TreeNode curRoot, int value) {
        if (curRoot == null) {
            return new TreeNode(value);
        }
        if (curRoot.key < value) {
            // find a proper position in right subtree, insert, and return curRoot.right
            // notice that the returned curRoot.right could be updated
            curRoot.right = insert(curRoot.right, value);
        } else if (curRoot.key > value) {
            curRoot.left = insert(curRoot.left, value);
        }
        // if curRoot.key == value, do nothing
        return curRoot;
    }

    /* Find the target key K in the given binary search tree,
    return the node that contains the key if K is found, otherwise return null. */
    public TreeNode search(int value) {
        return search(root, value);
    }

    private TreeNode search(TreeNode curRoot, int value) {
        if (curRoot == null) {
            return null;
        }
        if (curRoot.key < value) {
            return search(curRoot.right, value);
        }
        if (curRoot.key > value) {
            return search(curRoot.left, value);
        }
        // curRoot.key == value
        return curRoot;
    }

    /* Delete the target key K in the given binary search tree if the binary search tree contains K.
    Return the root of the binary search tree. After deletion the binary search tree's property should be maintained. */
    public TreeNode delete(int value) {
        /** this method may change the property of the tree */
        root = delete(root, value);
        return root;
    }

    private TreeNode delete(TreeNode curRoot, int value) {
        // step 1 find out if the target node exists in the tree
        if (curRoot == null) {
            return null;
        }
        if (curRoot.key != value) {
            if (curRoot.key > value) {
                // find a proper position in right subtree, delete, and return curRoot.right
                // notice that the returned curRoot.right could be updated
                curRoot.left = delete(curRoot.left, value);
            } else {
                curRoot.right = delete(curRoot.right, value);
            }
            return curRoot;
        }

        // now curRoot.key == value
        // step 2 properly delete the target node and maintain BST's integrity
        // now curRoot.key == value, so curRoot needs to be deleted
        // if curRoot does not have one child or both
        if (curRoot.left == null) {
            return curRoot.right;
        }
        if (curRoot.right == null) {
            return curRoot.left;
        }
        // now curRoot has both children

        // we choose the smallest larger node to replace the curRoot's position
        if (curRoot.right.left == null) {
            // curRoot.right is the smallest larger node
            curRoot.right.left = curRoot.left;
            return curRoot.right;
        }
        // otherwise, we need to find the smallest larger node in curRoot.right's left subtree
        TreeNode smallest = liftSmallest(curRoot.right);
        smallest.left = curRoot.left;
        smallest.right = curRoot.right;
        return smallest;
    }

    private TreeNode liftSmallest(TreeNode root) {
        // root is not null and root.left is not null
        while (root.left.left != null) {
            root = root.left;
        }
        TreeNode smallest = root.left;
        root.left = smallest.right;
        return smallest;
    }

}
