package leetcode;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Problem560 {

    // Solution1. Brute force
    public int subarraySum(int[] nums, int k) {
        int ans = 0;
        for (int size = 1; size <= nums.length; size++) {
            int sum = 0;
            for (int tmp = 0; tmp < size; tmp++)
                sum += nums[tmp];
            if (sum == k)
                ans++;
            for (int m = size; m < nums.length; m++) {
                sum = sum - nums[m - size] + nums[m];
                if  (sum == k)
                    ans++;
            }
        }
        return ans;
    }

    // Solution2. presum + hashtable
    public int subarraySum2(int[] nums, int k) {
        int ans = 0;
        int presums[] = new int[nums.length];
        Map<Integer, Integer> desiredValue = new HashMap<>();
        presums[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            presums[i] = presums[i - 1] + nums[i];
        }

        desiredValue.put(k, 1);
        for (int presum : presums) {
            if (desiredValue.containsKey(presum)) {
                ans += desiredValue.get(presum);
            }
            if (desiredValue.containsKey(presum + k)) {
                desiredValue.put(presum + k, desiredValue.get(presum + k) + 1);
            } else {
                desiredValue.put(presum + k, 1);
            }
        }

        return ans;
    }
}
