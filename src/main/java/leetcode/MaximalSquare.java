package leetcode;

/**
 * URL: https://leetcode.com/problems/maximal-square/
 * */
public class MaximalSquare {

    // Solution1. Brute force
    public int maximalSquare(char[][] matrix) {
        int max = 0;
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                max = Math.max(max, maximalSquare(matrix, i, j));
            }
        }
        return max;
    }

    public int maximalSquare(char[][] matrix, int x, int y) {
        int squareSize = 0;
        int rightX = x;
        int rightY = y;
        outter: while (rightX < matrix.length && rightY < matrix[0].length) {

            for (int startX = rightX; startX >= x; startX--) {
                if (matrix[startX][rightY] != '1')
                    break outter;
            }

            for (int startY = rightY; startY >= y; startY--) {
                if (matrix[rightX][startY] != '1')
                    break outter;
            }

            squareSize++;
            rightX++;
            rightY++;
        }
        return squareSize * squareSize;
    }

    public static void main(String[] args) {
        char[][] matrix = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        new MaximalSquare().maximalSquare(matrix);
    }
}
