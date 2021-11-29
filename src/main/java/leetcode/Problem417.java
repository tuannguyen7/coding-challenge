package leetcode;

import utils.LoggerUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem417 {

    private int[][] adds = {{1, 0}, {0, 1}, {0, -1}, {-1, 0}};
    // cach1
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        List<List<Integer>> ans = new ArrayList<>();
        for (int r = 0; r < heights.length; r++) {
            for (int c = 0; c < heights[0].length; c++) {
                Integer[][] islands = new Integer[heights.length][heights[0].length];
                boolean[][] visited = new boolean[heights.length][heights[0].length];
                for (int vi = 0; vi < heights.length; vi++)
                    Arrays.fill(visited[vi], false);
                if (recursive(heights, visited, islands, r, c) == 3) {
                    ans.add(Arrays.asList(r, c));
                }
            }
        }
        return ans;
    }

    public int recursive(int[][] heights, boolean[][] visited, Integer[][] islands, int r, int c) {
        if (islands[r][c] != null)
            return islands[r][c];
        if (visited[r][c]) {
            return 0;
        }
        int val = 0;
        visited[r][c] = true;
        if (r == 0 || c == 0) {
            val |= 1;
        }
        if (r == heights.length - 1 || c == heights[0].length - 1) {
            val |= 2;
        }

        for (int[] add : adds) {
            if (val == 3) break;
            int nextR = r + add[0];
            int nextC = c + add[1];
            if (nextR < 0 || nextC < 0 || nextR == heights.length || nextC == heights[0].length) {
                continue;
            }
            if (heights[r][c] >= heights[nextR][nextC]) {
                val |= recursive(heights, visited, islands, nextR, nextC);
            }
        }

        islands[r][c] = val;
        return val;
    }

    public static void main(String[] args) {
        Problem417 app = new Problem417();
        //int[][] heights = {{2,1},{1,2}};
        int[][] heights = {{1,2,2,3,5},{3,2,3,4,4},{2,4,5,3,1},{6,7,1,4,5},{5,1,1,2,4}};
    }
}
