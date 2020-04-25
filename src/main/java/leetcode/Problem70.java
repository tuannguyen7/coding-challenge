package leetcode;

public class Problem70 {
    // DP solution. climbStairs(n) = climbStairs(n - 1) + climbStairs(n - 2)
    public int climbStairs(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i-2];
        }
        return dp[n];
    }

    // Fibonacci. f(n) = f(n-1) + f(n-2)
    public int climbStairs_Fibo(int n) {
        if (n == 1) return 1;
        int prev = 1;
        int cur = 1;
        while (n > 1) {
            int tmpCur = cur;
            cur = cur + prev;
            prev = tmpCur;
            n--;
        }
        return cur;
    }
}
