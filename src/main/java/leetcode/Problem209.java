package leetcode;

public class Problem209 {

    public int minSubArrayLen(int target, int[] nums) {
        int l = 0;
        int r = l;
        int sum = 0;
        int minLength = nums.length + 1;

        while (l < nums.length) {
            for (; r < nums.length; r++) {
                sum += nums[r];
                if (sum >= target) {
                    break;
                }
            }
            if (sum < target) { // not found any
                break;
            }
            minLength = Math.min(minLength, r-l+1);

            l++;
            while (true) {
                sum -= nums[l-1];
                if (sum < target) {
                    break;
                }
                minLength = Math.min(minLength, r-l+1);
                l++;
            }
        }

        if (minLength == nums.length + 1) {
            return 0; // not found
        }
        return minLength;
    }

    public static void main(String[] args) {
        var app = new Problem209();
        int target = 7;
        int[] nums = {2,3,1,2,4,3};
        System.out.println(app.minSubArrayLen(target, nums));
    }
}
