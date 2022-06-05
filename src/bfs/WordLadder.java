package bfs;

import java.util.*;

/**
 * <a href="https://leetcode.com/problems/word-ladder/">...</a>
 */

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord)) {
            return 0;
        }
        set.add(beginWord);

        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        set.remove(beginWord);
        int step = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.equals(endWord)) {
                    return step;
                }
                StringBuilder sb = new StringBuilder(cur);
                for (int j = 0; j < cur.length(); j++) {
                    char org = cur.charAt(j);
                    for (char letter = 'a'; letter <= 'z'; letter++) {
                        if (letter == org) {
                            continue;
                        }
                        sb.setCharAt(j, letter);
                        String newWord = sb.toString();
                        if (set.remove(newWord)) {
                            q.offer(newWord);
                        }
                    }
                    sb.setCharAt(j, org);
                }
            }
            step++;
        }
        return 0;
    }

    public int ladderLength2(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }

        Set<String> dict = new HashSet<>(wordList);

        Map<String, Set<String>> graph = buildGraph(dict);

        Queue<String> q = new ArrayDeque<>();
        int steps = 1;
        q.offer(beginWord);
        dict.remove(beginWord);
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.equals(endWord)) {
                    return steps;
                }
                for (String nei : graph.get(cur)) {
                    if (dict.remove(nei)) {
                        q.offer(nei);
                    }
                }
            }
            steps++;
        }
        return 0;
    }

    private Map<String, Set<String>> buildGraph(Set<String> words) {
        Map<String, Set<String>> map = new HashMap<>();
        for (String word : words) {
            StringBuilder sb = new StringBuilder(word);
            for (int i = 0; i < word.length(); i++) {
                char cur = word.charAt(i);
                for (char ch = 'a'; ch <= 'z'; ch++) {
                    if (ch == cur) {
                        continue;
                    }
                    sb.setCharAt(i, ch);
                    if (words.contains(sb.toString())) {
                        map.computeIfAbsent(word, k -> new HashSet<>()).add(sb.toString());
                    }
                    sb.setCharAt(i, cur);
                }
            }
        }
        return map;
    }

}
