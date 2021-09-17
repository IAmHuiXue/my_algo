package string;

/** https://leetcode.com/problems/shifting-letters/ */

public class ShiftingLetters {
    public String shiftingLetters(String s, int[] shifts) {
        char[] array = s.toCharArray();
        long count = 0; // to avoid Integer overflow
        for (int i = s.length() - 1; i >= 0; i--) {
            // https://leetcode.com/problems/shifting-letters/discuss/1451878/C%2B%2BJavaPython-Easy-Solution-(Well-Explained-with-code-for-reference)
            array[i] = (char) (((array[i] - 'a' + (count + shifts[i]) % 26)) % 26 + 'a');
            count += shifts[i];
        }
        return new String(array);
    }
}
