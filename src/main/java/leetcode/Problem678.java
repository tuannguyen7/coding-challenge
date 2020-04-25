package leetcode;

import java.util.Arrays;
import java.util.Stack;

public class Problem678 {
    // Solution1. backtracking
    public boolean checkValidString(String s) {
        return isValid(s, 0, 0);
    }

    public boolean isValid(String s, int index, int numOpenParen) {
        if (numOpenParen < 0)
            return false;
        if (index == s.length())
            return numOpenParen == 0;
        char c = s.charAt(index);
        if (c == '*')
            return isValid(s, index + 1, numOpenParen)
                    || isValid(s, index + 1, numOpenParen + 1)
                    || isValid(s, index + 1, numOpenParen - 1);
        else if (c == '(') {
            return isValid(s, index + 1, numOpenParen + 1);
        } else if (c == ')') {
            return isValid(s, index + 1, numOpenParen - 1);
        } else {
            return isValid(s, index + 1, numOpenParen);
        }
    }

    // Solution2. memorise recursion
    public boolean checkValidString2(String s) {
        Boolean[][] memo = new Boolean[s.length()][s.length()];
        return isValidMemo(s, 0, 0, memo);
    }

    public boolean isValidMemo(String s, int index, int numOpenParen, Boolean[][] memo) {
        if (numOpenParen < 0)
            return false;
        if (index == s.length())
            return numOpenParen == 0;
        if (memo[index][numOpenParen] != null)
            return memo[index][numOpenParen];
        char c = s.charAt(index);
        boolean ans;
        if (c == '*')
            ans = isValidMemo(s, index + 1, numOpenParen, memo)
                    || isValidMemo(s, index + 1, numOpenParen + 1, memo)
                    || isValidMemo(s, index + 1, numOpenParen - 1, memo);
        else if (c == '(') {
            ans = isValidMemo(s, index + 1, numOpenParen + 1, memo);
        } else if (c == ')') {
            ans = isValidMemo(s, index + 1, numOpenParen - 1, memo);
        } else {
            ans = isValidMemo(s, index + 1, numOpenParen, memo);
        }
        memo[index][numOpenParen] = ans;
        return ans;
    }

    // Solution3. Dynamic programming
    public boolean isValid3(String s) {
        boolean[][] existed = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++)
            Arrays.fill(existed[i], false);

        return false;
    }
}
