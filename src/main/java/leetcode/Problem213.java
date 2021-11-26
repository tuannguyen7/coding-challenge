package leetcode;

public class Problem213 {

    public int rob(int[] nums) {
        if (nums.length == 1) {
            return nums[0];
        }
        int max1 = rob(nums, 0, nums.length - 2);
        int max2 = rob(nums, 1, nums.length - 1);
        return Math.max(max1, max2);
    }

    public int rob(int[] nums, int startIndex, int endIndex) {
        int curMax = nums[startIndex];
        int prevMax = nums[startIndex];
        int pPrevMax = 0;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            curMax = Math.max(prevMax, pPrevMax + nums[i]);
            pPrevMax = prevMax;
            prevMax = curMax;
        }
        return curMax;
    }

    public static void main(String[] args) {
        var app = new Problem213();
        int nums[] = {2,3,2};
        System.out.println(app.rob(nums));
    }
}
