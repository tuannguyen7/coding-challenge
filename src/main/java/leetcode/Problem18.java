package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Problem18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        nums = Arrays.stream(nums).sorted().toArray();
        Set<Integer> set = new HashSet<>();
        Set<String> stringAns = new HashSet<>();
        for (int i = 1; i < nums.length - 2; i++) {
            set.add(nums[i-1]);
            for (int j = i + 1; j < nums.length - 1; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];
                    int missing = target - sum;
                    if (set.contains(missing)) {
                        String qualified = missing + "," + nums[i] + "," + nums[j] + "," + nums[k];
                        stringAns.add(qualified);
                    }
                }
            }
        }

        return stringAns.stream().map(s -> {
            return Arrays.stream(s.split(",")).map(Integer::valueOf).collect(Collectors.toList());
        }).collect(Collectors.toList());
    }

    public static void main(String[] args) {
        int[] nums = {1,0,-1,0,-2,2};
        int target = 0;
        List<List<Integer>> ans = new Problem18().fourSum(nums, target);
        System.out.println(ans.size());
    }
}
