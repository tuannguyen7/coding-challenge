package leetcode;

public class Problem198 {

    // cach 1: O(n), space O(n)
    public int rob(int[] nums) {
        int dp[] = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i-1]);
        }
        return dp[dp.length-1];
    }

    // cach 2: O(n), space O(1)
    public int rob2(int[] nums) {
        int curMax = nums[0];
        int prevMax = nums[0];
        int pPrevMax = 0;
        for (int i = 2; i < nums.length + 1; i++) {
            curMax = Math.max(prevMax, pPrevMax + nums[i-1]);
            pPrevMax = prevMax;
            prevMax = curMax;
        }
        return curMax;
    }

    public static void main(String[] args) {
        var app = new Problem198();
        int[] nums = {1,2,3,1};
        System.out.println(app.rob(nums));
    }
}
