package leetcode;

import java.util.*;

public class Problem15 {
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new LinkedList<>();
        Arrays.sort(nums);
        int[][] combinedSum = new int[nums.length][nums.length];
        for (int i = 0; i < nums.length - 1; i++)
            for (int j = i + 1 ; j < nums.length; j++) {
                combinedSum[i][j] = nums[i] + nums[j];
            }

        Map<Integer, Integer> target = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int minusNum = nums[i];
            target.put(minusNum, i);
        }

        for (int i = 0; i < nums.length - 1; i++)
            for (int j = i + 1 ; j < nums.length; j++) {
                int val = combinedSum[i][j];
                if (i > 0 && combinedSum[i - 1][j] == val)
                    continue;
                if (j > (i + 1) && combinedSum[i][j - 1] == val)
                    continue;
                if (!target.containsKey(-val))
                    continue;
                int k = target.get(-val);
                if (k > j)
                    result.add(Arrays.asList(nums[i], nums[j], nums[k]));
            }

        return result;
    }

    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> result = new Problem15().threeSum(nums);
        System.out.println(result.size());
    }
}
