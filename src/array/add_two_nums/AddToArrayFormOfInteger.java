package array.add_two_nums;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** https://leetcode.com/problems/add-to-array-form-of-integer/ */

public class AddToArrayFormOfInteger {
    // k could be 10, 100, 1000,
    // so we need to make sure even if index reaches the start of num[]
    // we still fill carry % 10 && carry /= 10 until carry is 0
    public List<Integer> addToArrayForm(int[] num, int k) {
        int carry = k;
        List<Integer> result = new ArrayList<>();
        int index = num.length - 1;
        while (index >= 0 || carry != 0) {
            if (index >= 0) {
                carry += num[index];
                index--;
            }
            result.add(carry % 10);
            carry /= 10;
        }
        Collections.reverse(result);
        return result;
    }
}
