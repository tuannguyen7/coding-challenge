package leetcode;

import java.util.Arrays;

public class Problem1140 {

    public int stoneGameII(int[] piles) {
        long[][][] dp = new long[piles.length][piles.length + 1][2];
        for (int i = 0; i < dp.length; i++)
            for (int j = 0; j < dp[0].length; j++)
                Arrays.fill(dp[i][j], -1);
        long r = optimalPick(piles, dp, 0, 1, true);
        System.out.println(r);
        return (int)r;
    }

    public long sumArr(int[] arr, int l, int r) {
        long s = 0;
        for (; l <= r && l < arr.length; l++)
            s += arr[l];
        return s;
    }

    public long optimalPick(int[] piles, long[][][] dp, int leftIndex, int m, boolean isATurn) {
        int p = isATurn ? 0 : 1;
        if (leftIndex >= piles.length)
            return 0;
        if (dp[leftIndex][m][p] != -1)
            return dp[leftIndex][m][p];

        if (2*m >= piles.length - leftIndex) {
            long r;
            if (isATurn) {
                r = sumArr(piles, leftIndex, piles.length - 1);
            }
            else {
                r = 0; // Bob will take all stones so return 0
            }
            dp[leftIndex][m][p] = r;
            return r;
        }
        long r;
        if (isATurn) {
            long max = 0;
            for (int i = 0; i < 2*m && leftIndex + i < piles.length; i++) {
                long s = sumArr(piles, leftIndex, leftIndex + i);
                int newLeftIndex = leftIndex + i + 1;
                int newM = Math.max(i+1, m);
                max = Math.max(max, s + optimalPick(piles, dp, newLeftIndex, newM, !isATurn));
            }
            r = max;
        } else {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < 2*m; i++) {
                int newLeftIndex = leftIndex + i + 1;
                int newM = Math.max(i+1, m);
                min = Math.min(min, optimalPick(piles, dp, newLeftIndex, newM, !isATurn));
            }
            r = min;
        }

        dp[leftIndex][m][p] = r;
        return r;
    }

    public static void main(String[] args) {
        int[] piles = {2};
        new Problem1140().stoneGameII(piles);
    }
}
