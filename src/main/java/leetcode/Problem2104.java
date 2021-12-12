package leetcode;

public class Problem2104 {

    public long subArrayRanges(int[] nums) {
        int[][] maxes = new int[nums.length][nums.length];
        int[][] mines = new int[nums.length][nums.length];
        maxes[0][0] = nums[0];
        mines[0][0] = nums[0];
        long totalSum = 0;
        for (int i = 0; i < nums.length-1; i++) {
            maxes[i][i] = nums[i];
            mines[i][i] = nums[i];
            for (int j = i + 1; j < nums.length; j++) {
                maxes[i][j] = Math.max(maxes[i][j-1], nums[j]);
                mines[i][j] = Math.min(mines[i][j-1], nums[j]);
            }
        }
        for (int i = 0; i < nums.length-1; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                totalSum += maxes[i][j] - mines[i][j];
            }
        }

        return totalSum;
    }

    public static void main(String[] args) {
        var app = new Problem2104();
        int[] nums = {1, 2, 3};
        System.out.println(app.subArrayRanges(nums));
    }
}
