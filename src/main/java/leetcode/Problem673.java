package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Problem673 {

    private int count = 0;
    private int maxLen = 99999;
    public int findNumberOfLIS(int[] nums) {
        return solution2(nums);
    }

    // solution1: recursion
    public int solution1(int[] nums) {
        // first get the maximum
        maxLen = find(nums, 0, Integer.MIN_VALUE, 0);
        // second count how many
        find(nums, 0, Integer.MIN_VALUE, 0);
        return count;
    }

    public int find(int[] nums, int index, int lastNum, int curLen) {
        if (index == nums.length) {
            if (curLen == maxLen) count++;
            return curLen;
        }
        int max = 0;
        if (nums[index] <= lastNum) {
            max = Math.max(find(nums, index + 1, lastNum, curLen), max);
        } else {
            max = Math.max(find(nums, index + 1, lastNum, curLen), max);
            max = Math.max(find(nums, index + 1, nums[index], curLen + 1), max);
        }

        return max;
    }

    // solution2: recursion with memorization
    // in progress
    public int solution2(int[] nums) {
        int[][] memo = new int[nums.length][nums.length + 1];
        for (int i = 0; i < nums.length; i++)
            Arrays.fill(memo[i], -1);
        int maxLen = recursion_memo(nums, 0, 0, memo);

        return 0;
    }

    private int recursion_memo(int[] nums, int curIndex, int lastIndexPlusOne, int[][] memo) {
        if (curIndex == nums.length)
            return 0;
        int lastIndex = lastIndexPlusOne - 1;
        if (memo[curIndex][lastIndexPlusOne] != -1)
            return memo[curIndex][lastIndexPlusOne];
        int maxLen;
        if (lastIndex == -1 || nums[curIndex] > nums[lastIndex]) {
            maxLen = Math.max(
                    1 + recursion_memo(nums, curIndex + 1, curIndex + 1, memo),
                    recursion_memo(nums, curIndex + 1, lastIndexPlusOne, memo));
        } else {
            maxLen = recursion_memo(nums, curIndex + 1, lastIndexPlusOne, memo);
        }
        memo[curIndex][lastIndexPlusOne] = maxLen;
        return maxLen;
    }

    // Solution3: dynamic programming
    // not implemented yet
    public int solution3(int[] nums) {
        return 0;
    }

    public static void main(String[] args) {
        int[] nums = {1,3,5,4,7};
        //int[] nums = {};
        System.out.println(new Problem673().findNumberOfLIS(nums));
    }
}
