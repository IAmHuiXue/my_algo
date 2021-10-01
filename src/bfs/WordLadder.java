package bfs;

import java.util.*;

/** https://leetcode.com/problems/word-ladder/ */

public class WordLadder {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return 0;
        }
        if (!wordList.contains(beginWord)) {
            wordList.add(beginWord);
        }
        Set<String> set = new HashSet<>(wordList);
        Queue<String> q = new ArrayDeque<>();
        q.offer(beginWord);
        int step = 1;
        int len = beginWord.length();
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String cur = q.poll();
                if (cur.equals(endWord)) {
                    return step;
                }
                StringBuilder sb = new StringBuilder(cur);
                for (int j = 0; j < len; j++) {
                    char org = cur.charAt(j);
                    for (char letter = 'a'; letter <= 'z'; letter++) {
                        if (letter != org) {
                            sb.setCharAt(j, letter);
                            String newWord = sb.toString();
                            if (set.remove(newWord)) {
                                q.offer(newWord);
                            }
                        }
                    }
                    sb.setCharAt(j, org);
                }
            }
            step++;
        }
        return 0;
    }
}
