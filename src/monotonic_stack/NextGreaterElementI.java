package monotonic_stack;

import java.util.HashMap;
import java.util.Map;

/** https://leetcode.com/problems/next-greater-element-i/ */

public class NextGreaterElementI {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        // key=elementValueOfNums2, value=indexOfNums2
        // the purpose of the map is to build an indexing from nums1[i] to the corresponding index in nums2
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums2.length; i++) {
            map.put(nums2[i], i);
        }
        int[] dict = NextGreaterElementZero.nextGreaterElement(nums2);
        int[] result = new int[nums1.length];
        for (int j = 0; j < nums1.length; j++) {
            result[j] = dict[map.get(nums1[j])];
        }
        return result;
    }
}
