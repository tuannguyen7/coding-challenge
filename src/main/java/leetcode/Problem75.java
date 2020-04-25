package leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem75 {

    private static final Logger log = LoggerFactory.getLogger(Problem75.class);
    public void sortColors(int[] nums) {
        // red = 1, white = 2, blue = 3
        int endRedIndex = 0, endWhiteIndex = 0, endBlueIndex = 0;
        // endBlueIndex is not necessary, endBlueIndex always equals to iterator index (i)
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 2) {
                continue;
            }
            if (nums[i] == 1) {
                swap(nums, i, endWhiteIndex);
                endWhiteIndex++;
            }
            if (nums[i] == 0) {
                swap(nums, i, endWhiteIndex);
                swap(nums, endWhiteIndex, endRedIndex);
                endRedIndex++;
                endWhiteIndex++;
            }
        }
        log.info("after sorting {}", nums);
    }

    public void swap(int[] nums , int index1, int index2) {
        int tmp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = tmp;
    }

    public static void main(String[] args) {
        int[] nums = {2,0,2,1,1,0};
        new Problem75().sortColors(nums);
    }
}
