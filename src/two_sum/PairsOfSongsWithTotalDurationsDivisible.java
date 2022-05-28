package two_sum;

import java.util.HashMap;
import java.util.Map;

/** <a href="https://leetcode.com/problems/pairs-of-songs-with-total-durations-divisible-by-60/">...</a> */

public class PairsOfSongsWithTotalDurationsDivisible {

    /*
        (a + b) % 60 = 0
                |
         ((a % 60) + (b % 60)) % 60 = 0
                |
         either, a % 60 = 0 && b % 60 = 0,
         or,    (a % 60) + (b % 60) = 60

     */

    public int numPairsDivisibleBy60(int[] time) {
        // <key=element%60, value=freq> !
        Map<Integer, Integer> map = new HashMap<>();
        int count = 0;
        for (int song : time) {
            int mod = song % 60;
            // if mod = 0, can make the pairs with all other elements whose mod is 0
            int num = map.getOrDefault(mod, 0);
            if (mod == 0) {
                count += num;
            } else {
                // otherwise, can make the pairs with all other elements whose mod is 60 - mod
                count += map.getOrDefault(60 - mod, 0);
            }
            map.put(mod, num + 1); // !
        }
        return count;
    }
}
