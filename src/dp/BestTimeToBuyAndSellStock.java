package dp;

/** https://leetcode.com/problems/best-time-to-buy-and-sell-stock/ */

public class BestTimeToBuyAndSellStock {
    public int maxProfit(int[] prices) {
        int[] dp = new int[prices.length];
        // dp[i] represents the lowest price from 0 to i
        int res = 0;
        dp[0] = prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i] = Math.min(dp[i - 1], prices[i]);
            res = Math.max(res, prices[i] - dp[i]);
        }
        return res;

//        int minprice = prices[0];
//        int maxprofit = 0;
//        for (int i = 1; i < prices.length; i++) {
//            if (prices[i] < minprice) {
//                minprice = prices[i];
//            } else if (prices[i] - minprice > maxprofit){
//                maxprofit = prices[i] - minprice;
//            }
//        }
//        return maxprofit;
    }

}
