package leetcode;

public class Problem303 {

    long[] sums;
    public Problem303(int[] nums) {
        this.sums = new long[nums.length + 1];
        sums[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i];
        }
    }

    public int sumRange(int left, int right) {
        return (int)(this.sums[right+1] - this.sums[left]);
    }
}
