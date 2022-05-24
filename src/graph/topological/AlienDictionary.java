package graph.topological;

import java.util.*;

/** <a href="https://leetcode.com/problems/alien-dictionary/">https://leetcode.com/problems/alien-dictionary/</a> */

public class AlienDictionary {
    public String alienOrder(String[] words) {
        Map<Character, List<Character>> graph = new HashMap<>();
        Map<Character, Integer> incomingEdges = new HashMap<>();
        if (!build(graph, incomingEdges, words)) {
            return "";
        }
        return topologicalSort(graph, incomingEdges);
    }
    boolean build(Map<Character, List<Character>> graph, Map<Character, Integer> incomingEdges, String[] words) {
        // 1. we do this so that we can use graph.size() or incomingEdges.size() to represent the total num of all the characters
        // otherwise, since this is a directed graph, not putting every char seen explicitly will possibly miss chars.
        for (String word : words) {
            for (char c : word.toCharArray()) {
                incomingEdges.putIfAbsent(c, 0);
                graph.putIfAbsent(c, new ArrayList<>());
            }
        }
        for (int i = 0; i < words.length - 1; i++) {
            String word1 = words[i];
            String word2 = words[i + 1];
            if (word1.length() > word2.length() && word1.startsWith(word2)) { // !
                // indexOf() is wrong
                // startsWith(prefix) is the right method
                return false;
            }
            char[] charset1 = word1.toCharArray();
            char[] charset2 = word2.toCharArray();
            for (int j = 0; j < Math.min(charset1.length, charset2.length); j++) {
                char c1 = charset1[j];
                char c2 = charset2[j];
                if (c1 != c2) {
                    graph.get(c1).add(c2);
                    incomingEdges.put(c2, incomingEdges.get(c2) + 1);
                    break; // !
                }
            }
        }
        return true;
    }

    String topologicalSort(Map<Character, List<Character>> graph, Map<Character, Integer> incomingEdges) {
        Queue<Character> q = new ArrayDeque<>();
        for (char c : graph.keySet()) {
            if (incomingEdges.get(c) == 0) {
                q.offer(c);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            char cur = q.poll();
            sb.append(cur);
            for (char nei : graph.get(cur)) {
                int num = incomingEdges.get(nei);
                num--;
                incomingEdges.put(nei, num);
                // why do we not need to use Set to de-dup?
                // if a char is previously visited, meaning its incomingEdges = 0, and if we are revisiting it now,
                // its incomingEdges will be negative, so it will not be offered into the q.
                if (num == 0) {
                    q.offer(nei);
                }
            }
        }
        return sb.length() == graph.size() ? sb.toString() : "";
    }
}
