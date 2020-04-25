package leetcode;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Problem60 {
  public String getPermutation(int n, int k) {
    StringBuilder result = new StringBuilder();
    int permuN = permutation(n - 1);
    ArrayList<Integer> nums = IntStream.range(0, n + 1).collect(ArrayList::new, (a, e) -> a.add(e), (a, b) -> a.addAll(b));
    while (n > 1) {
      int numIndex = (k-1)/permuN + 1;
      result.append(nums.remove(numIndex).toString());
      k = k%(permuN);
      if (k == 0)
        k = permuN;
      permuN /= n - 1;
      n--;
    }
    result.append(nums.get(1));
    return result.toString();
  }

  public static void main(String[] args) {
    int n = 3;
    int k = 4;
    System.out.println(new Problem60().getPermutation(n, k));
  }

  public int permutation(int n) {
    int ans = 1;
    while (n > 1) {
      ans *= n;
      n--;
    }
    return ans;
  }
}
