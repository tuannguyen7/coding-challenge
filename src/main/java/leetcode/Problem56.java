package leetcode;

import java.util.*;

public class Problem56 {

    // 1. Solution 1 using sorting
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0)
            return new int[0][0];

        LinkedList<int[]> ans = new LinkedList<>();
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        ans.add(intervals[0]);
        for (int[] interval : intervals) {
            int[] last = ans.peekLast();

            if (interval[0] <= last[1]) {
                ans.removeLast();
                int[] newVal = {last[0], Math.max(last[1], interval[1])};
                ans.add(newVal);
            } else {
                ans.add(interval);
            }
        }
        return ans.stream().toArray(int[][]::new);
    }

    // 2. Solution 2 no sorting
    //
    public int[][] mergeSolution2(int[][] intervals) {
        if (intervals.length == 0)
            return new int[0][0];

        LinkedList<int[]> ans = new LinkedList<>();
        return null;
    }

    class TreeNode {
        int start;
        int end;

        public TreeNode(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    // End of solution 2
    public static void main(String[] args) {
        int[][] intervals = {{2,3},{2,2},{3,3},{1,3},{5,7},{2,2},{4,6}};
        new Problem56().merge(intervals);
    }
}
