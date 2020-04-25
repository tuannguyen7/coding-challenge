package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem12 {

  public String intToRoman(int num) {
    return helper(num);
  }

  public String helper(int num) {
    if (num == 0) {
      return "";
    }
    if (num >= 1000) {
      return "M" + helper(num - 1000);
    } else if (num >= 900) {
      return "CM" + helper(num - 900);
    } else if (num >= 500) {
      return "D" + helper(num - 500);
    } else if (num >= 400) {
      return "CD" + helper(num - 400);
    } else if (num >= 100) {
      return "C" + helper(num - 100);
    } else if (num >= 90) {
      return "XC" + helper(num - 90);
    } else if (num >= 50) {
      return "L" + helper(num - 50);
    } else if (num >= 40) {
      return "XL" + helper(num - 40);
    } else if (num >= 10) {
      return "X" + helper(num - 10);
    } else if (num >= 9) {
      return "IX" + helper(num - 9);
    } else if (num >= 5) {
      return "V" + helper(num - 5);
    } else if (num >= 4) {
      return "IV" + helper(num - 4);
    } else if (num >= 1) {
      return "I" + helper(num - 1);
    } else {
      return "";
    }
  }

  public static void main(String[] args) {
      new Problem12().helper(1);
  }
}
