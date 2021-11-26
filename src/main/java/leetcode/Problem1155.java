package leetcode;

import java.util.Arrays;

public class Problem1155 {

    private int MOD = 1000000007;
    public int numRollsToTarget(int d, int f, int target) {
        int[][] dp = new int[d][target + 1];

        for (int i = 1; i < dp.length; i++) {
            Arrays.fill(dp[i], 0);
        }
        // set target d[0][1] = 1, dp[0][2] = 1, etc
        for (int t = 1; t <= Math.min(f, target); t++) {
            dp[0][t] = 1;
        }
        for (int i = 1; i < d; i++) {
            for (int curTarget = 0; curTarget < dp[0].length; curTarget++) {
                for (int curF = 1; curF <= f; curF++) {
                    if (curTarget - curF >= 0) {
                        int totalWays = dp[i][curTarget] + dp[i-1][curTarget-curF];
                        if (totalWays >= MOD) {
                            totalWays %= MOD;
                        }

                        dp[i][curTarget] = totalWays;
                    }
                }
            }
        }

        return dp[d-1][target];
    }

    public static void main(String[] args) {
        var app = new Problem1155();
        int d = 30;
        int f = 30;
        int target = 500;
        System.out.println(app.numRollsToTarget(d, f, target));
    }
}
