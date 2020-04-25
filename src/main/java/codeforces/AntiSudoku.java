package codeforces;

import java.util.Scanner;

/**
 * URL: https://codeforces.com/problemset/problem/1335/D
 * rating: 1300
 * */
public class AntiSudoku {

    public static void solve(int[][] sudoku) {
        for (int i = 0; i < 3; i++ ) {
            for (int j = 0; j < 3; j++) {
                int row = 3*i + j;
                int col = 3*j + i;
                int newVal;
                if (row % 3 != 2) {
                    newVal = sudoku[row + 1][col];
                } else {
                    newVal = sudoku[row - 1][col];
                }
                sudoku[row][col] = newVal;
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);    //Instantiation Of Scanner Object
        int numTests = sc.nextInt();          //reads integer
        sc.nextLine();
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < numTests; i++) {
            int[][] sudoku = new int[9][9];
            for (int r = 0; r < 9; r++) {
                String line = sc.nextLine();
                for (int c = 0; c < 9; c++) {
                    sudoku[r][c] = line.charAt(c) - '0';
                }
            }
            solve(sudoku);
            result.append(printSudoku(sudoku));
        }
        System.out.println(result.toString());
    }

    public static StringBuilder printSudoku(int[][] sudoku) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < sudoku.length; i++) {
            for (int j = 0; j < sudoku[0].length; j++) {
                output.append(sudoku[i][j]);
            }
            output.append("\n");
        }
        return output;
    }
}
