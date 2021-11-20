package leetcode;

import java.util.Arrays;

public class Problem1143 {

    // Dynamic programming
    public int longestCommonSubsequence(String text1, String text2) {
        int dp[][] = new int[text1.length() + 1][text2.length() + 1];
        dp[0][0] = 0;
        for (int i = 0; i <= text1.length(); i++) {
            dp[i][0] = 0;
        }
        for (int i = 0; i <= text2.length(); i++) {
            dp[0][i] = 0;
        }
        for (int i = 1; i <= text1.length(); i++) {
            for (int j = 1; j <= text2.length(); j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                if (text1.charAt(i-1) == text2.charAt(j-1)) {
                    dp[i][j] = Math.max(dp[i][j], 1 + dp[i-1][j-1]);
                }
            }
        }

        return dp[text1.length()][text2.length()];
    }

    public int longestCommonSubsequence2(String text1, String text2) {
        int memo[][] = new int[text1.length()][text2.length()];
        for (int i = 0; i < text1.length(); i++) {
            Arrays.fill(memo[i], -1);
        }
        return recursion(text1, text2, 0, 0, memo);
    }

    public int recursion(String text1, String text2, int ti1, int ti2, int[][] memo) {
        if (ti1 == text1.length() || ti2 == text2.length()) return 0;
        if (memo[ti1][ti2] != -1)
            return memo[ti1][ti2];
        int m;
        if (text1.charAt(ti1) == text2.charAt(ti2)) {
            m = 1 + recursion(text1, text2, ti1+1, ti2+1, memo);
        } else {
            m = Math.max(recursion(text1, text2, ti1, ti2 + 1, memo), recursion(text1, text2, ti1+1, ti2, memo));
        }
        memo[ti1][ti2] = m;
        return m;
    }


    public static void main(String[] args) {
        var app = new Problem1143();
        String text1 = "abcde";
        String text2 = "ace";
        System.out.println(app.longestCommonSubsequence(text1, text2));
        System.out.println(app.longestCommonSubsequence2(text1, text2));
    }
}
