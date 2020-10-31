package leetcode;

import java.util.Arrays;

public class Problem300 {

    public int lengthOfLIS(int[] nums) {
        return solution2(nums);
    }
    
    // solution1: recursion
    public int solution1(int[] nums) {
        return recursion(nums, 0, -1, 0);
    }

    private int recursion(int[] nums, int curIndex, int lastIndex, int curLen) {
        if (curIndex == nums.length)
            return curLen;
        if (lastIndex == -1 || nums[curIndex] > nums[lastIndex]) {
            return Math.max(
                    recursion(nums, curIndex + 1, curIndex, curLen + 1),
                    recursion(nums, curIndex + 1, lastIndex, curLen));
        } else {
            return recursion(nums, curIndex + 1, lastIndex, curLen);
        }
    }

    // solution2: recursion with memorization // wrong answer
    public int solution2_WA(int[] nums) {
        int[][] memo = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++)
            Arrays.fill(memo[i], 0);
        return recursion_memo_WA(nums, 0, -1, 0, memo);
    }

    private int recursion_memo_WA(int[] nums, int curIndex, int lastIndex, int curLen, int[][] memo) {
        if (curIndex == nums.length)
            return curLen;
        if (lastIndex != -1 && memo[curIndex][lastIndex] != 0)
            return memo[curIndex][lastIndex];

        int maxLen;
        if (lastIndex == -1 || nums[curIndex] > nums[lastIndex]) {
            maxLen = Math.max(
                    recursion_memo_WA(nums, curIndex + 1, curIndex, curLen + 1, memo),
                    recursion_memo_WA(nums, curIndex + 1, lastIndex, curLen, memo));
        } else {
            maxLen = recursion_memo_WA(nums, curIndex + 1, lastIndex, curLen, memo);
        }
        if (lastIndex != -1)
            memo[curIndex][lastIndex] = maxLen;
        return maxLen;
    }

    public int solution2(int[] nums) {
        int[][] memo = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length; i++)
            Arrays.fill(memo[i], -1);
        return recursion_memo(nums, 0, -1, memo);
    }

    private int recursion_memo(int[] nums, int curIndex, int lastIndex, int[][] memo) {
        if (curIndex == nums.length)
            return 0;
        if (lastIndex != -1 && memo[curIndex][lastIndex] != -1)
            return memo[curIndex][lastIndex];
        int maxLen;
        if (lastIndex == -1 || nums[curIndex] > nums[lastIndex]) {
            maxLen = Math.max(
                    1 + recursion_memo(nums, curIndex + 1, curIndex, memo),
                    recursion_memo(nums, curIndex + 1, lastIndex, memo));
        } else {
            maxLen = recursion_memo(nums, curIndex + 1, lastIndex, memo);
        }
        if (lastIndex != -1)
            memo[curIndex][lastIndex] = maxLen;
        return maxLen;
    }

    // solution3: dynamic programming
    public int solution(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            dp[i] = 1;
        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j])
                    dp[i] = Math.max(dp[j] + 1, dp[i]);
            }
        }
        int max = dp[0];
        for (int i = 0; i < dp.length; i++) {
            if (dp[i] > max)
                max = dp[i];
        }
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {3,5,6,2,5,4,19,5,6,7,12};
        int[] nums2 = {1, 2, 3};
        System.out.println(new Problem300().lengthOfLIS(nums2));
    }
}
