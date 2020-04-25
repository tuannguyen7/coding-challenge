package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Problem17 {
    Map<Character, List<Character>> numToChars = Map.of('2', Arrays.asList('a', 'b', 'c'),
            '1', Collections.emptyList(),
            '3', Arrays.asList('d', 'e', 'f'),
            '4', Arrays.asList('g', 'h', 'i'),
            '5', Arrays.asList('j', 'k', 'l'),
            '6', Arrays.asList('m', 'n', 'o'),
            '7', Arrays.asList('p', 'q', 'r', 's'),
            '8', Arrays.asList('t', 'u', 'v'),
            '9', Arrays.asList('w', 'x', 'y', 'z'));

    public List<String> letterCombinations(String digits) {
        List<String> result = new LinkedList<>();
        helper(digits, 0, result, new StringBuilder());
        return result;
    }

    public void helper(String digits, int index, List<String> results, StringBuilder curPath) {
        if (index == digits.length()) {
            results.add(curPath.toString());
            return;
        }

        for (char c : numToChars.get(digits.charAt(index))) {
            helper(digits, index + 1, results, curPath.append(c));
            curPath.deleteCharAt(curPath.length() - 1);
        }
    }

    public static void main(String[] args) {
        List<String> result = new Problem17().letterCombinations("23");
        System.out.println(result.size());
    }
}
