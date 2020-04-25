package leetcode;

import java.util.Arrays;

public class Problem79 {
    public boolean exist(char[][] board, String word) {
        boolean[][] visited = new boolean[board.length][board[0].length];
        for (int i = 0; i < board.length; i++)
            Arrays.fill(visited[i], false);
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (backtrack(board, word, i, j, 0, visited))
                    return true;
            }
        }
        return false;
    }

    public boolean backtrack(char[][] board, String word, int x, int y, int index, boolean[][] visited) {
        if (index == word.length())
            return true;
        if (isOutOfScope(board.length, board[0].length, x, y) || visited[x][y])
            return false;
        char c = word.charAt(index);
        if (board[x][y] != c)
            return false;

        visited[x][y] = true;
        int[][] directions = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        for (int[] dir : directions) {
            int nx = x + dir[0];
            int ny = y + dir[1];
            if(backtrack(board, word, nx, ny, index + 1, visited))
                return true;
        }
        visited[x][y] = false;
        return false;
    }

    public boolean isOutOfScope(int m, int n, int x, int y) {
        if (x < 0 || y < 0 || x >= m || y>= n)
            return true;
        return false;
    }

    public static void main(String[] args) {
        //char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        char[][] board = {{'A'}};
        //String word = "SEE";
        String word = "A";
        System.out.println(new Problem79().exist(board, word));
    }
}
