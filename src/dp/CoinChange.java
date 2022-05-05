package dp;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/coin-change/
 */

public class CoinChange {
    public int coinChangeBottomUp(int[] coins, int amount) {
        // dp[i] represents for amount = i, the min num of coins needed
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;
        for (int curAmount = 1; curAmount < dp.length; curAmount++) {
            for (int coin : coins) {
                if (coin <= curAmount) {
                    dp[curAmount] = Math.min(dp[curAmount], dp[curAmount - coin] + 1);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }

    public int coinChangeTopDown(int[] coins, int amount) {
        if (amount < 1) return 0;
        return helper(coins, amount, new int[amount + 1]);
    }

    private int helper(int[] coins, int rem, int[] count) { // rem: remaining coins after the last step; count[rem]: minimum number of coins to sum up to rem
        if (rem < 0) return -1; // not valid
        if (rem == 0) return 0; // completed
        if (count[rem] != 0) return count[rem]; // already computed, so reuse
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = helper(coins, rem - coin, count);
            if (res >= 0 && res < min)
                min = 1 + res;
        }
        count[rem] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem];
    }

}
