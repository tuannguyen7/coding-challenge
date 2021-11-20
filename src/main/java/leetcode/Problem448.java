package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int i = 0;
        while (i < nums.length) {
            int n = nums[i];
            while (true) {
                int tmp = n;
                n = nums[n-1];
                nums[tmp-1] = -1;

                if (n == -1) {
                    break;
                }
            }
            i++;
            while (i < nums.length && nums[i] == -1) {
                i++;
            }
        }

        for (i = 0; i < nums.length; i++) {
            if (nums[i] != -1) {
                ans.add(i+1);
            }
        }

        return ans;
    }

    public List<Integer> findDisappearedNumbers_2(int[] nums) {
        int i = 0;
        List<Integer> result = new ArrayList<>();
        while (i < nums.length) {
            int index = -1;
            for (; i < nums.length; i++) {
                if (nums[i] != -1 && nums[nums[i] - 1] != -1) {
                    index = nums[i] - 1;
                    break;
                }
            }
            if (index == -1) break;

            while (nums[index] != -1) {
                int nextIndex = nums[index] - 1;
                nums[index] = -1;
                index = nextIndex;
            }
        }

        for (int j = 0; j < nums.length; j++) {
            if (nums[j] != -1) {
                result.add(j+1);
            }
        }
        return result;
    }
}
