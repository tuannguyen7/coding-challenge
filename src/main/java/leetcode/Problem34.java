package leetcode;

public class Problem34 {

    public int[] searchRange(int[] nums, int target) {
        int a = findEqual(nums, target);
        if (a == -1)
            return new int[] {-1, -1};
        int lower = a;
        int upper = a;
        for (;upper < nums.length && nums[upper] == target; upper++);
        for (;lower >= 0 && nums[lower] == target; lower--);

        return new int[] {lower + 1, upper};
    }

    public int findEqual(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi)/2;
            if (nums[mid] < target) {
                lo = mid + 1;
            } else if (nums[mid] > target) {
                hi = mid;
            } else {
                return mid;
            }
        }

        return -1;
    }

    /**          Other implementation          */
    public int[] searchRange2(int[] nums, int target) {
        int lower = findLowerBound(nums, target);
        int upper = findLowerBound(nums, target + 1);
        return new int[] {lower, upper};
    }

    public int findLowerBound(int[] nums, int target) {
        int lo = 0;
        int hi = nums.length;
        while (lo < hi) {
            int mid = (lo + hi) / 2;
            if (nums[mid] < target)
                lo = mid + 1;
            else
                hi = mid;
        }

        return lo;
    }

    public static void main(String[] args) {
        //int[] nums = {5,7,7,8,8,10};
        int[] nums = {2,2,2,2,2,2};
        int target = 2;
    }
}
