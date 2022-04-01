package divide_and_conquer;

/**
 https://leetcode.com/problems/longest-common-prefix/
*/

public class LongestCommonPrefix {
    // divide and conquer
  public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }
        return longestCommonPrefix(strs, 0 , strs.length - 1);
    }
    
    private String longestCommonPrefix(String[] strs, int left, int right) {
        if (left == right) {
            return strs[left];
        }
        int mid = left + (right - left) / 2;
        String leftResult = longestCommonPrefix(strs, left, mid);
        String rightResult = longestCommonPrefix(strs, mid + 1, right);
        return longestCommonPrefix(leftResult, rightResult);
    }
    
    private String longestCommonPrefix(String left, String right) {
        int minLen = Math.min(left.length(), right.length());
        int count = 0;
        for (int i = 0; i < minLen; i++) {
            if (left.charAt(i) != right.charAt(i)) {
                break;
            }
            count++;
        }
        return left.substring(0, count);
    }
    /**
    In the worst case we have n equal strings with length m

    Time complexity : O(m * n)

    Space complexity : O(m⋅logn)
    */
}
