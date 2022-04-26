package trie;

import java.util.HashMap;
import java.util.Map;

/** https://leetcode.com/problems/design-file-system/ */

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
        String[] segments = path.split("/");
        TrieNode cur = root;
        for (int i = 1; i < segments.length; i++) {
            TrieNode next = cur.children.get(segments[i]);
            if (next == null) {
                if (i == segments.length - 1) {
                    next = new TrieNode(value, segments[i]);
                    cur.children.put(segments[i], next);
                    return true;
                }
                return false;
            }
            cur = next;
        }
        // if the path has already existed
        return false;
    }

    public int get(String path) {
        String[] segments = path.split("/");
        TrieNode cur = root;
        for (int i = 1; i < segments.length; i++) {
            cur = cur.children.get(segments[i]);
            if (cur == null) {
                return -1;
            }
        }
        return cur.value;
    }


    /*

    trie node

      *
    / | \



    */
}

