package leetcode;

import java.util.Arrays;

public class Problem72 {

    // Solution1. backtracking
    public int minDistance(String word1, String word2) {
        return minDistanceHelper(word1, word2, word1.length() - 1, word2.length() - 1);
    }

    public int minDistanceHelper(String word1, String word2, int w1Index, int w2Index) {
        if (w1Index < 0)
            return w2Index + 1;
        if (w2Index < 0)
            return w1Index + 1;

        int minDistance = Integer.MAX_VALUE;
        if (word1.charAt(w1Index) == word2.charAt(w2Index))
            minDistance = minDistanceHelper(word1, word2, w1Index - 1, w2Index - 1);

        // case replacement
        minDistance = Math.min(minDistance, 1 + minDistanceHelper(word1, word2, w1Index - 1, w2Index - 1));

        // case insertion or deletion
        minDistance = Math.min(minDistance, 1 + minDistanceHelper(word1, word2, w1Index - 1, w2Index));
        minDistance = Math.min(minDistance, 1 + minDistanceHelper(word1, word2, w1Index, w2Index - 1));
        return minDistance;
    }
    // End of solution 1
    /**
    --------------------------------------------------------------------------------
    **/
    // Solution 2 backtracking with memorization
    public int minDistance_Memo(String word1, String word2) {
        int[][] memo = new int[word1.length()][word2.length()];

        for (int i = 0; i < memo.length; i++)
            Arrays.fill(memo[i], -1);

        return minDistanceHelper_Memo(word1, word2, word1.length() - 1, word2.length() - 1, memo);
    }

    public int minDistanceHelper_Memo(String word1, String word2, int w1Index, int w2Index, int[][] memo) {
        if (w1Index < 0)
            return w2Index + 1;
        if (w2Index < 0)
            return w1Index + 1;

        if (memo[w1Index][w2Index] != -1)
            return memo[w1Index][w2Index];
        int minDistance = Integer.MAX_VALUE;
        if (word1.charAt(w1Index) == word2.charAt(w2Index))
            minDistance = minDistanceHelper_Memo(word1, word2, w1Index - 1, w2Index - 1, memo);

        // case replacement
        minDistance = Math.min(minDistance, 1 + minDistanceHelper_Memo(word1, word2, w1Index - 1, w2Index - 1, memo));

        // case insertion or deletion
        minDistance = Math.min(minDistance, 1 + minDistanceHelper_Memo(word1, word2, w1Index - 1, w2Index, memo));
        minDistance = Math.min(minDistance, 1 + minDistanceHelper_Memo(word1, word2, w1Index, w2Index - 1, memo));
        memo[w1Index][w2Index] = minDistance;
        return minDistance;
    }

    public static void main(String[] args) {
        String word1 = "horse";
        String word2 = "ros";
        //String word1 = "";
        //String word2 = "ros";
        System.out.println(new Problem72().minDistance(word1, word2));
    }
}
