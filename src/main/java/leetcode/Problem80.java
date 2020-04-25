package leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem80 {
    private static final Logger log = LoggerFactory.getLogger(Problem80.class);
    public int removeDuplicates(int[] nums) {
        if (nums.length == 0)
            return 0;
        int count = 1;
        int prevNum = nums[0];
        int dupCount = 0;
        for (int i = 1; i < nums.length - dupCount; i++) {
            if (nums[i] == prevNum) {
                count++;
            } else {
                prevNum = nums[i];
                if (count > 2) {
                    shiftLeft(nums, i - (count - 2), (count - 2));
                    dupCount += (count - 2);
                    i -= (count - 2);
                }
                count = 1;
            }
        }
        if (count > 2) {
            dupCount += (count - 2);
        }
        log.info("{} and size {}", nums, nums.length - dupCount);
        return nums.length - dupCount;
    }

    public void shiftLeft(int[] nums, int position, int steps) {
        for (int i = position; i < nums.length - steps; i++) {
            nums[i] = nums[i + steps];
        }
    }

    public static void main(String[] args) {
        //int[] nums = {1,1,1,2,2,3};
        int[] nums = {0,0,1,1,1,1,2,3,3};
        new Problem80().removeDuplicates(nums);
    }
}
