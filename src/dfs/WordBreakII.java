package dfs;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <a href="https://leetcode.com/problems/word-break-ii/">...</a>
 */
public class WordBreakII {
    public List<String> wordBreak(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        List<String> result = new ArrayList<>();
        dfs(result, set, s, new StringBuilder(), 0);
        return result;
    }

    private void dfs(List<String> result, Set<String> set, String word, StringBuilder sb, int index) {
        if (index == word.length()) {
            result.add(sb.deleteCharAt(sb.length() - 1).toString());
            return;
        }
        for (int i = index; i < word.length(); i++) {
            String cur = word.substring(index, i + 1);
            int len = sb.length();
            if (set.contains(cur)) {
                sb.append(cur).append(' ');
                dfs(result, set, word, sb, i + 1); // index = i + 1 is important!
                sb.setLength(len);
            }
        }
    }
}
