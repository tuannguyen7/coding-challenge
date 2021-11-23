package leetcode;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Problem139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        Boolean memo[][] = new Boolean[s.length()][s.length()];
        Set<String> words = wordDict.stream().collect(Collectors.toSet());
        return recursion(s, 0, 0, new StringBuilder(), words, memo);
    }

    // Cach 1: memoization startIndex va index
    public boolean recursion(String s, int startIndex, int index, StringBuilder curWord, Set<String> words, Boolean[][] memo) {
        if (index == s.length()) {
            if (curWord.length() == 0) { // no word to check
                return true;
            }
            return words.contains(curWord.toString());
        }
        if (memo[startIndex][index] != null) return memo[startIndex][index];

        boolean result = false;
        curWord.append(s.charAt(index)); // check current char
        if (recursion(s, startIndex, index+1, curWord, words, memo)) {
            result = true;
        }

        if (!result && words.contains(curWord.toString())) { // if result is true then don't need to do next
            result = recursion(s, index+1,index+1, new StringBuilder(), words, memo);
        }
        curWord.deleteCharAt(curWord.length()-1); // remove last char
        memo[startIndex][index] = result;
        return result;
    }

    // Cach 2: memoization startIndex la du
    public boolean wordBreak2(String s, List<String> wordDict) {
        Boolean memo[] = new Boolean[s.length()];
        Set<String> words = wordDict.stream().collect(Collectors.toSet());
        return recursion(s, 0, 0, new StringBuilder(), words, memo);
    }

    public boolean recursion(String s, int startIndex, int index, StringBuilder curWord, Set<String> words, Boolean[] memo) {
        if (index == s.length()) {
            if (curWord.length() == 0) { // no word to check
                return true;
            }
            return words.contains(curWord.toString());
        }
        if (memo[startIndex] != null) return memo[startIndex];

        boolean result = false;
        curWord.append(s.charAt(index)); // check current char
        if (recursion(s, startIndex, index+1, curWord, words, memo)) {
            result = true;
        }

        if (!result && words.contains(curWord.toString())) { // if result is true then don't need to do next
            result = recursion(s, index+1,index+1, new StringBuilder(), words, memo);
        }
        curWord.deleteCharAt(curWord.length()-1); // remove last char
        memo[startIndex] = result;
        return result;
    }

    // cach 3
    public boolean wordBreak3(String s, List<String> wordDict) {
        Set<String> words = wordDict.stream().collect(Collectors.toSet());
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i < dp.length; i++) {
            dp[i] = false;
            for (int j = 1; j <= i; j++) { // j is string index
                if (dp[j - 1] && words.contains(s.substring(j-1, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[dp.length - 1];
    }

    public static void main(String[] args) {
        var app = new Problem139();
        String s = "leetcode";
        List<String> words = Arrays.asList("leet","code");
        System.out.println(app.wordBreak3(s, words));
    }
}
