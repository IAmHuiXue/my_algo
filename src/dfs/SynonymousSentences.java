package dfs;

import java.util.*;

/** https://leetcode.com/problems/synonymous-sentences/ */

// dfs within dfs

public class SynonymousSentences {
    public List<String> generateSentences(List<List<String>> synonyms, String text) {
        String[] words = text.split(" ");
        Map<String, List<String>> graph = new HashMap<>();
        for (List<String> pair : synonyms) {
            graph.computeIfAbsent(pair.get(0), k -> new ArrayList<>()).add(pair.get(1));
            graph.computeIfAbsent(pair.get(1), k -> new ArrayList<>()).add(pair.get(0));
        }
        List<String> result = new ArrayList<>();
        dfs(result, new StringBuilder(), words, 0, graph);
        return result;
    }

    private void dfs(List<String> result, StringBuilder sb, String[] words, int index, Map<String, List<String>> graph) {
        if (index == words.length) {
            result.add(sb.deleteCharAt(sb.length() - 1).toString());
            return;
        }
        String word = words[index];
        if (graph.containsKey(word)) {
            int len = sb.length();
            List<String> synonyms = new ArrayList<>();
            // 通过 在 graph 里 dfs 来找到所有同义词 neighbors
            findAllSynonyms(synonyms, graph, new HashSet<>(), word);
            // in order to get a lexicographically ordered result
            Collections.sort(synonyms);
            for (String nei : synonyms) {
                sb.append(nei).append(' ');
                dfs(result, sb, words, index + 1, graph);
                sb.setLength(len);
            }
        } else {
            sb.append(word).append(' ');
            dfs(result, sb, words, index + 1, graph);
        }
    }

    private void findAllSynonyms(List<String> synonyms, Map<String, List<String>> graph, Set<String> visited, String word) {
        if (visited.add(word)) {
            synonyms.add(word);
            for (String nei : graph.get(word)) {
                findAllSynonyms(synonyms, graph, visited, nei);
            }
        }
    }
}
