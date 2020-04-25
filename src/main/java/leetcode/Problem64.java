package leetcode;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Problem64 {

    // Dijkstra
    public int minPathSum(int[][] grid) {
        if (grid.length == 0 || grid[0].length == 0)
            return 0;
        int minDistances[][] = new int[grid.length][grid[0].length];
        PriorityQueue<Pair> queue = new PriorityQueue<>();
        boolean visited[][] = new boolean[grid.length][grid[0].length];

        for (int i = 0; i < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }

        for (int i = 0; i < minDistances.length; i++) {
            Arrays.fill(minDistances[i], Integer.MAX_VALUE);
        }

        int[][] directions = {{1, 0}, {0, 1}}; // move down or right
        minDistances[0][0] = grid[0][0];
        queue.add(new Pair(0, 0, grid[0][0]));

        while (!queue.isEmpty()) {
            Pair p = queue.poll();
            if (visited[p.x][p.y]) continue;

            for (int[] direction : directions) {
                int nx = p.x + direction[0];
                int ny = p.y + direction[1];

                if (isOutOfGrid(nx, ny, grid.length, grid[0].length)
                  || visited[nx][ny] ) {
                    continue;
                }

                minDistances[nx][ny] = Math.min(minDistances[nx][ny], p.weight + grid[nx][ny]);
                queue.add(new Pair(nx, ny, minDistances[nx][ny]));
            }

            visited[p.x][p.y] = true;
        }

        return minDistances[grid.length - 1][grid[0].length - 1];
    }

    public boolean isOutOfGrid(int x, int y, int maxX, int maxY) {
        if (x < 0 || y < 0 || x >= maxX || y >= maxY)
            return true;
        return false;
    }

    public static void main(String[] args) {
        int[][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.println(new Problem64().minPathSum(grid));
    }

    public static class Pair implements Comparable<Pair> {
        int x;
        int y;
        int weight;

        public Pair(int x, int y, int weight) {
            this.x = x;
            this.y = y;
            this.weight = weight;
        }

        @Override
        public int compareTo(Pair o) {
            return weight - o.weight;
        }
    }
}
