package array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/** https://leetcode.com/problems/maximum-units-on-a-truck/ */

public class MaximumUnitsOnATruck {
    public int maximumUnits(int[][] boxTypes, int truckSize) {
        // [1, 3]
        // [2, 4]
        // [3, 1]

//        Arrays.sort(boxTypes, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] a, int[] b) {
//                return Integer.compare(b[1], a[1]);
//            }
//        });
        Arrays.sort(boxTypes, (a, b) -> b[1] - a[1]);
        int unitCount = 0;
        for (int[] boxType : boxTypes) {
            int boxCount = Math.min(truckSize, boxType[0]);
            unitCount += boxCount * boxType[1];
            truckSize -= boxCount;
            if (truckSize == 0)
                break;
        }
        return unitCount;
    }
    // time: O(nlog(n))

    public int maximumUnitsWithMaxHeap(int[][] boxTypes, int truckSize) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b)->b[1] - a[1]);
        for (int[] boxType : boxTypes) {
            maxHeap.offer(boxType);
        }
        int unitCount = 0;
        while (!maxHeap.isEmpty()) {
            int[] top = maxHeap.poll();
            int boxCount = Math.min(truckSize, top[0]);
            unitCount += boxCount * top[1];
            truckSize -= boxCount;
            if(truckSize == 0)
                break;
        }
        return unitCount;
    }
}
