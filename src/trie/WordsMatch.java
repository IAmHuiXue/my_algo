package trie;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import util.Trie;
import util.TrieNode;

/**
 * find all words matching the expression with wildcard '?' in the dictionary
 * '?' can match any single character
 *
 * e.g.: allMatch["ca?"] = ["cap", "cat"]
 * allMatch["??p"] = ["app", "cap"]
 */

// this demonstrates how to use Trie API in practical problems

public class WordsMatch {
    public List<String> findAllMatchWildCardWords(String target, List<String> dict) {
        List<String> result = new ArrayList<>();
        if (target == null || target.length() == 0) {
            return result;
        }
        Trie trie = new Trie(dict.toArray(new String[0]));
        // need to obtain the root node of the built trie
        findAllMatchWildCardWords(trie.getRoot(), target, result, new StringBuilder(), 0);
        return result;
    }

    private void findAllMatchWildCardWords(TrieNode curNode, String target, List<String> result,
                                           StringBuilder curPath, int index) {
        // curPath is the path from root to cur
        if (index == target.length()) {
            // this has to be an end of a word!
            if (curNode.isWord) { // is this same as (curPath.length() == target.length()) ?
                result.add(curPath.toString());
            }
            return;
        }

        char toMatch = target.charAt(index);
        if (toMatch == '?') {
            for (Map.Entry<Character, TrieNode> child : curNode.children.entrySet()) {
                findAllMatchWildCardWords(child.getValue(), target, result,
                        curPath.append(child.getKey()), index + 1);
                curPath.deleteCharAt(curPath.length() - 1);
            }
        } else {
            TrieNode nextLevel = curNode.children.get(toMatch);
            if (nextLevel != null) {
                findAllMatchWildCardWords(nextLevel, target, result, curPath.append(toMatch), index + 1);
                curPath.deleteCharAt(curPath.length() - 1);
            }
        }
    }
}
