package util;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    public Map<Character, TrieNode> children;
    public boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
    }

    /*

    public TrieNode[] children;
    public boolean isWord;
    static final int LENG = 26;

    public TrieNode() {
        children = new TrieNode[LENG];

     */
}
