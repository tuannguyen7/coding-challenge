package leetcode;

public class Problem152 {

    public int maxProduct(int[] nums) {
        int maxNegative[] = new int[nums.length];
        int maxPositive[] = new int[nums.length];
        int maxToTal = nums[0];

        maxNegative[0] = nums[0] < 0 ? nums[0] : 1; // 1 mean not found
        maxPositive[0] = nums[0] > 0 ? nums[0] : -1; // -1 mean not found
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > 0) {
                if (maxPositive[i-1] == -1) { // not found max positive
                    maxPositive[i] = nums[i];
                    // -> must found max negative
                    maxNegative[i] = maxNegative[i-1] * nums[i];
                } else { // found max positive
                    maxPositive[i] = maxPositive[i-1] * nums[i];
                    if (maxNegative[i] == 1) { // not found max Negative
                        maxNegative[i] = 1;
                    } else {
                        maxNegative[i] = maxNegative[i-1] * nums[i];
                    }
                }

            } else if (nums[i] < 0) {
                if (maxNegative[i-1] == 1) { // not found max negative
                    // not found max negative means maxPositive must be found
                    maxNegative[i] = maxPositive[i-1] * nums[i];
                    maxPositive[i] = -1;
                } else { // found max negative
                    if (maxPositive[i] == -1) { // not found max positive
                        maxNegative[i] = nums[i];
                    } else {
                        maxNegative[i] = maxPositive[i-1] * nums[i];
                    }
                    maxPositive[i] = maxNegative[i-1] * nums[i];
                }
            } else { // nums[i] = 0
                maxNegative[i] = 1;
                maxPositive[i] = -1;
                maxToTal = Math.max(0, maxToTal);
            }
            if (maxPositive[i] != -1) { // found max positive
                maxToTal = Math.max(maxPositive[i], maxToTal);
            }
        }

        return maxToTal;
    }

    public static void main(String[] args) {
        var app = new Problem152();
        //int nums[] = {2,3,-2,4};
        //int nums[] = {-2,0,-1};
        //int nums[] = {-2,-3};
        //int nums[] = {3,-1,4};
        int nums[] = {7,-2,-4};
        System.out.println(app.maxProduct(nums));
    }
}
