package leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Problem59 {

    private static final Logger log = LoggerFactory.getLogger(Problem59.class);

    public int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        generateMatrixCover(matrix, 0, 0, n, 1);
        log.info("matrix {}", matrix);
        return matrix;
    }

    public void generateMatrixCover(int[][] matrix, int baseX, int baseY, int size, int baseNum) {
        if (size <= 0)
            return;
        if (size == 1) {
            matrix[baseX][baseY] = baseNum;
            return;
        }

        for (int i = 0; i < size; i++)
            matrix[baseX][baseY + i] = baseNum++;

        for (int i = 1; i < size - 1; i++)
            matrix[baseX + i][baseY + size - 1] = baseNum++;

        for (int i = size - 1; i >= 0; i--)
            matrix[baseX + size - 1][baseY + i] = baseNum++;

        for (int i = size - 2; i > 0; i--)
            matrix[baseX + i][baseY] = baseNum++;

        generateMatrixCover(matrix, baseX + 1, baseY + 1, size - 2, baseNum);
    }

    public static void main(String[] args) {
        int n = 3;
        new Problem59().generateMatrix(n);
    }
}
