package trie;

/**
 * https://leetcode.com/problems/design-add-and-search-words-data-structure/
 */

public class DesignAddAndSearchWordsDataStructure {
    static class TrieNode {
        static final int LENGTH = 26;
        boolean isWord;
        TrieNode[] children;

        TrieNode() {
            children = new TrieNode[LENGTH];
        }
    }

    TrieNode root;

    public DesignAddAndSearchWordsDataStructure() {
        root = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode cur = root;
        for (char ch : word.toCharArray()) {
            TrieNode next = cur.children[ch - 'a'];
            if (next == null) {
                next = new TrieNode();
                cur.children[ch - 'a'] = next; // put next into cur.children[ch - 'a']
            }
            cur = next;
        }
        cur.isWord = true;
    }

    public boolean search(String word) {
        return searchHelper(root, word, 0);
    }

    private boolean searchHelper(TrieNode curNode, String word, int index) {
        if (index == word.length()) {
            // because we are to search the exact matched WORD, not prefix, so the node needs to be an end of a word
            return curNode.isWord;
        }

        char curLetter = word.charAt(index);
        if (curLetter == '.') {
            for (int i = 0; i < TrieNode.LENGTH; i++) {
                TrieNode next = curNode.children[i];
                if (next != null && searchHelper(next, word, index + 1)) {
                    return true;
                }
            }
            return false;
        }
        // curLetter != '.'
        TrieNode next = curNode.children[curLetter - 'a'];
        return next != null && searchHelper(next, word, index + 1);
    }
}
