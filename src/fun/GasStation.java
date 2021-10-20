package fun;

/** https://leetcode.com/problems/gas-station/ */

public class GasStation {

    // [1, 2, 3, 4, 5]

    // [3, 4, 5, 1, 2]




    public int canCompleteCircuit(int[] gas, int[] cost) {

        int n = gas.length;

        int total_tank = 0;
        int curr_tank = 0;
        int starting_station = 0;
        for (int i = 0; i < n; ++i) {
            total_tank += gas[i] - cost[i];
            curr_tank += gas[i] - cost[i];
            // If one couldn't get here,
            if (curr_tank < 0) {
                // Pick up the next station as the starting one.
                starting_station = i + 1;
                // Start with an empty tank.
                curr_tank = 0;
            }
        }
        return total_tank >= 0 ? starting_station : -1;

        // time O(n)


        // brute force
        // int index = -1;
        // for (int i = 0; i < gas.length; i++) {
        //     index = i;
        //     int amount = gas[i];
        //     for (int k = 0; k < gas.length; k++) {
        //         amount -= cost[index];
        //         if (amount < 0) {
        //             break;
        //         }
        //         index = index + 1 == gas.length ? 0 : index + 1;
        //         amount += gas[index];
        //     }
        //     if (amount >= 0) {
        //         return index;
        //     }
        // }
        // return -1;
    }
}
