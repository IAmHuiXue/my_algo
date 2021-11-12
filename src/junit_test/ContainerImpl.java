package junit_test;

import java.util.*;

/**
 * A container of integers that should support
 * elements addition, removal, and search of the median element
 */
public class ContainerImpl implements Container {
    List<Integer> list;
    public ContainerImpl() {
        list = new ArrayList<>();
    }

    @Override
    public void add(int value) {
        list.add(value);
    }

    @Override
    public boolean delete(int value) {
        int index = list.indexOf(value);
        if (index != -1) {
            list.remove(index);
            return true;
        }
        return false;
    }

    @Override
    public int getMedian() {
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