package data_structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://leetcode.com/problems/design-underground-system/
 */

public class DesignUndergroundSystem {
    private Map<String, List<Integer>> map;
    private Map<Integer, checkInPair> records;

    static class checkInPair {
        String startStation;
        int startTime;

        checkInPair(String s, int t) {
            startStation = s;
            startTime = t;
        }
    }

    public DesignUndergroundSystem() {
        map = new HashMap<>();
        records = new HashMap<>();
    }

    public void checkIn(int id, String stationName, int t) {
        records.put(id, new checkInPair(stationName, t));
    }

    public void checkOut(int id, String stationName, int t) {
        checkInPair cur = records.get(id);
        String routeKey = cur.startStation + "->" + stationName;
        Integer sum = t - cur.startTime;
        map.computeIfAbsent(routeKey, k -> new ArrayList<>()).add(sum);
    }

    public double getAverageTime(String startStation, String endStation) {
        String routeKey = startStation + "->" + endStation;
        List<Integer> list = map.getOrDefault(routeKey, new ArrayList<>());
        if (list.isEmpty()) {
            return 0d;
        }
        double sum = 0d;
        for (int d : list) {
            sum += d;
        }
        return sum / list.size();
    }

}
