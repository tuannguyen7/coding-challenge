package leetcode;

public class Problem583 {

    public int minDistance(String word1, String word2) {
        int[][] dp = new int[word1.length()+1][word2.length()+1];
        dp[0][0] = 0;
        for (int i = 0; i <= word1.length(); i++) {
            dp[i][0] = i;
        }
        for (int i = 0; i <= word2.length(); i++) {
            dp[0][i] = i;
        }

        for (int i = 1; i <= word1.length(); i++)
            for (int j = 1; j <= word2.length(); j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + 1;
                if (word1.charAt(i-1) == word2.charAt(j-1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                }
            }

        return dp[word1.length()][word2.length()];
    }

    public static void main(String[] args) {
        Problem583 app = new Problem583();
        String word1 = "sea";
        String word2 = "eat";
        System.out.println(app.minDistance(word1, word2));
    }
}
