package string;

/**
 * https://leetcode.com/problems/reconstruct-original-digits-from-english/
 */

public class ReconstructOriginalDigitsFromEnglish {

    /*
    Letter "z" is present only in "zero". 0
    Letter "w" is present only in "two". 2
    Letter "u" is present only in "four". 4
    Letter "x" is present only in "six". 6
    Letter "g" is present only in "eight". 8

    By checking the above, we have 0, 2, 4, 6, 8 isolated

    Letter "h" is present only in "three" and "eight". 3 & 8
    Letter "f" is present only in "five" and "four". 4 & 5
    Letter "s" is present only in "seven" and "six". 6 & 7

    By checking the above, we have 3, 5, 7 isolated

    Letter "i" is present in "nine", "five", "six", and "eight". 9, 5, 6, 8

    By checking the above, we have 9 isolated

    Letter "n" is present in "one", "seven", and "nine". 1, 7, 9

    Lastly by checking the above, we have 1 isolated

     */

    public String originalDigits(String s) {
        // building hashmap letter -> its frequency
        char[] count = new char[26 + (int) 'a'];
        for (char letter : s.toCharArray()) {
            count[letter]++;
        }

        // building hashmap digit -> its frequency
        int[] out = new int[10];
        // letter "z" is present only in "zero"
        out[0] = count['z'];
        // letter "w" is present only in "two"
        out[2] = count['w'];
        // letter "u" is present only in "four"
        out[4] = count['u'];
        // letter "x" is present only in "six"
        out[6] = count['x'];
        // letter "g" is present only in "eight"
        out[8] = count['g'];
        // letter "h" is present only in "three" and "eight"
        out[3] = count['h'] - out[8];
        // letter "f" is present only in "five" and "four"
        out[5] = count['f'] - out[4];
        // letter "s" is present only in "seven" and "six"
        out[7] = count['s'] - out[6];
        // letter "i" is present in "nine", "five", "six", and "eight"
        out[9] = count['i'] - out[5] - out[6] - out[8];
        // letter "n" is present in "one", "nine", and "seven"
        out[1] = count['n'] - out[7] - 2 * out[9];

        // building output string
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < 10; i++)
            for (int j = 0; j < out[i]; j++) {
                output.append(i);
            }

        return output.toString();
    }
}
