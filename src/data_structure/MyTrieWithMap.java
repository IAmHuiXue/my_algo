package data_structure;

// Trie or prefix tree is a tree data structure, which is used for retrieval of a key in a dataset of strings.
// Its nodes have the following fields:

// 1. boolean field which specifies whether the node corresponds to the end of the key, or is just a key prefix.
// 2. Maximum of R links to its children, where each link corresponds to one of R character values
// from dataset alphabet. In this article we assume that R is 26, the number of lowercase latin letters.
// there are two ways to represent this --> HashMap or TrieNode[]
// why does an array work?
// an array of size 26, each index -> character, 'c': 'c' - 'a' = 2
// so the index of array acts as the key of the map

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTrieWithMap {
    private TrieNode root;

    static class TrieNode {
        Map<Character, TrieNode> children;
        boolean isWord;

        public TrieNode() {
            children = new HashMap<>();
        }
    }

    public MyTrieWithMap() {
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
            cur.children.putIfAbsent(ch, new TrieNode());
            cur = cur.children.get(ch);
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
            if (cur.children.get(ch) == null) {
                return null;
            }
            cur = cur.children.get(ch);
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

        for (Map.Entry<Character, TrieNode> child : cur.children.entrySet()) {
            findAllWordsWithPrefix(child.getValue(), curPath.append(child.getKey()), result);
            curPath.deleteCharAt(curPath.length() - 1);
        }
    }
}


