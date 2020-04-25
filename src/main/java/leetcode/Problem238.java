package leetcode;

public class Problem238 {
    // Solution1. 2 auxiliary arrays
    public int[] productExceptSelf(int[] nums) {
        int[] auxiliaryArr1 = new int[nums.length + 1]; // a[i] = nums[0] * nums[1] * ... * nums[i]
        int[] auxiliaryArr2 = new int[nums.length + 1]; // a[i] = nums[i] * nums[i+1] * ... *nums[n]
        int[] result = new int[nums.length];

        auxiliaryArr1[0] = 1;
        auxiliaryArr2[auxiliaryArr2.length - 1] = 1;

        for (int i = 0; i < nums.length; i++)
            auxiliaryArr1[i] = auxiliaryArr1[i-1]*nums[i];
        for (int i = nums.length - 1; i >= 0; i--)
            auxiliaryArr2[i] = auxiliaryArr2[i+1]*nums[i];

        for (int i = 0; i < result.length; i++) {
            result[i] = auxiliaryArr1[i] * auxiliaryArr2[i + 1];
        }

        return result;
    }

    // Solution2. no auxiliary array
    public int[] productExceptSelf2(int[] nums) {
        int[] result = new int[nums.length];

        result[0] = 1;

        for (int i = 1; i < nums.length; i++) {
            result[i] = result[i - 1]*nums[i - 1];
        }

        int mul = 1;
        for (int i = nums.length - 2; i >= 0; i--) {
            mul *= nums[i + 1];
            result[i] = result[i]*mul;
        }

        return result;
    }
}
