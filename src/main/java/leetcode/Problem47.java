package leetcode;

import java.util.*;
import java.util.stream.Collectors;

public class Problem47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Map<Integer, Integer> count = new HashMap<>();
        for (int num : nums) {
            if (!count.containsKey(num))
                count.put(num, 0);
            count.put(num, count.get(num) + 1);
        }
        List<List<Integer>> ans = solve(count, new LinkedList<>(), nums.length);
        System.out.println(ans.size());
        return ans;
    }

    public List<List<Integer>> solve(Map<Integer, Integer> map, LinkedList<Integer> cur, int maxSize) {
        if (cur.size() == maxSize) {
            return Arrays.asList(cur.stream().collect(Collectors.toList()));
        }
        List<List<Integer>> ans = new LinkedList<>();
        for (int key : map.keySet()) {
            int count = map.get(key);
            if (count > 0) {
                map.put(key, count - 1);
                cur.push(key);
                ans.addAll(solve(map, cur, maxSize));
                map.put(key, count);
                cur.pop();
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        new Problem47().permuteUnique(nums);
    }
}
