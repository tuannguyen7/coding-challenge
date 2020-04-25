package leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class Problem62 {

    public int uniquePaths(int m, int n) {
        Queue<Point> points = new LinkedList<>();
        int numSol = 0;
        points.add(new Point(1, 1));
        while (!points.isEmpty()) {
            Point p = points.poll();
            if (p.row == m && p.col == n)
                numSol += 1;
            else {
                if (p.row < m)
                    points.add(new Point(p.row + 1, p.col));
                if (p.col < n)
                    points.add(new Point(p.row, p.col + 1));
            }
        }
        return numSol;
    }

    public int fastSol(int m, int n) {
        int[][] table = new int[m + 1][n + 1];
        table[0][0] = 0;
        table[1][1] = 1;
        for (int i = 1; i <= m; i++)
            table[i][1] = 1;
        for (int i = 1; i <= n; i++)
            table[1][i] = 1;

        for (int i = 2; i < m + 1; i++) {
            for (int j = 2; j < n + 1; j++) {
                table[i][j] = table[i - 1][j] + table[i][j - 1];
            }
        }

        return table[m][n];
    }

    public static class Point {
        int row;
        int col;

        public Point(int r, int c) {
            row = r;
            col = c;
        }
    }

  public static void main(String[] args) {
    System.out.println(new Problem62().uniquePaths(3, 3));
    System.out.println(new Problem62().fastSol(2,3));
  }
}
