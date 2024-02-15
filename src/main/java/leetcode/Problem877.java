package leetcode;

import java.util.Arrays;

public class Problem877 {

    public boolean stoneGame(int[] piles) {
        long[][][] dp = new long[piles.length][piles.length][2];
        for (int i = 0; i < dp.length; i++)
            for (int j = 0; j < dp.length; j++)
                Arrays.fill(dp[i][j], -1);
        Result rs = optimalPick(piles, dp, 0, piles.length - 1, true);
        System.out.println("a: " + rs.aSum + ", b: " + rs.bSum);
        return rs.aSum > rs.bSum;
    }

    static class Result {
        long aSum;
        long bSum;
        Result(long aSum, long bSum) {
            this.aSum = aSum;
            this.bSum = bSum;
        }
    }
    public Result optimalPick(int[] piles, long[][][] dp, int leftIndex, int rightIndex, boolean isATurn) {
        int abIndex = isATurn ? 0 : 1;
        if (leftIndex == rightIndex) {
            dp[leftIndex][rightIndex][abIndex] = piles[leftIndex];
            long aSum = isATurn ? piles[leftIndex] : 0;
            long bSum = !isATurn ? piles[leftIndex] : 0;
            return new Result(aSum, bSum);
        }

        if (dp[leftIndex][rightIndex][abIndex] != -1) {
            long aSum = isATurn ? dp[leftIndex][rightIndex][abIndex] : 0;
            long bSum = !isATurn ? dp[leftIndex][rightIndex][abIndex] : 0;
            return new Result(aSum, bSum);
        }
        int first = piles[leftIndex];
        int last = piles[rightIndex];
        if (isATurn) {
            // take first
            Result rs1 = optimalPick(piles, dp, leftIndex + 1, rightIndex, !isATurn);

            // take last
            Result rs2 = optimalPick(piles, dp, leftIndex, rightIndex - 1, !isATurn);
            Result optimalRs;
            long aSum;
            if (rs1.aSum > rs2.aSum) {
                optimalRs = rs1;
                aSum = first + optimalRs.aSum;
            } if (rs1.aSum < rs2.aSum) {
                optimalRs = rs2;
                aSum = last + optimalRs.aSum;
            } else {
                if (rs1.bSum < rs2.bSum) {
                    optimalRs = rs1;
                    aSum = first + optimalRs.aSum;
                } else {
                    optimalRs = rs2;
                    aSum = last + optimalRs.aSum;
                }
            }
            dp[leftIndex][rightIndex][abIndex] = aSum;
            return new Result(aSum, optimalRs.bSum);
        } else {
            long bSum;
            Result optimalRs;

            // take first
            Result rs1 = optimalPick(piles, dp, leftIndex + 1, rightIndex, !isATurn);

            // take last
            Result rs2 = optimalPick(piles, dp, leftIndex, rightIndex - 1, !isATurn);

            if (rs1.bSum > rs2.bSum) {
                optimalRs = rs1;
                bSum = optimalRs.bSum + first;
            } if (rs1.bSum < rs2.bSum) {
                optimalRs = rs2;
                bSum = optimalRs.bSum + last;
            } else {
                if (rs1.aSum < rs2.aSum) {
                    optimalRs = rs1;
                    bSum = optimalRs.bSum + first;
                } else {
                    optimalRs = rs2;
                    bSum = optimalRs.bSum + last;
                }
            }

            dp[leftIndex][rightIndex][abIndex] = bSum;
            return new Result(optimalRs.aSum, bSum);
        }

    }
}
