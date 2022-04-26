package trick;

import java.util.ArrayList;
import java.util.List;

/** https://leetcode.com/problems/sequential-digits/ */

public class SequentialDigits {
    public List<Integer> sequentialDigits(int low, int high) {
        // All integers that have sequential digits are substrings of string "123456789".
        String sample = "123456789";
        List<Integer> nums = new ArrayList<>();
        int lowLen = String.valueOf(low).length();
        int highLen = String.valueOf(high).length();
        for (int length = lowLen; length <= highLen; length++) {
            for (int start = 0; start <= sample.length() - length; start++) {
                int num = Integer.parseInt(sample.substring(start, start + length));
                if (num >= low && num <= high) nums.add(num);
            }
        }
        return nums;
    }
}
