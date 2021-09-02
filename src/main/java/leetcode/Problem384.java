package leetcode;

import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Problem384 {

    private int[] nums;

    public Problem384(int[] nums) {
        this.nums = nums;
    }

    /** Resets the array to its original configuration and return it. */
    public int[] reset() {
        return this.nums;
    }

    /** Returns a random shuffling of the array. */
    public int[] shuffle() {
        int[] shuffled = Arrays.copyOf(nums, nums.length);
        for (int i = 0; i < shuffled.length - 1; i++) {
            int randomIndex = ThreadLocalRandom.current().nextInt(i, shuffled.length); // random int between [i, len(nums))
            int tmp = shuffled[i];
            shuffled[i] = shuffled[randomIndex];
            shuffled[randomIndex] = tmp;
        }
        return shuffled;
    }
}
