package trie;

import java.util.HashMap;
import java.util.Map;

/** <a href="https://leetcode.com/problems/design-file-system/">https://leetcode.com/problems/design-file-system/</a> */

public class DesignFileSystem {
    TrieNode root;

    static class TrieNode {
        int value;
        String path;
        Map<String, TrieNode> children = new HashMap<>();

        TrieNode(int value, String path) {
            this.value = value;
            this.path = path;
        }
    }

    public DesignFileSystem() {
        root = new TrieNode(0, "");
    }

    public boolean createPath(String path, int value) {
        String[] segments = resolvePath(path);
        TrieNode cur = root;
        for (int i = 1; i < segments.length - 1; i++) {
            cur = cur.children.get(segments[i]);
            if (cur == null) {
                return false;
            }
        }
        String lastPath = segments[segments.length - 1];
        if (cur.children.containsKey(lastPath)) { // if the existing path already exits
            return false;
        }
        cur.children.put(lastPath, new TrieNode(value, segments[segments.length - 1]));
        return true;
    }

    public int get(String path) {
        String[] segments = resolvePath(path);
        TrieNode cur = root;
        for (int i = 1; i < segments.length; i++) {
            cur = cur.children.get(segments[i]);
            if (cur == null) {
                return -1;
            }
        }
        return cur.value;
    }

    String[] resolvePath(String path) {
        return path.split("/");
    }


    /*

    trie node

      *
    / | \



    */
}

