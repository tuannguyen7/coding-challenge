package leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Problem442 {

    private static final Logger log = LoggerFactory.getLogger(Problem442.class);

    // Solution1:
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> result = new LinkedList<>();
        for (int i = 0; i < nums.length; i++) {
            int index = Math.abs(nums[i]) - 1;
            if (nums[index] < 0) {
                result.add(index + 1);
            } else {
                nums[index] *= -1;
            }
        }
        return result;
    }

    // Solution 2
    public List<Integer> findDuplicates_2(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            int prev;
            int cur = nums[i]; // cur always != -1
            while (true) {
                prev = cur;
                cur = nums[cur-1];
                nums[prev-1] = -1; // mark as visited

                if (cur == -1) {
                    ans.add(prev);
                    break;
                }

                //
                if (prev-1 <= i) {
                    break;
                }
            }
            i++;
            while (i < nums.length && nums[i] == -1) {
                i++;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {4,3,2,7,8,2,3,1};
        var result =new Problem442().findDuplicates(nums);
        log.info("{}", result);
    }
}
