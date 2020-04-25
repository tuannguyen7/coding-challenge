package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem73 {

    // Solution1. O(m+n) space. Save all rows and columns that will be fill 0
    public void setZeroes(int[][] matrix) {
    }

    // Solution2. O(1) space
    public void setZeroes2(int[][] matrix) {
        int zerozeroType = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 0) {
                    if (i == 0) {
                        zerozeroType |= 1;
                    }
                    if (j == 0) {
                        zerozeroType |= 2;
                    }
                    matrix[i][0] = 0;
                    matrix[0][j] = 0;
                }
            }
        }

        for (int i = 1; i < matrix.length; i++) {
            if (matrix[i][0] == 0) {
                for (int j = 0; j < matrix[0].length; j++) {
                    matrix[i][j] = 0;
                }
            }
        }

        for (int j = 1; j < matrix[0].length; j++) {
            if (matrix[0][j] == 0) {
                for (int i = 0; i < matrix.length; i++) {
                    matrix[i][j] = 0;
                }
            }
        }

        if ((zerozeroType & 1) == 1) {
            for (int i = 0; i < matrix.length; i++)
                matrix[i][0] = 0;
        }

        if ((zerozeroType & 2) == 2)
            for (int i = 0; i < matrix[0].length; i++)
            matrix[0][i] = 0;
    }


    public static void main(String[] args) {
        System.out.println();
    }
}
