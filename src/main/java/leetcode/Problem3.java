package leetcode;

import java.util.HashSet;
import java.util.Set;

// 3. Longest Substring Without Repeating Characters
public class Problem3 {

  public static int lengthOfLongestSubstring(String s) {
    int maxLen = 0;
    int i = 0, j = 0;
    Set<Character> chars = new HashSet<>();

    while (i < s.length() && j < s.length()) {
      char c = s.charAt(j);
      if (chars.contains(c)) {
        maxLen = Math.max(j - i, maxLen);
        chars.remove(s.charAt(i));
        i++;
      } else {
        chars.add(c);
        j++;
      }
    }

    return Math.max(j - i, maxLen);
  }

  public static void main(String[] args) {
    //
    String s = "pwwkew";
    String s2 = "abcd";
    String s3 = "aab";
    System.out.println(lengthOfLongestSubstring(s3));
    System.out.println(lengthOfLongestSubstring(s));
    System.out.println(lengthOfLongestSubstring(s2));

  }
}
