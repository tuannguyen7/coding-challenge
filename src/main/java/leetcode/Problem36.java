package leetcode;

public class Problem36 {

    public boolean isValidSudoku(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            if (!isRowValid(board, i) || !isColValid(board, i))
                return false;
        }

        return isBoxValid(board);
    }

    public boolean isRowValid(char[][] board, int rowIndex) {
        boolean[] found = {false, false, false, false, false, false, false, false, false};
        for (int i = 0; i < board.length; i++) {

            int index = charToIndex(board[rowIndex][i]);
            if (index == 9) continue;
            if (found[index])
                return false;

            found[index] = true;
        }
        return true;
    }

    public boolean isColValid(char[][] board, int rowIndex) {
        boolean[] found = {false, false, false, false, false, false, false, false, false};
        for (int i = 0; i < board.length; i++) {
            int index = charToIndex(board[i][rowIndex]);
            if (index == 9) continue;
            if (found[index])
                return false;

            found[index] = true;
        }
        return true;
    }

    public boolean isBoxValid(char[][] board) {
        for (int row = 0; row < board.length; row += 3) {
            for (int col = 0; col < board.length; col += 3) {
                boolean[] found = {false, false, false, false, false, false, false, false, false};
                for (int i = 0;i < 3; i++) {
                    for (int j = 0; j < 3; j ++) {
                        char c = board[row + i][col + j];
                        int index = charToIndex(c);
                        if (index == 9) continue;
                        if (found[index])
                            return false;
                        found[index] = true;
                    }
                }
            }
        }

        return true;
    }

    public int charToIndex(char c) {
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
            default: return 9;
        }
    }

    public static void main(String[] args) {
    //
    }
}
