package leetcode;

public class Problem6 {

    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        StringBuilder result = new StringBuilder();
        char[][] matrix = new char[numRows][1000];
        int colIndex = 0;
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }

        int stepSize = (numRows - 1);
        int loopsize = s.length()/(stepSize * 2);
        int sIndex = 0;
        for (int i = 0; i < loopsize; i++) {
            for (int r = 0; r < stepSize; r++) {
                matrix[r][colIndex] = s.charAt(sIndex+r);
            }

            sIndex += stepSize;
            for (int c = 0; c < stepSize; c++) {
                matrix[numRows - 1 - c][colIndex + c] = s.charAt(sIndex+c);
            }
            colIndex += stepSize;
            sIndex += stepSize;
        }

        for (int r = 0; r < stepSize && sIndex < s.length(); r++, sIndex++) {
            matrix[r][colIndex] = s.charAt(sIndex);
        }
        for (int c = 0; c < stepSize && sIndex < s.length(); c++, sIndex++) {
            matrix[numRows - 1 - c][colIndex + c] = s.charAt(sIndex);
        }

        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) result.append(matrix[i][j]);
            }

        return result.toString();
    }

    public static void main(String[] args) {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        System.out.println(new Problem6().convert(s, numRows));
    }
}
