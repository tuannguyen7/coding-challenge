package leetcode;

public class Problem941 {
    public boolean validMountainArray(int[] arr) {
        int i = 1;
        for (; i < arr.length; i++) {
            if (arr[i] == arr[i-1]) return false;
            if (arr[i] < arr[i-1]) break;
        }

        if (i == arr.length || i == 1) return false;
        for (; i < arr.length - 1; i++) {
            if (arr[i] <= arr[i+1]) return false;
        }

        return true;
    }
}
