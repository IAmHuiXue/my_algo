package greedy;

import java.util.Arrays;

/** https://leetcode.com/problems/boats-to-save-people/ */

public class BoatsToSavePeople {

    /*
If the heaviest person can share a boat with the lightest person, then do so. Otherwise, the heaviest person can't pair
with anyone, so they get their own boat.

The reason this works is that if the lightest person can pair with anyone, they might as well pair with the heaviest person.
     */

    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        int i = 0, j = people.length - 1;
        int res = 0;
        while (i <= j) {
            int sum = people[i] + people[j];
            res++;
            j--;
            if (sum <= limit) {
                i++;
            }
        }
        return res;
    }
}
