package leetcode;

import java.util.Arrays;

public class Problem14 {
    public String longestCommonPrefix(String[] strs) {
        int index = 0;
        int minLen = Arrays.stream(strs).map(String::length).min(Integer::compare).get();
        boolean found = true;
        while (index < minLen && found) {
            char curChar = strs[0].charAt(index);
            for (String str : strs) {
                if (str.charAt(index) != curChar) {
                    found = false;
                    break;
                }
            }
            if (found)
                index++;
        }

        return strs[0].substring(0, index);
    }

  public static void main(String[] args) {
    //
      String[] strings = {};
      System.out.println(new Problem14().longestCommonPrefix(strings));
  }
}
