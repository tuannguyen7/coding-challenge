package leetcode;

public class Problem81 {
    public boolean search(int[] nums, int target) {
        if (nums.length == 0)
            return false;
        return binarySearch(nums, 0, nums.length - 1, target);
    }

    public boolean binarySearch(int[] nums, int start, int end, int target) {
        if (end < start)
            return false;
        int mid = (start + end)/2;
        if (nums[mid] == target)
            return true;

        if (nums[mid] == nums[start]) {
            return binarySearch(nums, mid + 1, end, target) || binarySearch(nums, start, mid - 1, target);
        } else if (nums[mid] >= nums[start]) {
            if (target >= nums[start] && target < nums[mid]){
                return binarySearch(nums, start, mid - 1, target);
            }
            return binarySearch(nums, mid + 1, end, target);
        } else {
            if (target >= nums[start] || target < nums[mid]){
                return binarySearch(nums, start, mid - 1, target);
            }
            return binarySearch(nums, mid + 1, end, target);
        }
    }
}
