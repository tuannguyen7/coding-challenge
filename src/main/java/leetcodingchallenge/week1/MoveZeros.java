package leetcodingchallenge.week1;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class MoveZeros {
    public void moveZeroes(int[] nums) {
        List<Integer> list = new LinkedList<>();
        int zeroCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                list.add(i);
                for (; i < nums.length && nums[i] == 0; i++);
                list.add(i);
            }
        }
        list.add(nums.length);

        for (int i = 1; i < list.size(); i += 2) {
            int low = list.get(i);
            int high = list.get(i + 1);
            zeroCount += list.get(i) - list.get(i - 1);
            shiftLeftNTimes(nums, zeroCount, low, high - 1);
        }

        for (int i = nums.length - 1; zeroCount > 0; i--, zeroCount--) {
            nums[i] = 0;
        }
    }

    public void shiftLeftNTimes(int[] nums, int times, int startIndex, int endIndex) {
        for (int i = startIndex; i <= endIndex; i++) {
            nums[i - times] = nums[i];
        }
    }
}
