package dp;

/** https://leetcode.com/problems/max-chunks-to-make-sorted-ii/ */

public class MaxChunksToMakeSortedII {
    public int maxChunksToSorted(int[] arr) {
        // dp[i] represents the smallest element to the right of index i including i
        int[] dp = new int[arr.length];
        dp[arr.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            dp[i] = Math.min(arr[i], dp[i + 1]);
        }

        // from left to right,
        // maintain variables max, numChunks
        // if max > dp[i] -> include a[i] into cur chunk, update max if possible
        // otherwise, chuck++, update max
        int max = arr[0], numChunks = 1;
        for (int i = 1; i < arr.length; i++) {
            if (max > dp[i]) {
                max = Math.max(arr[i], max);
                continue;
            }
            numChunks++;
            max = arr[i];
        }
        return numChunks;
    }
}
