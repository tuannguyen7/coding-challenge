package leetcode;


import java.util.Arrays;

public class Problem274 {
    public int hIndex(int[] citations) {
        Arrays.sort(citations);
        if (citations[0] >= citations.length) {
            return citations.length;
        }
        for (int i = 1; i < citations.length; i++) {
            int x = citations.length - i;
            if (citations[i] >= x && citations[i-1] <= x)
                return x;
        }
        return 0;
    }
}
