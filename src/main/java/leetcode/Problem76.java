package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Problem76 {

  public static String minWindow(String s, String t) {
    Map<Character, Integer> charCounts = new HashMap<>();
    int patLen = t.length();
    int minLen = s.length() + 1;
    int minL = 0, minR = 0;
    int l = 0, r = 0;
    for (char c : t.toCharArray()) {
      if (!charCounts.containsKey(c)) {
        charCounts.put(c, 0);
      }
      int curCount = charCounts.get(c);
      charCounts.put(c, curCount + 1);
    }

    while (l < s.length()) {
      for (; r < s.length(); r++) {
        char curCh = s.charAt(r);
        if (!charCounts.containsKey(curCh)) {
          continue;
        }
        int chCount = charCounts.get(curCh);
        charCounts.put(curCh, chCount - 1);
        if (chCount > 0) {
          patLen--;
        }
        if (patLen == 0) {
          break;
        }
      }

      if (patLen > 0) {// not found any
        break;
      }

      if (r - l + 1 < minLen) {
        minLen = r - l +1;
        minL = l;
        minR = r;
      }

      l++;
      while (true) {
        char curCh = s.charAt(l-1);
        if (!charCounts.containsKey(curCh)) {
          if ((r - l + 1) < minLen) {
            minLen = (r - l + 1);
            minL = l;
            minR = r;
          }
          l++;
          continue;
        }
        int chCount = charCounts.get(curCh);
        charCounts.put(curCh, chCount+1);
        if (chCount >= 0) {
          patLen++;
        }
        if (patLen > 0) {
          break;
        }
        if ((r - l + 1) < minLen) {
          minLen = (r - l + 1);
          minL = l;
          minR = r;
        }
        l++;
      }
      r++;
    }

    if (minLen == s.length() + 1) {
      return "";
    }
    return s.substring(minL, minR + 1);
  }

  public static void main(String[] args) {
    String s = "ADOBECODEBANCEF";
    String s2 = "XYZT";
    String s3 = "ADOBEABCODEBANCEF";
    String s4 = "AA";
    String t = "ABC";
    String t2 = "AA";
    System.out.println(minWindow(s, t));
//    System.out.println(minWindow(s2, t));
//    System.out.println(minWindow(s3, t));
    System.out.println(minWindow(s4, t2));
  }
}
