package line;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

// Complexity: O(n)
// Travel the list of sticks, add as
public class Problem5 {

  public static void main(String[] args) throws Exception {
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      String[] input = br.readLine().split("\\s");
      int target = Integer.parseInt(br.readLine());
      List<Integer> nums = Arrays.stream(input).map(Integer::parseInt).collect(Collectors.toList());
      Map<Integer, Integer> map = new HashMap<>();
      int minIndex = Integer.MAX_VALUE;
      int greater = 0;
      int smaller = 0;
      for (int i = 0; i < nums.size(); i++) {
          int num = nums.get(i);
          if (map.containsKey(num)) {
              if (map.get(num) < minIndex) {
                  greater = Math.max(num, target - num);
                  smaller = Math.min(num, target - num);
                  minIndex = map.get(num);
              }
          } else {
              map.put(target - num, i);
          }
      }
      if (minIndex != Integer.MAX_VALUE) {
          System.out.println(smaller + " " + greater);
      } else {
          System.out.println("-1");
      }
  }
}
