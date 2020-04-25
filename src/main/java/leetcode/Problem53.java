package leetcode;

public class Problem53 {

    public int maxSubArray(int[] nums) {
        return naiveSolution(nums);
    }

    // 1. Naive solution O(n^3)
    public int naiveSolution(int[] nums) {
        int leftMax = 0;
        int rightMax = 0;
        int curMax = nums[0];
        for (int arrSize = 1; arrSize <= nums.length; arrSize++) {
            for (int i = 0; i < nums.length; i++) {
                int m = 0;
                for (int j = i; j < nums.length; j++) {
                    m += nums[j];
                    if (m > curMax) {
                        leftMax = i;
                        rightMax = j;
                        curMax = m;
                    }
                }
            }
        }
        return curMax;
    }

    // 2. precomputed table O(n^2)
    public int improveSolution1(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[] precomputedSum = new int[nums.length + 1];
        int max = nums[0];
        precomputedSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            precomputedSum[i + 1] = precomputedSum[i] + nums[i];
        }

        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j <= nums.length; j++) {
                max = Math.max(precomputedSum[j] - precomputedSum[i], max);
            }
        }

        return max;
    }

    /**
     * dynamic programming
     * */
    public int improveSolution2(int[] nums) {
        if (nums.length == 0)
            return 0;
        int[][] dp = new int[nums.length][2];
        dp[0][0] = nums[0];         // end
        dp[0][1] = -999999;         // not end
        for (int i = 1; i < nums.length; i++) {
            dp[i][0] = Math.max(dp[i-1][0] + nums[i], nums[i]);
            dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1]);
        }
        return Math.max(dp[nums.length-1][0], dp[nums.length-1][1]);
    }

    public static void main(String[] args) {
        //int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        //int[] nums = {1,2,5};
        int[] nums = {-7, -3,-1};
        System.out.println(new Problem53().maxSubArray(nums));
        System.out.println(new Problem53().improveSolution1(nums));
        System.out.println(new Problem53().improveSolution2(nums));
    }
}
