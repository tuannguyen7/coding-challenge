package leetcode;

public class Problem48 {
    public void rotate(int[][] matrix) {
        rotate(matrix, matrix.length, 0, 0);
        System.out.println();
    }

    // baseX, baseY -> top left X and Y
    public void rotate(int[][] matrix, int size, int baseX, int baseY) {
        if (size <= 0)
            return;
        for (int i = 0; i < size - 1; i++) {
            int[] xCoordinate = {baseX    , baseX + i       , baseX + size - 1, baseX + size - 1 - i};
            int[] yCoordinate = {baseY + i, baseY + size - 1, baseY + size - 1 - i, baseY};
            int prev = matrix[xCoordinate[3]][yCoordinate[3]];
            for (int coordinateIndex = 0; coordinateIndex < xCoordinate.length; coordinateIndex++) {
                int tmp = matrix[xCoordinate[coordinateIndex]][yCoordinate[coordinateIndex]];
                matrix[xCoordinate[coordinateIndex]][yCoordinate[coordinateIndex]] = prev;
                prev = tmp;
            }
        }
        rotate(matrix, size - 2, baseX + 1, baseY + 1);
    }

    public static void main(String[] args) {
        //int[][] matrix = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] matrix = {{4, 8},{3,6}};
        new Problem48().rotate(matrix);
    }
}
