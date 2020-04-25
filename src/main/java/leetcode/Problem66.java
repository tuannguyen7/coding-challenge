package leetcode;

import java.util.Arrays;

public class Problem66 {

    public int[] plusOne(int[] digits) {
        int addition = 1;
        int lastIndex = digits.length - 1;
        while (lastIndex >= 0 && addition > 0) {
            int afterPlus = (digits[lastIndex] + 1);
            digits[lastIndex] = afterPlus%10;
            addition = afterPlus/10;
            lastIndex--;
        }
        if (addition == 0)
            return digits;
        int[] result = new int[digits.length + 1];
        result[0] = 1;
        for (int i = 0; i < digits.length; i++)
            result[i+1] = digits[i];
        return result;
    }

    public static void main(String[] args) {

    }
}
