package leetcode;

public class Problem26 {

  public int removeDuplicates(int[] nums) {
    int prev = 1;
    int prevVal = nums[0];
    int len = 1;
    for (int i = 1; i < nums.length; i++) {
      if (nums[i] != prevVal) {
          nums[prev] = nums[i];
          len++;
          prev++;
          prevVal = nums[i];
      }
    }

    return len;
  }

  public static void main(String[] args) {
    //
      int[] nums = {1, 1, 2};
      int result = new Problem26().removeDuplicates(nums);
      System.out.println(result);
  }
}
