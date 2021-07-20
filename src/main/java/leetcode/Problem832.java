package leetcode;

public class Problem832 {
    public int[][] flipAndInvertImage(int[][] A) {
        int row = A.length;
        int col = A[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < (col - 1)/2; j++) {
                int tmp = A[i][j];
                A[i][j] = A[i][col - j - 1] ^ 1;
                A[i][col - j - 1] = tmp ^ 1;
            }
            if (col % 2 == 0) {
                int tmp = A[i][col/2 - 1];
                A[i][col/2 - 1] = A[i][col/2] ^ 1;
                A[i][col/2] = tmp ^ 1;
            } else {
                A[i][col/2] ^= 1;
            }
        }
        return A;
    }
}
