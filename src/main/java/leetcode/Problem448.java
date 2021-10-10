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
}
