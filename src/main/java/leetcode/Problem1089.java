package leetcode;

import utils.LoggerUtil;

public class Problem1089 {

    public void duplicateZeros(int[] arr) {
        int zeroCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zeroCount++;
            }
        }
        int sourceIndex = arr.length - 1;
        int destIndex = sourceIndex + zeroCount;

        while (sourceIndex >= 0 && sourceIndex < destIndex) {
            if (arr[sourceIndex] == 0) {
                if (destIndex < arr.length) {
                    arr[destIndex] = 0;
                }
                destIndex--;
            }
            if (destIndex < arr.length) {
                arr[destIndex] = arr[sourceIndex];
            }
            sourceIndex--;
            destIndex--;
        }
    }

    public static void main(String[] args) {
        var a = new Problem1089();
        int[] arr = {8,4,5,0,0,0,0,7};
        a.duplicateZeros(arr);
        LoggerUtil.INFO("{}", arr);
    }
}
