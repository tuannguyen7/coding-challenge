package leetcode;

public class Problem33 {

    // Solution 1. Find rotated index
    public int search(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        int rotate = findRotatedIndex(nums, 0, nums.length, nums[0]);
        return binarySearch(nums, rotate, nums.length + rotate, target);
    }

    public int binarySearch(int[] nums, int start, int end, int val) {
        if (end <= start)
            return -1;
        int mid = (start + end)/2;
        int realMidIndex = mid % nums.length;
        if (nums[realMidIndex] == val)
            return realMidIndex;
        else if (nums[realMidIndex] > val)
            return binarySearch(nums, start, mid, val);
        else
            return binarySearch(nums, mid + 1, end, val);

    }

    public int findRotatedIndex(int[] nums, int start, int end, int firstVal) {
        if (end <= start)
            return start % nums.length;

        int mid = (start + end)/2;
        if (nums[mid] > firstVal)
            return findRotatedIndex(nums, mid + 1, end, firstVal);
        else
            return findRotatedIndex(nums, start, mid, firstVal);
    }

    // Solution 2
    public int search2(int[] nums, int target) {
        if (nums.length == 0)
            return -1;
        return binarySearch2(nums, 0, nums.length - 1, target);
    }

    public int binarySearch2(int[] nums, int start, int end, int target) {
        if (end < start)
            return -1;
        int mid = (start + end)/2;
        if (nums[mid] == target)
            return mid;
        if (nums[mid] >= nums[start]) {
            if (target >= nums[start] && target < nums[mid]){
                return binarySearch2(nums, start, mid - 1, target);
            }
            return binarySearch2(nums, mid + 1, end, target);
        } else {
            if (target >= nums[start] || target < nums[mid]){
                return binarySearch2(nums, start, mid - 1, target);
            }
            return binarySearch2(nums, mid + 1, end, target);
        }
    }

    public static void main(String[] args) {
        //int[] nums = {4,5,6,7,0,1,2};
        //int[] nums = {1,2,3,4,5,6,7,0};
        int[] nums = {1, 3};
        //System.out.println(new Problem33().search(nums, 3));
        System.out.println(new Problem33().search2(nums, 3));
    }
}
