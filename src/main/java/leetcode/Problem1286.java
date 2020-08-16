package leetcode;

import java.util.*;

public class Problem1286 {

    static class CombinationIterator {

        List<String> combinations;
        Iterator<String> iterator;

        public CombinationIterator(String characters, int combinationLength) {
            combinations = gen(characters, combinationLength, 0, 0, new StringBuilder());
            iterator = combinations.iterator();
        }

        public List<String> gen(String characters, int len, int curLen, int curIndex, StringBuilder curStr) {
            if (curLen == len) {
                return Arrays.asList(curStr.toString());
            }

            if (characters.length() - curIndex < len - curLen)
                return Collections.emptyList();

            List<String> result = new ArrayList<>();
            curStr.append(characters.charAt(curIndex));
            result.addAll(gen(characters, len, curLen + 1, curIndex + 1, curStr));
            curStr.deleteCharAt(curLen);
            result.addAll(gen(characters, len, curLen, curIndex+1, curStr));
            return result;
        }

        public String next() {
            return iterator.next();
        }

        public boolean hasNext() {
            return iterator.hasNext();
        }
    }

    public static void main(String[] args) {
        String characters = "abc";
        int combinationLength = 2;
        new CombinationIterator(characters, combinationLength);
    }
}
