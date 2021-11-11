package junit_test;

import java.util.*;

/**
 * A container of integers that should support
 * elements addition, removal, and search of the median element
 */
public class ContainerImpl implements Container {
    List<Integer> list;
    public ContainerImpl() {
        // write your code here
        list = new ArrayList<>();
    }

    @Override
    public void add(int value) {
        // write your code here
        list.add(value);

        // throw new UnsupportedOperationException();
    }

    @Override
    public boolean delete(int value) {
        // write your code here
        int index = list.indexOf(value);
        if (index != -1) {
            list.remove(index);
            return true;
        }
        return false;

        // throw new UnsupportedOperationException();
    }

    @Override
    public int getMedian() {
        // write your code here
        if (list.isEmpty()) {
            throw new UnsupportedOperationException();
        }
        Collections.sort(list);
        int mid = list.size() / 2;
        if (list.size() % 2 == 0) {
            return list.get(mid - 1);
        }
        return list.get(mid);
    }
}