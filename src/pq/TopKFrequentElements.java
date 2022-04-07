package pq;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {
    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Map.Entry<Integer, Integer>> minHeap = new PriorityQueue<>((e1, e2) -> Integer.compare(e1.getValue(), e2.getValue()));
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (minHeap.size() < k) {
                minHeap.offer(entry);
            } else {
                if (minHeap.peek().getValue() < entry.getValue()) {
                    minHeap.poll();
                    minHeap.offer(entry);
                }
            }
        }
        int[] res = new int[minHeap.size()];
        for (int i = res.length - 1; i >= 0; i--) {
            res[i] = minHeap.poll().getKey();
        }
        return res;
    }
    // O(nlog(k))

    // if we maintain a maxHeap and store all unique num, time -> O(nlogn)
}
