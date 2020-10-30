package leetcode;

import java.util.Arrays;

public class Problem799 {

    public double champagneTower(int poured, int query_row, int query_glass) {
        return solve3(poured, query_row, query_glass);
    }

    // Solution_1: backtrack // TLE
    public double backtrack(int poured, int query_row, int query_glass) {
        double[][] matrix = new double[query_row + 1][query_row + 1];
        pour(poured, 0, 0, matrix, query_row);
        return matrix[query_row][query_glass];
    }

    private void pour(double water, int row, int col, double[][] matrix, int desRow) {
        if (row > desRow || water < 0)
            return;
        double pouring = Math.min(1 - matrix[row][col], water);
        matrix[row][col] += pouring;
        pour((water - pouring)/2, row + 1, col, matrix, desRow);
        pour((water - pouring)/2, row + 1, col + 1, matrix, desRow);
    }

    static int nCr(int n, int r)
    {
        return fact(n) / (fact(r) *
                fact(n - r));
    }

    // Returns factorial of n
    static int fact(int n)
    {
        int res = 1;
        for (int i = 2; i <= n; i++)
            res = res * i;
        return res;
    }

    // In progress:
    public double solve2(int poured, int query_row, int query_glass) {
        double left = 0;
        double pouring = poured;
        for (int i = 0; i <= query_row; i++) {
            left = Math.min(1, pouring);
            if (left == 0) break;
            pouring = (pouring - left)/2;
        }
        double ncr = nCr(query_row, query_glass);
        return Math.min(1, ncr * left);
    }

    // Simulation
    public double solve3(int poured, int query_row, int query_glass) {
        double[][] matrix = new double[query_row + 1][query_row + 1];
        double[][] remaining = new double[query_row + 1][query_row + 1];
        for (int i = 0; i <= query_row; i++) {
            Arrays.fill(matrix[i], 0.0);
            Arrays.fill(remaining[i], 0.0);
        }
        matrix[0][0] = Math.min(poured, 1);
        remaining[0][0] = poured - matrix[0][0];
        for (int i = 1; i <= query_row; i++) {
            for (int j = 0; j <= i; j++) {
                if (j != 0) {
                    double canTake = Math.min(1 - matrix[i][j], remaining[i - 1][j - 1]/2);
                    matrix[i][j] += canTake;
                    remaining[i][j] += remaining[i - 1][j - 1]/2 - canTake;
                }
                if (j != i) {
                    double canTake = Math.min(1 - matrix[i][j], remaining[i - 1][j]/2);
                    matrix[i][j] += canTake;
                    remaining[i][j] += remaining[i - 1][j]/2 - canTake;
                }
            }
        }
        return matrix[query_row][query_glass];
    }

    public static void main(String[] args) {
        int poured = 100000009;
        int queryRow = 33;
        int queryGlass = 17;
        //System.out.println(new Problem799().champagneTower(poured, queryRow, queryGlass));
        System.out.println(new Problem799().champagneTower(6, 3, 2));
        //System.out.println(new Problem799().champagneTower(5, 2, 1));
    }
}
