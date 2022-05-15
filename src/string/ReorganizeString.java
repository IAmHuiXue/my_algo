package string;

public class ReorganizeString {
    public static void main(String[] args) {
        System.out.println(new ReorganizeString().reorganizeString("bfrbs"));
    }

    public String reorganizeString(String s) {

        /*

We construct the resulting string in sequence: at position 0, 2, 4, ... and then 1, 3, 5, ...
In this way, we can make sure there is always a gap between the same characters

Consider this example: "aaabbbcdd", we will construct the string in this way:

a _ a _ a _ _ _ _ // fill in "a" at position 0, 2, 4
a b a _ a _ b _ b // fill in "b" at position 6, 8, 1
a b a c a _ b _ b // fill in "c" at position 3
a b a c a d b d b // fill in "d" at position 5, 7

         */
        int[] hash = new int[26];
        for (int i = 0; i < s.length(); i++) {
            hash[s.charAt(i) - 'a']++;
        }
        int max = 0, letter = 0;
        for (int i = 0; i < hash.length; i++) {
            if (hash[i] > max) {
                max = hash[i];
                letter = i;
            }
        }
        if (max > (s.length() + 1) / 2) {
            return "";
        }
        char[] res = new char[s.length()];
        int idx = 0;
        while (hash[letter] > 0) {
            res[idx] = (char) (letter + 'a');
            idx += 2;
            hash[letter]--;
        }
        for (int i = 0; i < hash.length; i++) {
            while (hash[i] > 0) {
                if (idx >= res.length) {
                    idx = 1;
                }
                res[idx] = (char) (i + 'a');
                idx += 2;
                hash[i]--;
            }
        }
        return String.valueOf(res);
    }
}
