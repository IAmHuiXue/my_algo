package string;

import util.Swap;

/** <a href="https://leetcode.com/problems/reverse-string-ii/">...</a> */

public class ReverseStringII {
    public String reverseStr(String s, int k) {
        char[] arr = s.toCharArray();
        int i = 0, j = 0;
        while (j < arr.length) {
            while (j < arr.length && j - i < 2 * k) {
                j++;
            }
            Swap.swap(arr, i, Math.min(j - 1, i + k - 1));
            i = j;
        }
        return new String(arr);
    }

    public String reverseStr2(String s, int k) {
        char[] a = s.toCharArray();
        for (int i = 0; i < a.length; i += 2 * k) {
            Swap.swap(a, i, Math.min(a.length - 1, i + k - 1));
        }
        return new String(a);
    }
}
