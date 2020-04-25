package leetcode;

public class Problem777 {

  public static int characterReplacement(String s, int k) {
      int leftIndex = 0;
      int rightIndex = 0;
      int left = 0;
      int maxLen = 0;
      boolean firstFoundDiff = false;
      int tmpK = k;
      char curChar = s.charAt(0);

      while (rightIndex < s.length()) {
          if (s.charAt(rightIndex) == curChar) {
              rightIndex++;
          } else {
              if (!firstFoundDiff) {
                  firstFoundDiff = true;
                  leftIndex = rightIndex;
                  rightIndex++;
              } else {
                  tmpK--;
                  rightIndex++;
              }
          }

          if (tmpK == 0) {
              maxLen = Math.max(rightIndex - left, maxLen);
              rightIndex = leftIndex;
              left = leftIndex;
              curChar = s.charAt(leftIndex);
              tmpK = k;
              firstFoundDiff = false;
          }
      }

      return maxLen;
  }

  public static void main(String[] args) {
    String s = "ABABC";
    int k = 2;
    String s2 = "AABABBA";
    int k2 = 1;
    String s3 = "AABABBA";
    int k3 = 0;
    System.out.println(characterReplacement(s, k));
    System.out.println(characterReplacement(s2, k2));
    System.out.println(characterReplacement(s3, k3));
  }
}
