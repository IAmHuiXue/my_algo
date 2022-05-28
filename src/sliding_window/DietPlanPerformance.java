package sliding_window;

/** <a href="https://leetcode.com/problems/diet-plan-performance/">...</a> */

public class DietPlanPerformance {
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int score = 0;
        int sum = 0;
        for (int i = 0; i < calories.length; i++) {
            sum += calories[i];
            if (i < k - 1) {
                continue;
            }
            if (i >= k) {
                sum -= calories[i - k];
            }
            // if i = k - 1, the window just reaches the correct size, so no need to remove the front element yet
            score += verify(sum, lower, upper);
        }
        return score;
    }

    int verify(int sum, int l, int u) {
        return sum < l ? - 1 : sum > u ? 1 : 0;
    }
}
