package recursion;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * https://leetcode.com/problems/different-ways-to-add-parentheses/
 */

public class DifferentWaysToAddParentheses {
    public List<Integer> diffWaysToCompute(String input) {
        return diffWaysToCompute(input, 0, input.length() - 1);
    }

    private List<Integer> diffWaysToCompute(String input, int l, int r) {
        List<Integer> res = new ArrayList<>();
        for (int i = l; i <= r; i++) {
            char cur = input.charAt(i);
            if (cur == '-' || cur == '*' || cur == '+') {
                List<Integer> part1Res = diffWaysToCompute(input, l, i - 1);
                List<Integer> part2Res = diffWaysToCompute(input, i + 1, r);
                for (Integer p1 : part1Res) {
                    for (Integer p2 : part2Res) {
                        int c = 0;
                        switch (cur) {
                            case '+':
                                c = p1 + p2;
                                break;
                            case '-':
                                c = p1 - p2;
                                break;
                            case '*':
                                c = p1 * p2;
                                break;
                        }
                        res.add(c);
                    }
                }
            }
        }
        // in this level of recursive call, there is no operator, only one digit,
        // so put the digit into the cur Res
        if (res.size() == 0) {
            res.add(Integer.valueOf(input.substring(l, r + 1)));
        }
        return res;
    }
}
