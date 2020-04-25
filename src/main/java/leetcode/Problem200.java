package leetcode;

import java.util.*;

public class Problem200 {

    public int numIslands(char[][] grid) {
        Queue<int[]> vertexes = new LinkedList<>();
        int islandCount = 0;

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '0') continue;
                vertexes.add(new int[] {i, j});
                islandCount++;
                while (!vertexes.isEmpty()) {
                    int[] point = vertexes.poll();
                    if (grid[point[0]][point[1]] == '0') continue;
                    grid[point[0]][point[1]] = '0';
                    vertexes.addAll(neighbors(point[0], point[1], grid.length, grid[0].length));
                }
            }

        return islandCount;
    }

    public List<int[]> neighbors(int x, int y, int maxX, int maxY) {
        List<int[]> l = new LinkedList<>();
        int[][] additions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        for (int[] add : additions) {
            if (x + add[0] < 0 || x + add[0] >= maxX || y + add[1] < 0 || y + add[1] >= maxY)
                continue;
            l.add(new int[] {x + add[0], y + add[1]});
        }
        return l;
    }
}
