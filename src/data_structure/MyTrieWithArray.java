package data_structure;

import java.util.ArrayList;
import java.util.List;

// Trie or prefix tree is a tree data structure, which is used for retrieval of a key in a dataset of strings.
// Its nodes have the following fields:

// 1. boolean field which specifies whether the node corresponds to the end of the key, or is just a key prefix.
// 2. Maximum of R links to its children, where each link corresponds to one of R character values
// from dataset alphabet. In this article we assume that R is 26, the number of lowercase latin letters.
// there are two ways to represent this --> HashMap or TrieNode[]
// why does an array work?
// an array of size 26, each index -> character, 'c': 'c' - 'a' = 2
// so the index of array acts as the key of the map

public class MyTrieWithArray {
    TrieNode root;

    public static class TrieNode {
        private static final byte SIZE = 26; // assume words consist only of lowercase English letters
        TrieNode[] children;
        boolean isWord;

        public TrieNode() {
            children = new TrieNode[SIZE];
        }
    }

    public MyTrieWithArray() {
        root = new TrieNode();
    }

    public TrieNode getRoot() {
        return root;
    }

    public boolean insert(String word) {
        if (search(word)) {
            return false;
        }
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            TrieNode next = cur.children[ch - 'a'];
            if (next == null) {
                next = new TrieNode();
                cur.children[ch - 'a'] = next;
            }
            cur = next;
        }
        // in order to maintain the trie's integrity, we should only insert A word and always set isWord = true at the end
        cur.isWord = true;
        return true;
    }

    public boolean search(String word) {
        TrieNode result = searchPrefix(word);
        return result != null && result.isWord;
    }

    public boolean startsWith(String prefix) {
        return searchPrefix(prefix) != null;
    }

    private TrieNode searchPrefix(String prefix) {
        TrieNode cur = root;
        for (char ch : prefix.toCharArray()) {
            if (cur.children[ch - 'a'] == null) {
                return null;
            }
            cur = cur.children[ch - 'a'];
        }
        return cur;
    }

    public List<String> findAllWordsWithPrefix(String prefix) {
        List<String> result = new ArrayList<>();
        TrieNode cur = searchPrefix(prefix);
        if (cur != null) {
            findAllWordsWithPrefix(cur, new StringBuilder(prefix), result);
        }
        return result;
    }

    private void findAllWordsWithPrefix(TrieNode cur, StringBuilder curPath, List<String> result) {
        if (cur.isWord) {
            result.add(curPath.toString());
        }

        for (int i = 0; i < cur.children.length; i++) {
            TrieNode child = cur.children[i];
            if (child != null) {
                findAllWordsWithPrefix(child, curPath.append((char) (i + 'a')), result);
                curPath.deleteCharAt(curPath.length() - 1);
            }
        }
    }
}
