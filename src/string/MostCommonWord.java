package string;

import java.util.*;

/** <a href="https://leetcode.com/problems/most-common-word/">...</a> */

public class MostCommonWord {
    public String mostCommonWordOnePass(String paragraph, String[] banned) {
        Set<String> bannedWords = new HashSet<>(Arrays.asList(banned));
        String ans = "";
        int maxCount = 0;
        Map<String, Integer> wordCount = new HashMap<>();
        StringBuilder wordBuffer = new StringBuilder();
        char[] chars = paragraph.toCharArray();
        for (int p = 0; p < chars.length; ++p) {
            char currChar = chars[p];
            if (Character.isLetter(currChar)) {
                wordBuffer.append(Character.toLowerCase(currChar));
                if (p != chars.length - 1) {
                    continue;
                }
            }
            // 2). at the end of one word or at the end of paragraph
            if (wordBuffer.length() > 0) {
                String word = wordBuffer.toString();
                // identify the maximum count while updating the wordCount table.
                if (!bannedWords.contains(word)) {
                    int newCount = wordCount.getOrDefault(word, 0) + 1;
                    wordCount.put(word, newCount);
                    if (newCount > maxCount) {
                        ans = word;
                        maxCount = newCount;
                    }
                }
                wordBuffer.setLength(0);
            }
        }
        return ans;
    }

    public String mostCommonWordOnePassMultiplePasses(String paragraph, String[] banned) {
        // todo: to cover the edge case like "a, a, a, a, b,b,b,c, c"
        Map<String, Integer> map = new HashMap<>();
        int mostFreq = 0;
        String res = "";
        Set<String> set = new HashSet<>(Arrays.asList(banned));
        StringBuilder sb = new StringBuilder();
        for (char c : paragraph.toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(Character.toLowerCase(c));
                continue;
            }
            if (c == ' ') {
                sb.append(c);
            }
        }
        String[] chunks = sb.toString().split(" ");
        for (String word : chunks) {
            if (set.contains(word)) {
                continue;
            }
            int newCount = map.getOrDefault(word, 0) + 1;
            map.put(word, newCount);
            if (newCount > mostFreq) {
                res = word;
                mostFreq = newCount;
            }
        }
        return res;
    }
}
