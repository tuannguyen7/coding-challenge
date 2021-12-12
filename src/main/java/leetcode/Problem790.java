package leetcode;

public class Problem790 {

    private static int MOD = 1000000007;
    public int numTilings(int n) {
        if (n == 1) {
            return 1;
        }
        long[][] dp = new long[n+1][n+1];
        dp[0][0] = 1;
        dp[1][2] = 1;
        dp[2][1] = 1;
        for (int i = 1; i < n+1; i++) {
            dp[i][i] = dp[i-1][i-1];
            if (i >= 2) {
                dp[i][i] = dp[i][i] + dp[i-2][i-2];
                dp[i][i] = dp[i][i] + dp[i-1][i-2];
                dp[i][i] += dp[i-2][i-1];
            }
            dp[i][i] %= MOD;
            if (i + 2 < n+1) {
                dp[i+1][i+2] = (dp[i][i] + dp[i+1][i])%MOD;
                dp[i+2][i+1] = (dp[i][i] + dp[i][i+1])%MOD;
            }
        }

        return (int)dp[n][n];
    }

    public static void main(String[] args) {
        var app = new Problem790();
        int n = 5;
        System.out.println(app.numTilings(n));
    }
}
