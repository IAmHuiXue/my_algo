package string;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/** <a href="https://leetcode.com/problems/goat-latin">...</a> */

public class GoatLatin {
    static class WithStringBuilder {
        public String toGoatLatin(String sentence) {
            Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U'));
            StringBuilder sb = new StringBuilder();
            String[] words = sentence.split("\\s");
            int count = 0;
            for (String word : words) {
                if (set.contains(word.charAt(0))) {
                    sb.append(word);
                } else {
                    sb.append(word.substring(1)).append(word.charAt(0));
                }
                sb.append("ma");
                count++;
                for (int i = 1; i <= count; i++) {
                    sb.append('a');
                }
                sb.append(' ');
            }
            return sb.deleteCharAt(sb.length() - 1).toString();
        }
    }

    static class TwoPointers {
        public String toGoatLatin(String sentence) {
            // 1. traverse the string from left to right
            // 1.1 get the count of individual words
            // 1.2 replace the vowel
            // TODO: the while loop cannot handle the case where the word is a single letter
            char[] array = sentence.toCharArray();
            int count = 0;
            int i = 0, j = 0;
            int start = -1;
            boolean toAppend = false;
            while (j < array.length) {
                // to determine start of a word
                if (array[j] != ' ' && (i == 0 || array[i - 1] == ' ')) {
                    count++;
                    start = j;
                    if (isVowel(array[start])) {
                        toAppend = true;
                        j++;
                    }
                }
                if (toAppend && array[j] != ' ' && (j == array.length - 1 || array[j + 1] == ' ')) {
                    // to determine end of a word
                    array[i++] = array[j++];
                    array[i++] = array[start];
                    toAppend = false;
                } else {
                    array[i++] = array[j++];
                }
            }
            // 2. create a new char[] with fina length and traverse
            // from right to left to finish adding ma & a
            return null;
        }

        private boolean isVowel(char ch) {
            char c = Character.toLowerCase(ch);
            return c == 'a' || c == 'e' || c == 'i'
                    || c == 'o' || c == 'u';
        }
    }
}
