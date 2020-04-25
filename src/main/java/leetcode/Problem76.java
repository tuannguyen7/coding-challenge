package leetcode;

import java.util.Arrays;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

public class Problem76 {

  public static String minWindow(String s, String t) {
    Set<Character> chars = t.chars().mapToObj(e -> (char)e).collect(Collectors.toUnmodifiableSet());
    TreeSet<CharIndex> tree = new TreeSet<>();
    int count = 0;
    int l =0, r =0, curMin = s.length();
    int[] indexes = new int[128];
    Arrays.fill(indexes, -1);
    for (int i = 0; i < s.length(); i++) {
      char curChar = s.charAt(i);
      if (!chars.contains(curChar)) {
        continue;
      }

      CharIndex newCharIndex = new CharIndex(curChar, i);
      CharIndex forFinding = new CharIndex(curChar, indexes[curChar]);
      indexes[curChar] = i;
      if (!tree.contains(forFinding)) {
        tree.add(newCharIndex);
        count++;
      } else {
        tree.remove(forFinding);
        tree.add(newCharIndex);
      }

      if (count == t.length()) {
        int newMin = tree.last().index - tree.first().index;
        if (newMin < curMin) {
          curMin = newMin;
          l = tree.first().index;
          r = tree.last().index;
        }
      }
    }

    if (count < t.length()) return "";
    return s.substring(l, r + 1);
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

  public static class CharIndex implements Comparable<CharIndex> {
    char c;
    int index;

    public CharIndex(char c, int index) {
      this.c = c;
      this.index = index;
    }

    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      CharIndex charIndex = (CharIndex) o;
      return c == charIndex.c;
    }

    @Override
    public int hashCode() {
      return Objects.hash(c, index);
    }

    @Override
    public int compareTo(CharIndex o) {
      return index - o.index;
    }
  }
}
