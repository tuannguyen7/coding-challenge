package leetcode;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Problem54 {
    public List<Integer> spiralOrder(int[][] matrix) {
        if (matrix.length == 0 || matrix[0].length == 0)
            return Collections.emptyList();
        return spiralOrderHelper(matrix, 0, 0, matrix.length, matrix[0].length);
    }

    public List<Integer> spiralOrderHelper(int[][] matrix, int baseX, int baseY, int sizeX, int sizeY) {
        if (sizeX <= 0 || sizeY <= 0)
            return Collections.emptyList();


        List<Integer> ans = new LinkedList<>();

        if (sizeX == 1) {
            for (int i = 0; i < sizeY; i++) {
                ans.add(matrix[baseX][baseY + i]);
            }
            return ans;
        }

        if (sizeY == 1) {
            for (int i = 0; i < sizeX; i++) {
                ans.add(matrix[baseX + i][baseY]);
            }
            return ans;
        }

        for (int i = 0; i < sizeY; i++) {
            ans.add(matrix[baseX][baseY + i]);
        }

        for (int i = 1; i < sizeX - 1; i++) {
            ans.add(matrix[baseX + i][baseY + sizeY - 1]);
        }

        for (int i = 0; i < sizeY; i++) {
            ans.add(matrix[baseX + sizeX - 1][baseY + sizeY - 1 - i]);
        }

        for (int i = 1; i < sizeX - 1; i++) {
            ans.add(matrix[baseX + sizeX - 1 - i][baseY]);
        }

        ans.addAll(spiralOrderHelper(matrix, baseX + 1, baseY + 1, sizeX - 2, sizeY - 2));
        return ans;
    }

    public static void main(String[] args) {
        int[][] matrix = {{ 1, 2, 3 },{ 4, 5, 6 },{ 7, 8, 9 }};
        //int[][] matrix = {{1, 2, 3, 4},{5, 6, 7, 8},{9,10,11,12}};
        //int[][] matrix = {{1,2,3}};
        //int[][] matrix = {{1}, {2}, {3}};
        List<Integer> ans = new Problem54().spiralOrder(matrix);
        ans.stream().forEach(n -> System.out.print(n + " "));
    }
}
