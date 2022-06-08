package trick;

/** <a href="https://leetcode.com/problems/count-binary-substrings/">...</a> */

public class CountBinarySubstrings {
    public int countBinarySubstrings(String s) {
        /*
    First, count the number of 1 or 0 grouped consecutively.
    For example "0110001111" will be [1, 2, 3, 4].

    Second, for any possible substrings with 1 and 0 grouped consecutively, the number of valid substring will be the
    minimum number of 0 and 1.
    For example "0001111", will be min(3, 4) = 3, ("01", "0011", "000111")
         */

        int cur = 1, pre = 0, res = 0;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == s.charAt(i - 1)) cur++;
            else {
                res += Math.min(cur, pre);
                pre = cur;
                cur = 1;
            }
        }
        return res + Math.min(cur, pre);
    }
    // Time O(N)
    // Space O(1)
}
