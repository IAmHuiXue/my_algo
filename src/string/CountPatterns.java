package string;

/*
You are given two strings pattern and s. The first string pattern contains only the symbols 0 and 1, and the second string s contains only lowercase English letters.

Let's say that pattern matches a substring s[l..r] of s if the following 3 conditions are met:

they have equal length;
for each 0 in pattern the corresponding letter in the substring is a vowel;
for each 1 in pattern the corresponding letter is a consonant.
Your task is to calculate the number of substrings of s that match pattern.

Note: In this task we define the vowels as 'a', 'e', 'i', 'o', 'u', and 'y'. All other letters are consonants.

Example

For pattern = "010" and s = "amazing", the output should be binaryPatternMatching(pattern, s) = 2.
Expand to see the example video.

"010" matches s[0..2] = "ama", because both 0s match a, which is a vowel, and 1 match m, which is a consonant;
"010" doesn't match s[1..3] = "maz", because the first 0 corresponds to an m, which is a consonant, not a vowel;
"010" matches s[2..4] = "azi", because the first 0 matches an a (vowel), 1 matches z (consonant), and the second 0 matches i (vowel);
"010" doesn't match s[3..5] = "zin", because the first 0 corresponds to a consonant (z);
"010" doesn't match s[4..6] = "ing", because the second 0 corresponds to the letter g, which is a consonant.
So, there are only 2 matches.

For pattern = "100" and s = "codesignal", the output should be binaryPatternMatching(pattern, s) = 0.

There are no double vowels in the string "codesignal", so it's not possible for any of its substrings to match this pattern.

Input/Output

[execution time limit] 3 seconds (java)

[input] string pattern

A string of 0s and 1s.

Guaranteed constraints:
1 ≤ pattern.length ≤ 103.

[input] string s

A string of lowercase English letters.

Guaranteed constraints:
1 ≤ s.length ≤ 103.

[output] integer

The number of substrings of s that match pattern.
 */

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class CountPatterns {
    Set<Character> set = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u', 'y'));
    int res;
    int binaryPatternMatching(String pattern, String s) {
        if (pattern.length() > s.length()) {
            return res;
        }
        for (int i = 0; i <= s.length() - pattern.length(); i++) {
            helper(i, s, pattern);
        }
        return res;
    }

    void helper(int index, String s, String pattern) {
        for (int i = 0; i < pattern.length(); i++) {
            char cur = s.charAt(i + index);
            char p = pattern.charAt(i);
            if (p == '0' && !set.contains(cur) || p == '1' && set.contains(cur)) {
                return;
            }
        }
        res++;
    }

    // todo: 优化方法
}
