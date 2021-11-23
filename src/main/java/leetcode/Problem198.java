package leetcode;

public class Problem198 {

    public int rob(int[] nums) {
        int dp[] = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i-1];
            for (int j = 0; j < i -1; j++) {
                dp[i] = Math.max(dp[i], dp[j] + nums[i-1]);
            }
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        var app = new Problem198();
        int[] nums = {1,2,3,1};
        System.out.println(app.rob(nums));
    }
}
