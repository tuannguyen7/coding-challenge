package leetcode;

public class Problem334 {

    public boolean increasingTriplet(int[] nums) {
        int i = 0;
        while (i < nums.length - 2) {
            for (; i < nums.length - 2; i++) {
                if (nums[i+1] > nums[i]) {
                    break;
                }
            }

            for (int j = i + 1; j < nums.length - 1; j++) {
            }

        }
        return false;
    }
}
