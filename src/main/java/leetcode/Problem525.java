package leetcode;

import java.util.HashMap;
import java.util.HashSet;

public class Problem525 {
    public int findMaxLength(int[] nums) {
        int[] preSumNums = new int[nums.length + 1];
        HashMap<Integer, Integer> map = new HashMap<>();
        preSumNums[0] = 0;

        for (int i = 0; i < nums.length; i++) {
            // 0 => -1, 1 => 1
            int addition = nums[i] == 1 ? 1 : -1;
            preSumNums[i + 1] = preSumNums[i] + addition;
        }

        int maxLen = 0;
        for (int i = 0; i < preSumNums.length; i++) {
            if (map.containsKey(preSumNums[i])) {
                maxLen = Math.max(maxLen, i - map.get(preSumNums[i]));
            } else {
                map.put(preSumNums[i], i);
            }
        }
        return maxLen;
    }

    public static void main(String[] args) {
    //
    }
}
