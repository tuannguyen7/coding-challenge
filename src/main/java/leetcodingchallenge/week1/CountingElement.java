package leetcodingchallenge.week1;

import java.util.Arrays;

public class CountingElement {
    public int countElements(int[] arr) {
        Arrays.sort(arr);
        int ans = 0;
        int count = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[i - 1]) count++;
            else if (arr[i] == arr[i-1] + 1) {
                ans += count;
                count = 1;
            }
            else
                count = 1;
        }
        return ans;
    }
}
