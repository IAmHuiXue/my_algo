package string;


/** <a href="https://leetcode.com/problems/rotate-string/">...</a> */

public class RotateString {
    public boolean rotateString(String A, String B) {

        // All rotations of A are contained in A+A.
        // Thus, we can simply check whether B is a substring of A+A.
        // We also need to check A.length == B.length, otherwise we will fail cases like A = "a", B = "aa".

        return A.length() == B.length() && (A + A).contains(B);
    }
}
