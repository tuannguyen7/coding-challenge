package leetcode;

public class Problem45 {


    // solution 1: backtracking
    public int jump(int[] nums) {
        //return jump_1(nums, 0);
        return jump_2(nums);
    }

    private int jump_1(int[] num, int curIndex) {
        if (curIndex == num.length - 1)
            return 0;
        int minStep = 999999;
        for (int ns = 1; ns <= num[curIndex]; ns++) { // next step
            if (curIndex + ns >= num.length) break;
            minStep = Math.min(minStep, jump_1(num, curIndex + ns));
        }
        return 1 + minStep;
    }

    // solution2: dynamic programming
    public int jump_2(int[] nums) {
        int[] jumps = new int[nums.length];
        for (int i = 0; i < jumps.length; i++)
            jumps[i] = 999999;
        jumps[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            for (int ns = 1; ns <= nums[i]; ns++) {
                if (i + ns >= jumps.length) break;
                jumps[i + ns] = Math.min(jumps[i + ns], jumps[i] + 1);
            }
        }

        return jumps[jumps.length - 1];
    }

    // solution 3: greedy
    public int jump_3(int[] nums) {
        int count = 0;
        for (int i = 0; i < nums.length - 1;) {
            if (nums[i] + i >= nums.length - 1) { // can jump to the last index
                return count + 1;
            }
            int max = -1;
            int maxIndex = 1;
            for (int add = 1; add <= nums[i] && i + add < nums.length; add++) {
                if (i + add + nums[i + add] >= max) {
                    max = i + add + nums[i + add];
                    maxIndex = add;
                }
            }
            count += 1;
            i += maxIndex;
        }
        return count;
    }

    public static void main(String[] args) {
        int[] nums = {2,3,5,9,0,9,7,2,7,9,1,7,4,6,2,1,0,0,1,4,9,0,6,3};
        Problem45 p = new Problem45();
        //System.out.println(p.jump(nums));
        System.out.println(p.jump_3(nums));
    }
}
