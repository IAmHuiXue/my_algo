package trie;

import util.Trie;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * <a href="https://leetcode.com/problems/search-suggestions-system/">...</a>
 */

public class SearchSuggestionsSystem {
    static class SortWithTwoPointers {

        // sort + 2 pointers

        public List<List<String>> suggestedProducts(String[] products, String searchWord) {
            List<List<String>> result = new ArrayList<>(searchWord.length());
            Arrays.sort(products);
            int left = 0;
            int right = products.length - 1;
            int len = searchWord.length();

            for (int i = 0; i < len; i++) {
                while (left <= right
                        && (products[left].length() < i + 1 || products[left].charAt(i) != searchWord.charAt(i))) {
                    left++;
                }
                while (left <= right
                        && (products[right].length() < i + 1 || products[right].charAt(i) != searchWord.charAt(i))) {
                    right--;
                }
                // shorten the range
                int end = Math.min(left + 2, right);
                List<String> list = new ArrayList<>();
                for (int j = left; j <= end; j++) {
                    list.add(products[j]);
                }
                result.add(list);
            }
            return result;
        }
        // time: O(n(log(n) for sort + n for 2 pointers' move) if we ignore the length of words
    }

    static class WithTrie {
        public List<List<String>> suggestedProducts(String[] products, String searchWord) {
            Trie trie = new Trie(products);
            List<List<String>> result = new ArrayList<>(searchWord.length());
            StringBuilder prefix = new StringBuilder();
            for (char c : searchWord.toCharArray()) {
                prefix.append(c);
                List<String> res = trie.findAllWordsWithPrefix(prefix.toString());
                Collections.sort(res);
                result.add(res.subList(0, Math.min(res.size(), 3)));
            }
            return result;
        }
    }

    // TODO
    static class SortWithBS {

    }


}
