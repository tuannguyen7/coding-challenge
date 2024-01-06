package leetcode;

import java.util.List;
import java.util.Set;

public class Problem140 {

    public List<String> wordBreak(String s, List<String> wordDict) {

        return null;
    }

    List<String> buildSentences(String s, int pos, StringBuilder curWord, Set<String> wordDict, List<String> curSentence, List<String> acc) {
        if (pos >= s.length()) {
//            if (wordDict.contains(curWord.toString())) {
//                curSentence.
//            }
            return acc;
        }

        if (wordDict.contains(curWord.toString())) {
            curSentence.add(curWord.toString());
        }

        return null;
    }
    public static void main(String[] args) {

    }
}
