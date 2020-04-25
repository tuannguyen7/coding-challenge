package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Problem78 {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new LinkedList<>();
        for (int size = 0; size <= nums.length; size++) {
            ans.addAll(createSubsetsOfSize(nums, size, 0, new LinkedList<>()));
        }
        return ans;
    }

    public List<List<Integer>> createSubsetsOfSize(int[] nums, int size, int curIndex, LinkedList<Integer> curSubset) {
        if (size == 0)
            return Arrays.asList(curSubset.stream().collect(Collectors.toList()));
        if (nums.length - curIndex < size)
            return Collections.emptyList();
        List<List<Integer>> result = new LinkedList<>();
        for (int i = curIndex; i <= nums.length - size; i++) {
            curSubset.add(nums[i]);
            result.addAll(createSubsetsOfSize(nums, size - 1, i + 1, curSubset));
            curSubset.removeLast();
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        new Problem78().subsets(nums);
    }
}
