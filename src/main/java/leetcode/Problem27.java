package leetcode;

public class Problem27 {

    public int removeElement(int[] nums, int val) {
        int backIndex = nums.length - 1;
        int frontIndex = 0;
        while (backIndex > frontIndex) {
            if (nums[frontIndex] != val) {
                frontIndex++;
            } else {
                // swap front and back
                int tmp = nums[frontIndex];
                nums[frontIndex] = nums[backIndex];
                nums[backIndex]  = tmp;
                backIndex--;
            }
        }

        if (nums[frontIndex] != val)
            return frontIndex + 1;
        else
            return frontIndex;

    }

    public int removeElement2(int[] nums, int val) {
        int len = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != val) {
                nums[len] = nums[i];
                len++;
            }
        }
        return len;
    }

    public static void main(String[] args) {
    }
}
