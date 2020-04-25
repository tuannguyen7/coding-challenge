package leetcode;

import java.time.LocalDateTime;

public class Problem162 {
    // Solution1.
    public int findPeakElement(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (hi - lo > 1) {
            int mid = lo + (hi - lo)/2;
            long prev = mid == 0 ? Long.MIN_VALUE : nums[mid - 1];
            long cur = nums[mid];
            long next = mid == (nums.length - 1) ? Long.MAX_VALUE : nums[mid + 1];

            if (cur > prev && cur > next)
                return mid;

            if (cur > prev) { // next > cur is redundant
                lo = mid + 1;
            } else if (cur < prev) {
                hi = mid - 1;
            }
        }
        return nums[lo] > nums[hi] ? lo : hi;
    }

    // Solution2
    public int findPeakElement2(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int ans = lo;   // ans = hi also work
        while (hi > lo) {
            int mid = lo + (hi - lo)/2;
            long prev = mid == 0 ? Long.MIN_VALUE : nums[mid - 1];
            long cur = nums[mid];
            long next = mid == (nums.length - 1) ? Long.MAX_VALUE : nums[mid + 1];

            if (cur > prev && cur > next)
                return mid;

            if (cur > prev) { // next > cur is redundant
                lo = mid + 1;
                ans = lo;
            } else if (cur < prev) {
                hi = mid - 1;
                ans = hi;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        //int[] nums = {1,2,1,3,5,6,4};
        int[] nums = {2,1};
        int peak = new Problem162().findPeakElement2(nums);
        System.out.println(peak);
    }

    // 1, 5  -> mid = 3
    //
}
