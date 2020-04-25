package leetcode;

public class Problem37 {

    private static final int SIZE = 9;
    public void solveSudoku(char[][] board) {
        boolean[][] rowFound = makeRow(board);
        boolean[][] colFound = makeCol(board);
        boolean[][] cubeFound = makeCube(board);

        // try
        boolean canSolve = try_(board, 0, 0, rowFound, colFound, cubeFound);
        System.out.println(canSolve);
    }

    private boolean try_(char[][] board, int row, int col, boolean[][] rowFound, boolean[][] colFound, boolean[][] cubeFound) {
        if (row == SIZE)
            return true;

        if (col == SIZE)
            return try_(board, row + 1, 0, rowFound, colFound, cubeFound);

        if (board[row][col] != '.')
            return try_(board, row, col + 1, rowFound, colFound, cubeFound);

        for (int val = 0; val < SIZE; val++) {
            if (rowFound[row][val] || colFound[col][val] || cubeFound[toCubeIndex(row, col)][val])
                continue;
            // mark
            board[row][col] = (char)('1' + val);
            rowFound[row][val] = true;
            colFound[col][val] = true;
            cubeFound[toCubeIndex(row, col)][val] = true;
            if (try_(board, row, col + 1, rowFound, colFound, cubeFound))  // can solve
                return true;

            // reset
            rowFound[row][val] = false;
            colFound[col][val] = false;
            cubeFound[toCubeIndex(row, col)][val] = false;
            board[row][col] = '.';
        }

        return false;
    }

    private boolean[][] makeRow(char[][] board) {
        boolean[][] ret = new boolean[SIZE][];
        for (int i = 0; i < SIZE; i++) {
            boolean[] found = {false, false, false, false, false, false, false, false, false};
            for (int j = 0; j < SIZE; j++) {
                int index = charToIndex(board[i][j]);
                if (index == -1) continue;
                found[index] = true;
            }
            ret[i] = found;
        }
        return ret;
    }

    private boolean[][] makeCol(char[][] board) {
        boolean[][] ret = new boolean[SIZE][];
        for (int i = 0; i < SIZE; i++) {
            boolean[] found = {false, false, false, false, false, false, false, false, false};
            for (int j = 0; j < SIZE; j++) {
                int index = charToIndex(board[j][i]);
                if (index == -1) continue;
                found[index] = true;
            }
            ret[i] = found;
        }
        return ret;
    }

    public boolean[][] makeCube(char[][] board) {

        boolean[][] ret = new boolean[SIZE][];

        for (int row = 0; row < SIZE; row += 3) {
            for (int col = 0; col < SIZE; col += 3) {
                boolean[] found = {false, false, false, false, false, false, false, false, false};
                for (int i = 0;i < 3; i++) {
                    for (int j = 0; j < 3; j ++) {
                        char c = board[row + i][col + j];
                        int index = charToIndex(c);
                        if (index == -1) continue;
                        found[index] = true;
                    }
                }
                ret[toCubeIndex(row, col)] = found;
            }
        }

        return ret;
    }

    private int charToIndex(char c) {
        switch (c) {
            case '1': return 0;
            case '2': return 1;
            case '3': return 2;
            case '4': return 3;
            case '5': return 4;
            case '6': return 5;
            case '7': return 6;
            case '8': return 7;
            case '9': return 8;
            default: return -1;
        }
    }

    // 2, 2 -> 0
    // 3, 2 -> 3
    // 2, 3 -> 1
    private int toCubeIndex(int row, int col) {
        return (row/3)*3 + col/3;
    }

    public static void main(String[] args) {
        char[][] board = {{'5','3','.','.','7','.','.','.','.'},{'6','.','.','1','9','5','.','.','.'},{'.','9','8','.','.','.','.','6','.'},{'8','.','.','.','6','.','.','.','3'},{'4','.','.','8','.','3','.','.','1'},{'7','.','.','.','2','.','.','.','6'},{'.','6','.','.','.','.','2','8','.'},{'.','.','.','4','1','9','.','.','5'},{'.','.','.','.','8','.','.','7','9'}};
        new Problem37().solveSudoku(board);
    }
}
