package for_fun;

/**
 * https://leetcode.com/problems/find-the-celebrity/
 */

public class FindTheCelebrity {
    private int numberOfPeople;

    /* The knows API is defined.
      boolean knows(int a, int b); */
    boolean knows(int a, int b) {
    return false;
    }

    // if a knows b -> a is not a celeb
    // if a does not know b -> b is not a celeb
    // every time we invoke a knows() call, we can rule out a candidate
    //
    public int findCelebrity(int n) {
        numberOfPeople = n;
        int celebrityCandidate = 0;
        for (int i = 0; i < n; i++) {
            if (celebrityCandidate == i) { // don't ask if they know themselves
                continue;
            }
            if (knows(celebrityCandidate, i)) { // celebrityCandidate is ruled out
                celebrityCandidate = i;
            }
        }
        // now celebrityCandidate is not ruled out, but we need to further check if he is the one
        if (isCelebrity(celebrityCandidate)) {
            return celebrityCandidate;
        }
        return -1;
    }

    private boolean isCelebrity(int i) {
        for (int j = 0; j < numberOfPeople; j++) {
            if (i == j) continue; // don't ask if they know themselves
            if (knows(i, j) || !knows(j, i)) {
                return false;
            }
        }
        return true;
    }
    // time: O(n)
}
