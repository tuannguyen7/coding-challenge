package leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Problem127 {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> wordMap = new HashMap<>();
        for (int i = 0; i < wordList.size() - 1; i++) {
            String w1 = wordList.get(i);
            List<String> transformableWords;
            if (wordMap.containsKey(w1)) {
                transformableWords = wordMap.get(w1);
            } else {
                transformableWords = new ArrayList<>();
                wordMap.put(w1, transformableWords);
            }
            for (int j = i + 1; j < wordList.size(); j++) {
                String w2 = wordList.get(j);
                if (canTransform(w1, w2)) {
                    transformableWords.add(w2);
                    if (!wordMap.containsKey(w2)) {
                        wordMap.put(w2, new ArrayList<>());
                    }
                    wordMap.get(w2).add(w1);
                }
            }
        }
        return 0;
    }

    private boolean canTransform(String w1, String w2) {
        int diff = 0;
        for (int i = 0; i < w1.length() && diff < 2; i++) {
            if (w1.charAt(i) != w2.charAt(i)) {
                diff++;
            }
        }
        return diff == 1;
    }
}
