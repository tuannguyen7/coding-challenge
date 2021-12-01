package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Problem76 {

  public static String minWindow(String s, String t) {
    Map<Character, Integer> charCounts = new HashMap<>();
    int charCount = t.length();
    int curCharCount = 0;
    int l = 0;
    int r = l;

    for (char c : t.toCharArray()) {
      if (!charCounts.containsKey(c)) {
        charCounts.put(c, 0);
      }
      int count = charCounts.get(c);
      charCounts.put(c, count+1);
    }

    while (l < s.length()) {
      char curChr = s.charAt(l);
      if (!charCounts.containsKey(curChr)) {

      }
    }

    return "";
  }

  public static void main(String[] args) {
    String s = "ADOBECODEBANCEF";
    String s2 = "XYZT";
    String s3 = "ADOBEABCODEBANCEF";
    String s4 = "AA";
    String t = "ABC";
    String t2 = "AA";
//    System.out.println(minWindow(s, t));
//    System.out.println(minWindow(s2, t));
//    System.out.println(minWindow(s3, t));
    System.out.println(minWindow(s4, t2));
  }
}
