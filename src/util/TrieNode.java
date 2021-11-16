package util;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {
    public Map<Character, TrieNode> children;
    public boolean isWord;

    public TrieNode() {
        children = new HashMap<>();
    }
}

//class TrieNode {
//    public TrieNode[] children;
//    public boolean isWord;
//    static final int LEN = 26;
//
//    public TrieNode() {
//        children = new TrieNode[LEN];
//    }
//}
