package leetcode;

import java.util.Arrays;
import java.util.Comparator;

public class Problem1288 {

    public int removeCoveredIntervals(int[][] intervals) {
        int[][] finalResult = new int[intervals.length][2];
        int finalResultSize = 0;
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0])
                    return Integer.compare(o1[0], o2[0]);
                return Integer.compare(o2[1], o1[1]);
            }
        });
        for (int i = 0; i < intervals.length; i++) {
            int a = intervals[i][0];
            int b = intervals[i][1];
            boolean satisfiedToAdd = true;
            for (int j = 0; j < finalResultSize; j++) {
                if (a >= finalResult[j][0] && b <= finalResult[j][1]) {
                    satisfiedToAdd = false;
                    break;
                }
            }
            if (satisfiedToAdd) {
                finalResult[finalResultSize] = intervals[i];
                finalResultSize++;
            }
        }
        return finalResultSize;
    }

    public static void main(String[] args) {
        int[][] intervals = {{3, 10}, {4, 10}, {5, 11}};
        System.out.println(new Problem1288().removeCoveredIntervals(intervals));
    }
}
