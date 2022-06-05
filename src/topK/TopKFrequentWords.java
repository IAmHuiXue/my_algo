package topK;

import java.util.*;

import util.Swap;


/**
 * <a href="https://leetcode.com/problems/top-k-frequent-words/">...</a>
 */

public class TopKFrequentWords {
    /** this is a demonstration to use quickSelect to solve the problem */
    public List<String> topKFrequent(String[] words, int k) {
        Map<String, Integer> map = buildMap(words);
        // map does not have indices, so it is hard to apply algorithm that requires operating positions.
        // therefore, use an int array to relate the index and each key
        // generic array creation!
        Map.Entry<String, Integer>[] array = (Map.Entry<String, Integer>[]) (new Map.Entry[map.size()]);
        int index = 0;
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            array[index++] = entry;
        }
        quickSelect(array, 0, array.length - 1, k);
        Arrays.sort(array, (e1, e2) -> {
            int result = Integer.compare(e2.getValue(), e1.getValue());
            if (result != 0) {
                return result;
            }
            return e1.getKey().compareTo(e2.getKey());
        });

        // trick: need to copy after the sort, not before, to avoid a corner case
        // e.g. the kth element in the result has the same freq with others but the latter has more priority lexicographically
        array = Arrays.copyOf(array, k);
        List<String> res = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : array) {
            res.add(entry.getKey());
        }
        return res;
    }

    private Map<String, Integer> buildMap(String[] words) {
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        return map;
    }

    private void quickSelect(Map.Entry<String, Integer>[] array, int left, int right, int k) {
        if (left >= right) {
            return;
        }
        int pivotIndex = partition(array, left, right);
        if (pivotIndex == k - 1) {
            return;
        }
        if (pivotIndex < k - 1) {
            quickSelect(array, pivotIndex + 1, right, k);
        } else {
            quickSelect(array, left, pivotIndex - 1, k);
        }
    }

    private int partition(Map.Entry<String, Integer>[] array, int left, int right) {
        int pivot = array[right].getValue();
        int l = left;
        int r = right - 1;
        while (l <= r) { // = !
            if (array[l].getValue() > pivot) {
                l++;
            } else if (array[r].getValue() <= pivot) {
                r--;
            } else {
                Swap.swap(array, l++, r--);
            }
        }
        Swap.swap(array, l, right);
        return l;
    }
}
