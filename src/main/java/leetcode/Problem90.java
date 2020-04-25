package leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class Problem90 {

    private static final Logger log = LoggerFactory.getLogger(Problem90.class);
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        if (nums.length == 0)
            return Collections.emptyList();
        List<Integer> numbers = new ArrayList<>();
        List<Integer> counts = new ArrayList<>();
        Arrays.sort(nums);
        numbers.add(nums[0]);
        counts.add(1);
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[i-1]) {
                numbers.add(nums[i]);
                counts.add(1);
            } else {
                counts.set(counts.size() - 1, counts.get(counts.size() - 1) + 1);
            }
        }

        log.info("numbers {}, counts {}", numbers, counts);
        List<List<Integer>> ans = new LinkedList<>();
        for (int len = 0; len <= nums.length; len++) {
            ans.addAll(subsetsWithDup(numbers, counts, len, 0, new LinkedList<>()));
        }
        log.info("Solution 90: {}", ans);
        return ans;
    }

    public List<List<Integer>> subsetsWithDup(List<Integer> numbers, List<Integer> counts, int len, int curIndex, List<Integer> curSubset) {
        if (curSubset.size() == len)
            return Arrays.asList(curSubset.stream().collect(Collectors.toList()));
        if (curIndex == numbers.size() || curSubset.size() > len)
            return Collections.emptyList();

        List<List<Integer>> ans = new LinkedList<>();

        for (int j = 0; j <= counts.get(curIndex); j++) {
            int n1 = 0, n2 = 0;
            while (n1 < j) {
                curSubset.add(numbers.get(curIndex));
                n1++;
            }
            ans.addAll(subsetsWithDup(numbers, counts, len, curIndex + 1, curSubset));
            while (n2 < j) {
                curSubset.remove(curSubset.size() - 1);
                n2++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1,2,2};
        new Problem90().subsetsWithDup(nums);
    }
}
