package recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/** https://leetcode.com/problems/pascals-triangle-ii/ */

public class PascalTriangleII {
    public static List<Integer> getRow(int rowIndex) {
        if (rowIndex == 0) {
            return new ArrayList<>(Arrays.asList(1));
        }
        List<Integer> list = getRow(rowIndex - 1);
        List<Integer> result = new ArrayList<>();
        result.add(1);
        for (int i = 0; i < list.size() - 1; i++) {
            result.add(list.get(i) + list.get(i + 1));
        }
        result.add(1);
        return result;
    }

}
