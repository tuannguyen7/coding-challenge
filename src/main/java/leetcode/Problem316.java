package leetcode;

import java.util.Arrays;

public class Problem316 {

    public static void main(String[] args) {
        String s = "bcabc";
        String s2 = "cbacdcbc";
        System.out.println(new Problem316().removeDuplicateLetters(s));
        System.out.println(new Problem316().removeDuplicateLetters(s2));
    }

    public String removeDuplicateLetters(String s) {
        int[] lettersCount = new int[26];
        boolean[] selected = new boolean[s.length()];
        Arrays.fill(lettersCount, 0);
        Arrays.fill( selected, false);
        for (char c : s.toCharArray()) {
            lettersCount[c - 'a'] += 1;
        }

        StringBuilder finalString = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            char l = s.charAt(i);
            if (selected[l - 'a']) continue;
            if (lettersCount[l - 'a'] == 1) {
                selected[l -'a'] = true;
                finalString.append(l);
                continue;
            }
            boolean hasSmaller = false;
            for (int j = i + 1; j < s.length(); j++) {
                char comparingLetter = s.charAt(j);
                if (selected[comparingLetter - 'a']) continue;
                if (comparingLetter < l) {
                    hasSmaller = true;
                    break;
                }
            }
            if (!hasSmaller) {
                finalString.append(l);
                selected[l -'a'] = true;
            }
        }

        return finalString.toString();
    }
}
