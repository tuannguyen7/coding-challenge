package leetcode;

import java.util.Arrays;

public class Problem1970 {

    public int latestDayToCross(int row, int col, int[][] cells) {
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++)
            Arrays.fill(matrix[i], 0);
        return find(matrix, cells) + 1; // day is 1-based index
    }

    public int find(int[][] matrix, int[][] cells) {
        int l = 0;
        int r = cells.length;
        int last = l;
        int matrixValue = 1; // 1 water, 0 land
        while (l < r) {
            int m = (r + l)/2; // day m
            if (matrixValue == 1) {
                fixMatrix(matrix, cells, l, m, matrixValue);
            } else {
                fixMatrix(matrix, cells, m + 1, r, matrixValue);
            }
            if (canReachDestination(matrix)) {
                last = m;
                l = m + 1;
                matrixValue = 1;
            } else {
                r = m;
                matrixValue = 0;
            }
        }
        return last;
    }

    // apply matrixValue on matrix
    public int[][] fixMatrix(int[][] matrix, int[][] cells, int from, int to, int matrixValue) {
        for (int i = from; i <= to; i++) {
            int tmpR = cells[i][0] - 1;
            int tmpC = cells[i][1] - 1;
            matrix[tmpR][tmpC] = matrixValue;
        }

        return matrix;
    }

    public boolean canReachDestination(int[][] matrix) {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int ix = 0; ix < matrix.length; ix++)
            Arrays.fill(visited[ix], false);
        for (int i = 0; i < matrix[0].length; i++) {
            if (canReachDestination2(matrix, 0, i, visited))
                return true;
        }

        return false;
    }

    public boolean canReachDestination2(int[][] matrix, int r, int c, boolean[][] visited) {
        if (c < 0 || c >= matrix[0].length)
            return false;
        if (r < 0)
            return false;
        if (matrix[r][c] == 1)
            return false;
        if (r == matrix.length - 1)
            return true;
        if (visited[r][c]) {
            return false;
        }
        visited[r][c] = true;

        if (r == 0) {
            return canReachDestination2(matrix, r + 1, c, visited);
        } else {
            return canReachDestination2(matrix, r + 1, c, visited)
                    || canReachDestination2(matrix, r, c - 1, visited)
                    ||  canReachDestination2(matrix, r, c + 1, visited)
                    || canReachDestination2(matrix, r- 1, c, visited);
        }
    }

   public static void main(String[] args) {
        var sol = new Problem1970();
        int[][] cells = {{1,1},{2,1},{1,2},{2,2}};
        var result = sol.latestDayToCross(2, 2, cells);
        System.out.println(result);
    }
}
