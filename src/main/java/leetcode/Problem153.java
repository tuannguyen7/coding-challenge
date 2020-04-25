package leetcode;

public class Problem153 {
    // Note: no duplicate in array
    public int findMin(int[] nums) {
        int lo = 0;
        int hi = nums.length - 1;
        int lastValue = nums[nums.length - 1];
        int minIndex = nums[lo] < nums[hi] ? lo : hi;

        while (hi >= lo) {
            int mid = lo + (hi - lo)/2;
            if (nums[mid] < lastValue) {
                minIndex = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return nums[minIndex];
    }

    public static void main(String[] args) {
        //int[] nums = {5,1,2};
        int[] nums = {6, 5};
        int min = new Problem153().findMin(nums);
        System.out.println(min);
    }
}
