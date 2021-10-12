//package trie;
//
//import java.util.*;
//
///**
// * https://leetcode.com/problems/design-search-autocomplete-system/
// */
//
//public class DesignSearchAutocompleteSystem {
//    private StringBuilder sb;
//    private TrieNode root;
//
//    static class TrieNode {
//        Map<Character, TrieNode> children;
//        Map<String, Integer> counts;
//        boolean isSentence;
//        int times;
//
//        TrieNode() {
//            children = new HashMap<>();
//            counts = new HashMap<>();
//        }
//    }
//
//    public DesignSearchAutocompleteSystem(String[] sentences, int[] times) {
//        root = new TrieNode();
//        sb = new StringBuilder();
//        for (int i = 0; i < sentences.length; i++) {
//            addSen(sentences[i], times[i]);
//        }
//    }
//
//    private void addSen(String word) {
//        addSen(word, 1);
//    }
//
//    private void addSen(String word, int time) {
//        TrieNode cur = root;
//        for (char ch : word.toCharArray()) {
//            cur.children.putIfAbsent(ch, new TrieNode());
//            cur = cur.children.get(ch);
//        }
//        cur.isSentence = true;
//        cur.times = time;
//    }
//
//    private TrieNode searchPrefix() {
//        TrieNode cur = root;
//        for (char ch : sb.toString().toCharArray()) {
//            if (cur.children.get(ch) == null) {
//                return null;
//            }
//            cur = cur.children.get(ch);
//        }
//        return cur;
//    }
//
//    private List<String> findTopSentencesWithPrefix() {
//        List<String> result = new ArrayList<>();
//        TrieNode cur = searchPrefix();
//        if (cur != null) {
//            findAllSentencesWithPrefix(root, new StringBuilder(prefix), result);
//        }
//        result.sort((a, b) -> {
//            @Override
//
//        });
//        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> (a.getValue() == b.getValue() ?
//                a.getKey().compareTo(b.getKey()) : Integer.compare(b.getValue(), a.getValue())));
//        pq.addAll(cur.counts.entrySet());
//        List<String> res = new ArrayList<>();
//        int k = 3;
//        while (!pq.isEmpty() && k > 0) {
//            res.add((String) pq.poll().getKey());
//            k--;
//        }
//        return result;
//    }
//
//    private void findAllWordsWithPrefix(TrieNode cur, StringBuilder curPath, List<String> result) {
//        if (cur.isSentence) {
//            if (result.size() == 3) {
//
//            }
//            result.add(curPath.toString());
//        }
//
//        for (Map.Entry<Character, TrieNode> child : cur.children.entrySet()) {
//            findAllWordsWithPrefix(child.getValue(), curPath.append(child.getKey()), result);
//            curPath.deleteCharAt(curPath.length() - 1);
//        }
//    }
//
//
//    public List<String> input(char c) {
//        if (c == '#') {
//            addSen(sb.toString());
//            sb.setLength(0);
//            return new ArrayList<>();
//        }
//        sb.append(c);
//        return findTopSentencesWithPrefix();
//    }
//}
