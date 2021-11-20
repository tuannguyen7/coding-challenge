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

    public static void main(String[] args) {
        var app = new Problem139();
        String s = "leetcode";
        List<String> words = Arrays.asList("leet","code");
        System.out.println(app.wordBreak(s, words));
    }
}
