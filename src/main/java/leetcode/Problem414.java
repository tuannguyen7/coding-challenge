package leetcode;

public class Problem414 {

    public int thirdMax(int[] nums) {
        int max1 = maxNotExceed(nums, Integer.MAX_VALUE);
        if (nums.length < 3) return max1;
        Integer max2 = maxNotExceed(nums, max1 - 1);
        if (max2 == null) return max1;
        Integer max3 = maxNotExceed(nums, max2 - 1);
        return max3 == null ? max1 : max3;
    }

    private Integer maxNotExceed(int[] nums, int maximum) {
        Integer curMax = null;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > maximum) {
                continue;
            }
            if (curMax == null) {
                curMax = nums[i];
            } else {
                curMax = Math.max(curMax, nums[i]);
            }
        }
        return curMax;
    }
}
