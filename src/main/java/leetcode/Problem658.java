package leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Problem658 {

    private static final Logger log = LoggerFactory.getLogger(Problem658.class);

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        if (arr.length == 0)
            return Collections.emptyList();
        int lowerIndex = lowerBound(arr, x); // first index that arr[index] = x

        int leftIndex = lowerIndex - 1;
        int rightIndex = lowerIndex;
        while (k > 0) {
            if (rightIndex >= arr.length) {
                leftIndex--;
            } else if (leftIndex < 0) {
                rightIndex++;
            } else if (Math.abs(x - arr[leftIndex]) <= (arr[rightIndex] - x)) {
                leftIndex--;
            } else {
                rightIndex++;
            }
            k--;
        }
        return Arrays.stream(arr).boxed().collect(Collectors.toList()).subList(leftIndex + 1, rightIndex);
    }

    public int lowerBound(int[] arr, int target) {
        int lo = 0;
        int hi = arr.length - 1;
        int ans = arr.length; // in case target > arr
        while (lo <= hi) {
            int mid = lo + (hi - lo)/2;
            if (arr[mid] >= target) {
                hi = mid - 1;
                ans = mid;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

  public static void main(String[] args) {
      int[] arr = {0,0,1,2,3,3,4,7,7,8};
      List<Integer> s = new Problem658().findClosestElements(arr, 3, 5);
      log.info("{}", s);
  }
}
