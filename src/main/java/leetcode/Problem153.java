package leetcode;

public class Problem153 {
    // Note: no duplicate in array
    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        while (lo < hi) {
            int mid = (lo + hi)/2;
            if (nums[mid] >= nums[lo] && nums[mid] <= nums[hi]) {
                return nums[lo];
            }
            if (nums[mid] < nums[lo]) {
                hi = mid;
                continue;
            }

            if (nums[mid] > nums[hi]) {
                lo = mid + 1;
                continue;
            }
        }
        return Math.min(nums[lo], nums[hi]);
    }

    public static void main(String[] args) {
        //int[] nums = {5,1,2};
        int[] nums = {3, 1, 2};
        int min = new Problem153().findMin(nums);
        System.out.println(min);
    }
}
