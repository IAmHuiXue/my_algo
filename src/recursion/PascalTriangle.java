package recursion;

import java.util.ArrayList;
import java.util.List;

import static recursion.PascalTriangleII.getRow;

/** https://leetcode.com/problems/pascals-triangle/ */

public class PascalTriangle {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 1; i <= numRows; i++) {
            res.add(getRow(i - 1));
        }
        return res;
    }
}
