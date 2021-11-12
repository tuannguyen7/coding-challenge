package leetcode;

import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.List;

public class Problem977 {
    public int[] sortedSquares(int[] nums) {
        List<Integer> gt0 = new ArrayList<>();
        List<Integer> lt0 = new ArrayList<>();
        int[] result = new int[nums.length];
        int zeroCount = 0;
        int rIndex = 0;
        for (int num : nums) {
            if (num > 0) {
                gt0.add(num*num);
            } else if(num < 0) {
                lt0.add(num*num);
            } else {
                zeroCount++;
            }
        }
        int lIndex = lt0.size() - 1;
        int gIndex = 0;

        while (rIndex < zeroCount) {
            result[rIndex] = 0;
            rIndex++;
        }

        while (gIndex < gt0.size() && lIndex >= 0) {
            int gVal = gt0.get(gIndex);
            int lVal = lt0.get(lIndex);
            if (gVal < lVal) {
                result[rIndex] = gVal;
                gIndex++;
            } else {
                result[rIndex] = lVal;
                lIndex--;
            }
            rIndex++;
        }

        for (; gIndex < gt0.size(); gIndex++, rIndex++) {
            result[rIndex] = gt0.get(gIndex);
        }

        for (; lIndex >=0; lIndex--, rIndex++) {
            result[rIndex] = lt0.get(lIndex);
        }

        return result;
    }

    public static void main(String[] args) {
        var a = new Problem977();
        int[] nums = {-4,-1,0,3,10};
        //int[] nums = {-1};
        LoggerUtil.INFO("{}", a.sortedSquares(nums));
    }
}
