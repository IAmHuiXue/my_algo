package recursion;

import java.util.List;

/**
 * https://leetcode.com/problems/nested-list-weight-sum/
 */

public class NestedListWeightSum {
    public int depthSum(List<NestedInteger> nestedList) {
        int[] sum = new int[1];
        cal(nestedList, 1, sum);
        return sum[0];
    }

    private void cal(List<NestedInteger> nestedList, int depth, int[] sum) {
        for (NestedInteger element : nestedList) {
            if (element.isInteger()) {
                sum[0] += element.getInteger() * depth;
            } else {
                cal(element.getList(), depth + 1, sum);
            }
        }
    }

    public int depthSumAnotherWay(List<NestedInteger> nestedList) {
        return depthSumHelper(nestedList, 1);
    }
    private int depthSumHelper(List<NestedInteger> nestedList, int depth) {
        int sum = 0;
        for (NestedInteger n : nestedList) {
            if (n.isInteger()) {
                sum += depth * n.getInteger();
            } else {
                sum += depthSumHelper(n.getList(), depth + 1);
            }
        }
        return sum;
    }
}

// This is the interface that allows for creating nested lists.
// You should not implement it, or speculate about its implementation
interface NestedInteger {
    // @return true if this NestedInteger holds a single integer, rather than a nested list.
    boolean isInteger();

    // @return the single integer that this NestedInteger holds, if it holds a single integer
    // Return null if this NestedInteger holds a nested list
    Integer getInteger();

    // Set this NestedInteger to hold a single integer.
    void setInteger(int value);

    // Set this NestedInteger to hold a nested list and adds a nested integer to it.
    void add(NestedInteger ni);

    // @return the nested list that this NestedInteger holds, if it holds a nested list
    // Return empty list if this NestedInteger holds a single integer
    List<NestedInteger> getList();
}
