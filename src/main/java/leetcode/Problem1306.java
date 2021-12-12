package leetcode;

import java.util.Arrays;

public class Problem1306 {

    public boolean canReach(int[] arr, int start) {
        boolean[] visited = new boolean[arr.length];
        Arrays.fill(visited, false);
        return can(arr, start, visited);
    }

    private boolean can(int[] arr, int index, boolean[] visited) {
        if (index >= arr.length || index < 0) return false;
        if (visited[index]) return false;
        if (arr[index] == 0) return true;
        visited[index] = true;
        return can(arr, index - arr[index], visited) || can(arr, index + arr[index], visited);
    }

    public static void main(String[] args) {
        var app = new Problem1306();
        int[] arr = {4,2,3,0,3,1,2};
        int start = 5;
        System.out.println(app.canReach(arr, start));
    }
}
