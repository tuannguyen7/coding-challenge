package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

// 3. Longest Substring Without Repeating Characters
public class Problem3 {

  public static int lengthOfLongestSubstring(String s) {
    int maxLen = 0;
    int l = 0, r = 0;
    Set<Character> chars = new HashSet<>();

    while (r < s.length()) {
      char c = s.charAt(r);
      if (chars.contains(c)) {
        maxLen = Math.max(r - l, maxLen);
        chars.remove(s.charAt(l));
        l++;
      } else {
        chars.add(c);
        r++;
      }
    }

    return Math.max(r - l, maxLen);
  }

  // Cải tiến: Nhảy l nhanh hơn khi s_r trung
  public static int lengthOfLongestSubstring2(String s) {
    int maxLen = 0;
    int l = 0, r = 0;
    Map<Character, Integer> chars = new HashMap<>();

    while (r < s.length()) {
      char c = s.charAt(r);
      if (chars.containsKey(c)) {
        maxLen = Math.max(r - l, maxLen);
        int nextL = chars.get(c) + 1;
        for (; l < nextL; l++) {
          chars.remove(s.charAt(l));
        }
      } else {
        chars.put(c, r);
        r++;
      }
    }

    return Math.max(r - l, maxLen);
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
