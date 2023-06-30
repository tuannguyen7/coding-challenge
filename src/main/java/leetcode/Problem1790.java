package leetcode;

import java.util.Arrays;

public class Problem1790 {

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
        while (l < r) {
            int m = (r + l)/2; // day m
            int[][] matrixAtDayM = buildUpMatrix(matrix, cells, l, m);
            if (canReachDestination(matrixAtDayM)) {
                last = m;
                l = m + 1;
                matrix = matrixAtDayM;
            } else {
                r = m;
            }
        }
        return last;
    }

    public int[][] buildUpMatrix(int[][] matrix, int[][] cells, int from, int to) {
        int [][] copiedMatrix = clone(matrix);

        for (int i = from; i <= to; i++) {
            int tmpR = cells[i][0] - 1;
            int tmpC = cells[i][1] - 1;
            copiedMatrix[tmpR][tmpC] = 1;
        }

        return copiedMatrix;
    }

    public int[][] clone(int[][] matrix) {
        int [][] myInt = new int[matrix.length][];
        for(int i = 0; i < matrix.length; i++)
            myInt[i] = matrix[i].clone();
        return myInt;
    }

    public boolean canReachDestination(int[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++)
            if (canReachDestination(matrix, 0, i, 0))
                return true;

        return false;
    }

    // direction: 0 -> down, 1 -> from right, 2 -> from left

    public boolean canReachDestination(int[][] matrix, int r, int c, int direction) {
        if (c < 0 || c >= matrix[0].length)
            return false;
        if (matrix[r][c] == 1)
            return false;
        if (r == matrix.length - 1)
            return true;

        if (canReachDestination(matrix, r + 1, c, 0))
            return true;
        if (direction == 0) {
            return canReachDestination(matrix, r, c - 1, 1) ||  canReachDestination(matrix, r, c + 1, 2);
        } else if (direction == 1) {
            // if it is from right => only go to the left
            return canReachDestination(matrix, r, c - 1, 1);
        } else {
            // if it is from left => only go to the right
            return canReachDestination(matrix, r, c + 1, 2);
        }
    }

    public static void main(String[] args) {
        var sol = new Problem1790();
        int[][] cells = {{1,1},{2,1},{1,2},{2,2}};
        var result = sol.latestDayToCross(2, 2, cells);
        System.out.println(result);
    }
}
