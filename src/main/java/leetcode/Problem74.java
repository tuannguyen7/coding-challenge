package leetcode;

public class Problem74 {
    public boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return false;
        int first = 0;
        int last = matrix.length * matrix[0].length - 1;
        while (first <= last) {
            int mid = (first + last)/2;
            int x = mid/matrix[0].length;
            int y = mid%matrix[0].length;
            if (matrix[x][y] == target)
                return true;
            if (matrix[x][y] > target)
                last = mid;
            else
                first = mid;
        }

        return false;
    }

}
