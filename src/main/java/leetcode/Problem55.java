package leetcode;

public class Problem55 {
    public boolean canJump(int[] nums) {
        return canJumpRecursive(nums, 0);
    }

    // 1. Backtrack
    public boolean canJumpRecursive(int[] nums, int curIndex) {
        if (curIndex >= nums.length)
            return false;
        if (curIndex == nums.length - 1)
            return true;

        for (int i = 1; i <= nums[curIndex]; i++) {
            if (canJumpRecursive(nums, curIndex + i))
                return true;
        }
        return false;
    }

    // 2. Dynamic programming
    public boolean canJumpSolution2(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        int lastIndex = nums.length - 1;
        dp[lastIndex] = true;
        for (int i = lastIndex - 1; i >= 0; i--) {
            if (nums[i] >= lastIndex - i)
                dp[i] = true;
            else {
                boolean canJump = false;
                for (int j = 1; j <= nums[i]; j++) {
                    if (dp[i + j]) {
                        canJump = true;
                        break;
                    }
                }
                dp[i] = canJump;
            }
        }
        return dp[0];
    }

    // 2. Dynamic programming 2
    public boolean canJumpSolution3(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        int lastIndex = nums.length - 1;
        dp[lastIndex] = true;
        int minCanJump = lastIndex;
        for (int i = lastIndex - 1; i >= 0; i--) {
            if (nums[i] + i >= minCanJump) {
                minCanJump = i;
                dp[i] = true;
            }
        }
        return dp[0];
    }

    public static void main(String[] args) {
        int[] nums = {2,3,1,1,4};
        System.out.println(new Problem55().canJump(nums));
        System.out.println(new Problem55().canJumpSolution2(nums));
        System.out.println(new Problem55().canJumpSolution3(nums));
    }
}
