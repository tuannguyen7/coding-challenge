package leetcode;

public class Problem213 {

    public int rob(int[] nums) {
        if (nums.length == 1) { // not work if nums.length < 2
            return nums[0];
        }
        int dp[] = new int[nums.length + 1];
        int dp2[] = new int[nums.length + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        dp2[0] = 0;
        dp2[1] = 0;

        for (int i = 2; i < dp.length - 1; i++) {
            dp[i] = dp[i-1];
            dp2[i] = dp2[i-1];
            for (int j = 0; j < i -1; j++) {
                dp[i] = Math.max(dp[i], dp[j] + nums[i-1]);
                if (j != 1) {
                    dp2[i] = Math.max(dp2[i], dp2[j] + nums[i-1]);
                }
            }
        }

        int i = dp.length - 1;
        dp[i] = dp[i-1]; // không chọn nhà cuối
        for (int j = 0; j < i - 1; j++) {
            if (j == 1) continue;
            dp[i] = Math.max(dp[i], dp2[j] + nums[i-1]);
        }
        return dp[dp.length-1];
    }

    public static void main(String[] args) {
        var app = new Problem213();
        int nums[] = {2,3,2};
        System.out.println(app.rob(nums));
    }
}
