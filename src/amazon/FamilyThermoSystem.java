package amazon;

import java.util.HashMap;
import java.util.Map;

public class FamilyThermoSystem {
    // input temp for each room
    // cal the avr temp, mode, max and min

    // assume room temp is always >= 0
    Map<Double, Integer> map = new HashMap<>();
    double curMax = -1.0;
    double curMin = -1.0;
    double curMode = -1.0;
    double curSum = 0.0;
    int curNum = 0;

    void addTemp(double temp) {
        int curCount = map.getOrDefault(temp, 0);
        curCount++;
        map.put(temp, curCount);
        curSum += temp;
        curNum++;
        int curMaxCount = map.getOrDefault(curMode, 0);
        curMode = curMaxCount > curCount ? curMode : temp;
        curMax = Math.max(curCount, curMax);
        curMin = Math.min(curCount, curMin);
    }

    double avrTemp() {
        return curNum == 0 ? 0 : curSum / curNum;
    }

    double maxTemp() {
        return curMax;
    }

    double minTemp() {
        return curMin;
    }

    double modeTemp() {
        return curMode;
    }

}
