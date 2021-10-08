package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Trie {
    private TrieNode root;

    public Trie() {
        this(null);
    }

    public Trie(String[] words) {
        root = new TrieNode();
        // build trie -> O(l * k) where l is the avr length of each word, k is num of words
        if (words != null) {
            for (String word : words) {
                insert(word);
            }
        }
    }

    public TrieNode getRoot() {
        return root;
    }

    public boolean insert(String word) { // O(l)
        if (search(word)) {
            return false;
        }
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            cur.children.putIfAbsent(ch, new TrieNode());
            cur = cur.children.get(ch);
        }
        cur.isWord = true;
        return true;
    }

    public boolean search(String word) { // O(l)
        TrieNode result = searchPrefix(word);
        return result != null && result.isWord;
    }

    public boolean startsWith(String prefix) { // O(l)
        return searchPrefix(prefix) != null;
    }

    private TrieNode searchPrefix(String prefix) { // O(l)
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

    public static void main(String[] args) {
        Trie test = new Trie();
    }
}
