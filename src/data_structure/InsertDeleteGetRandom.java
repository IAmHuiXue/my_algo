package data_structure;

import java.util.*;

/** https://leetcode.com/problems/insert-delete-getrandom-o1/ */

public class InsertDeleteGetRandom { // all operations O(1)
    // key=element, value=index in the arraylist
    Map<Integer, Integer> map;
    List<Integer> list;
    static final Random R = new Random();

    public InsertDeleteGetRandom() {
        map = new HashMap<>();
        list = new ArrayList<>();
    }

    public boolean insert(int val) {
        if (map.containsKey(val)) {
            return false;
        }
        map.put(val, list.size());
        list.add(val);
        return true;
    }

    public boolean remove(int val) {
        if (!map.containsKey(val)) {
            return false;
        }
        // get the index of the val, move the last element to the index position
        // remove the last element in the list
        // update map
        int index = map.get(val);
        int lastVal = list.get(list.size() - 1);
        list.set(index, lastVal);
        map.put(lastVal, index);
        map.remove(val);
        // put the following code in here is wrong. think about the scenario when val is
        // the lastVal
//        map.put(lastVal, index);
        list.remove(list.size() - 1);
        return true;
    }

    public int getRandom() {
        return list.get(R.nextInt(list.size()));

    }
}
