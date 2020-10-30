package leetcode;


public class Problem456 {

    // Solution1: O(n^3)
    public boolean find132pattern_1(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                for (int k = j + 1; k < nums.length; k++) {
                    if (nums[i] < nums[k] && nums[k] < nums[j])
                        return true;
                }
            }
        }
        return false;
    }

    // Solution2: O(n^2)
    public boolean find132pattern_2(int[] nums) {
        int ai = nums[0];
        int prevAj = nums[0];
        for (int j = 1; j < nums.length - 1; j++) {
            ai = Math.min(ai, prevAj);
            int aj = nums[j];
            for (int k = j + 1; k < nums.length; k++) {
                if (nums[k] < aj && nums[k] > ai)
                    return true;
            }
            prevAj = aj;
        }
        return false;
    }

    // Solution 2.1: O(n^2) + some improvement
    public boolean find132pattern_3(int[] nums) {
        int ai = nums[0];
        int prevAj = nums[0];
        for (int j = 1; j < nums.length - 1; j++) {
            ai = Math.min(ai, prevAj);
            int aj = nums[j];
            if (aj <= prevAj) {
                prevAj = aj;
                continue;
            }
            for (int k = j + 1; k < nums.length; k++) {
                if (nums[k] < aj && nums[k] > ai)
                    return true;
            }
            prevAj = aj;
        }
        return false;
    }
}
