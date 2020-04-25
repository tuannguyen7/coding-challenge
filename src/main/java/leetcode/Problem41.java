package leetcode;

public class Problem41 {
    public int firstMissingPositive(int[] nums) {
        boolean[] found = new boolean[nums.length];
        for (int num : nums) {
            if (num < 1 || num > nums.length)
                continue;
            found[num-1] = true;
        }
        for (int i = 0; i < found.length;i++) {
            if (!found[i])
                return i+1;
        }
        return nums.length+1;
    }

    // constant space
    public int firstMissingPositive2(int[] nums) {
        int magic = 9999999;
        int newLastIndex = makeArrayContainValidElements(nums);
        int starting = 0;

        while (starting <= newLastIndex) {
            int cur = nums[starting] - 1;
            for (int i = starting; i <= newLastIndex; i++) {
                if (nums[cur] == magic || nums[cur] <= 0 || nums[cur] > nums.length) {
                    starting++;
                    break;
                }
                int tmp = nums[cur];
                nums[cur] = magic;
                cur = tmp - 1;
            }

            for (; starting <= newLastIndex && nums[starting] == magic; starting++);
        }

        for (int i = 0; i < nums.length;i++) {
            if (nums[i] != magic)
                return i + 1;
        }
        return nums.length+1;
    }

    // return
    public int makeArrayContainValidElements(int[] nums) {
        int validIndex = nums.length;
        for (int i = 0; i < validIndex;) {
            if (nums[i] <= 0 || nums[i] > nums.length) {
                validIndex--;
                int tmp = nums[validIndex];
                nums[validIndex] = nums[i];
                nums[i] = tmp;
            } else {
                i++;
            }
        }
        return validIndex - 1;
    }

    public static void main(String[] args) {
        //int[] nums = {1,2,0};
        //int[] nums = {1};
        //int[] nums = {3, 4, -1, 1};
        //int[] nums = {7,8,9,11,12};
        int[] nums = {-1,4,2,1,9,10};
        System.out.println(new Problem41().firstMissingPositive2(nums));
    }
}
