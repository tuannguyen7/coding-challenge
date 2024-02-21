package leetcode;

import java.util.Arrays;

public class Problem1406 {

    static int maxM = 3;
    public String stoneGameIII(int[] stoneValue) {
        Long[][] dp = new Long[stoneValue.length][2];
        for (int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], null);
        long r = optimalPick(stoneValue, dp, 0, true);
        long total = sumArr(stoneValue, 0, stoneValue.length - 1);
        System.out.println(r);
        if (r * 2 > total) {
            return "Alice";
        } else if (r * 2 < total)
            return "Bob";
        else
            return "Tie";
    }

    public long sumArr(int[] arr, int l, int r) {
        long s = 0;
        for (; l <= r && l < arr.length; l++)
            s += arr[l];
        return s;
    }

    public long optimalPick(int[] stoneValue, Long[][] dp, int leftIndex, boolean isATurn) {
        int p = isATurn ? 0 : 1;
        if (leftIndex >= stoneValue.length)
            return 0;
        if (dp[leftIndex][p] != null)
            return dp[leftIndex][p];

        long r;
        if (isATurn) {
            long max = Long.MIN_VALUE;
            for (int i = 0; i < maxM && leftIndex + i < stoneValue.length; i++) {
                long s = sumArr(stoneValue, leftIndex, leftIndex + i);
                int newLeftIndex = leftIndex + i + 1;
                max = Math.max(max, s + optimalPick(stoneValue, dp, newLeftIndex, !isATurn));
            }
            r = max;
        } else {
            long min = Long.MAX_VALUE;
            for (int i = 0; i < maxM && leftIndex + i < stoneValue.length; i++) {
                int newLeftIndex = leftIndex + i + 1;
                min = Math.min(min, optimalPick(stoneValue, dp, newLeftIndex, !isATurn));
            }
            r = min;
        }

        dp[leftIndex][p] = r;
        return r;
    }

    public static void main(String[] args) {
        int[] stoneValue = {-1,-2,-3};
        new Problem1406().stoneGameIII(stoneValue);
    }
}
