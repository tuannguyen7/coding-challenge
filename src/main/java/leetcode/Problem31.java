package leetcode;

public class Problem31 {

    public void nextPermutation(int[] nums) {
        for (int i = nums.length - 2; i >= 0; i--) {
            int minIndex = findMinMax(nums, i);
            if (minIndex != -1) {
                swap(nums, i, minIndex);
                break;
            }

            // not found, shift left
            rotate(nums, i);
        }
        System.out.println(1);
    }

    private int findMinMax(int[] nums, int left) {
        int target = nums[left];
        while (left < nums.length) {
            if (nums[left] > target)
                return left;
            left++;
        }

        return -1;
    }

    private void swap(int[] nums, int a, int b) {
        int tmp = nums[a];
        nums[a] = nums[b];
        nums[b] = tmp;
    }

    private void rotate(int[] nums, int begin) {
        int tmp = nums[begin];
        for (int i = begin; i < nums.length - 1; i++)
            nums[i] = nums[i+1];
        nums[nums.length - 1] = tmp;
    }

    public static void main(String[] args){
        //int[] nums = {1, 5, 4, 3};
        //int[] nums = {1, 3, 5, 4};
        int[] nums = {5, 4, 3, 1};
        new Problem31().nextPermutation(nums);
    }
}
