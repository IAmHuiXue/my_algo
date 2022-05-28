package trie;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/design-search-autocomplete-system/">...</a>
 */

public class DesignSearchAutocompleteSystem {
    TrieNode root;
    StringBuilder prefix;

    static class TrieNode {
        boolean isSentence;
        int times;
        Map<Character, TrieNode> children = new HashMap<>();
    }

    static class Pair {
        String sentence;
        int times;

        Pair(String s, int t) {
            sentence = s;
            times = t;
        }
    }

    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
        prefix = new StringBuilder();
        root = new TrieNode();
        for (int i = 0; i < sentences.length; i++) {
            String s = sentences[i];
            int time = times[i];
            addSentence(s, time);
        }
    }

    void addSentence(String sentence, int time) {
        TrieNode cur = root;
        for (char c : sentence.toCharArray()) {
            cur = cur.children.computeIfAbsent(c, k -> new TrieNode());
        }
        cur.isSentence = true;
        cur.times += time; // ! if the sentence was previously added, when adding it again, we just append the time.
    }

    public List<String> input(char c) {
        List<String> res = new ArrayList<>();
        if (c == '#') {
            addSentence(prefix.toString(), 1);
            prefix.setLength(0);
            return res;
        }
        prefix.append(c);
        List<Pair> tmp = findAllSentences(prefix.toString());
        tmp.sort((a, b) -> {
            int diff = b.times - a.times;
            if (diff == 0) {
                return a.sentence.compareTo(b.sentence);
            }
            return diff;
        });
        for (Pair p : tmp) {
            if (res.size() == 3) {
                break;
            }
            res.add(p.sentence);
        }
        return res;
    }

    private List<Pair> findAllSentences(String s) {
        List<Pair> res = new ArrayList<>();
        TrieNode cur = findPrefix(s);
        if (cur != null) {
            dfs(res, cur, new StringBuilder(s));
        }
        return res;
    }

    private TrieNode findPrefix(String s) {
        TrieNode cur = root;
        for (char c : s.toCharArray()) {
            cur = cur.children.get(c);
            if (cur == null) {
                break;
            }
        }
        return cur;
    }

    private void dfs(List<Pair> res, TrieNode cur, StringBuilder sb) {
        if (cur.isSentence) {
            res.add(new Pair(sb.toString(), cur.times));
        }
        for (char c : cur.children.keySet()) {
            sb.append(c);
            dfs(res, cur.children.get(c), sb);
            sb.deleteCharAt(sb.length() - 1);
        }
    }

}
