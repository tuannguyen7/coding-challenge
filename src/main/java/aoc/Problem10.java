package aoc;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class Problem10 {

    public static void solution() {
        FastReader reader = new FastReader(System.in);
        List<char[]> matrix = new ArrayList<>();

        int lineCount = 140;
        while (lineCount-- > 0) {
            char[] row = reader.next().toCharArray();
            matrix.add(row);
        }
        char[][] matrix2 = new char[matrix.size()][matrix.get(0).length];

        for (int i = 0; i < matrix.size(); i++)
            matrix2[i] = matrix.get(i);
        System.out.println(solve(matrix2));
    }

    public static int solve(char[][] matrix) {
        int[][] dp = new int[matrix.length][matrix[0].length];
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i  < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }

        for (int i = 0; i  < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }
        int startPosX = 0;
        int startPosY = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 'S') {
                    startPosX = i;
                    startPosY = j;
                    break;
                }
            }

        dp[startPosX][startPosY] = 0;
        visited[startPosX][startPosY] = true;
        int length = 0;
        if (startPosX > 0) {
            length = Math.max(length, dfs(matrix, startPosX - 1, startPosY, visited));
        }
        if (startPosX < matrix.length - 1) {
            int tmpL = dfs(matrix, startPosX + 1, startPosY, visited);
            length = Math.max(length, tmpL);
        }
        if (startPosY > 0) {
            int tmpL = dfs(matrix, startPosX, startPosY - 1, visited);
            length = Math.max(length, tmpL);
        }

        if (startPosY < matrix[0].length - 1) {
            int tmpL = dfs(matrix, startPosX, startPosY + 1, visited);
            length = Math.max(length, tmpL);
        }

        return length/2;
    }

    static Set<Character> right = Set.of('-', 'J', '7', 'S');
    static Set<Character> down = Set.of('|', 'J', 'L', 'S');
    static Set<Character> up = Set.of('|', '7', 'F', 'S');
    static Set<Character> left = Set.of('-', 'L', 'F', 'S');


    public static int dfs(char[][] matrix, int x, int y, boolean[][] visited) {
        if (matrix[x][y] == '.')
            return -1;

        for (int i = 0; i  < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }
        Stack<int[]> stack = new Stack<>();
        int stepCount = 1;
        stack.push(new int[]{x, y, 1});

        while (!stack.isEmpty()) {
            int[] stackVal = stack.pop();
            x = stackVal[0];
            y = stackVal[1];
            int count = stackVal[2];
            if (matrix[x][y] == 'S') {
                stepCount = Math.max(stepCount, count);
            }

            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;

            if (matrix[x][y] == 'F') {
                if (x < matrix.length - 1 && down.contains(matrix[x+1][y])) {
                    stack.push(new int[]{x+1, y, count + 1});
                }

                if (y < matrix[0].length - 1 && right.contains(matrix[x][y+1])) {
                    stack.push(new int[]{x, y+1, count+1});
                }
            } else if (matrix[x][y] == 'J') {
                if (x > 0 && up.contains(matrix[x-1][y])) {
                    stack.push(new int[]{x-1, y, count + 1});
                }

                if (y > 0 && left.contains(matrix[x][y-1])) {
                    stack.push(new int[]{x, y-1, count+1});
                }
            } else if (matrix[x][y] == '7') {
                if (x < matrix.length - 1  &&  down.contains(matrix[x+1][y])) {
                    stack.push(new int[]{x+1, y, count + 1});
                }

                if (y > 0 && left.contains(matrix[x][y-1])) {
                    stack.push(new int[]{x, y-1, count+1});
                }
            } else if (matrix[x][y] == 'L') {
                if (x > 0 && up.contains(matrix[x-1][y])) {
                    stack.push(new int[]{x-1, y, count + 1});
                }

                if (y < matrix.length - 1 && right.contains(matrix[x][y+1])) {
                    stack.push(new int[]{x, y+1, count+1});
                }
            } else if (matrix[x][y] == '-') {
                if (y > 0 && left.contains(matrix[x][y-1])) {
                    stack.push(new int[]{x, y-1, count+1});
                }
                if (y < matrix.length - 1 && right.contains(matrix[x][y+1])) {
                    stack.push(new int[]{x, y+1, count+1});
                }
            } else if (matrix[x][y] == '|') {
                if (x < matrix.length - 1 && down.contains(matrix[x+1][y])) {
                    stack.push(new int[]{x+1, y, count+1});
                }

                if (x > 0 && up.contains(matrix[x-1][y])) {
                    stack.push(new int[]{x-1, y, count+1});
                }
            }
        }

        return stepCount;
    }

    public static void main(String[] args) {
        solution();
    }

    static class FastReader {
        InputStream is;
        private byte[] inbuf = new byte[1024];
        private int lenbuf = 0, ptrbuf = 0;

        public FastReader(InputStream is) {
            this.is = is;
        }

        public int readByte() {
            if (lenbuf == -1) throw new InputMismatchException();
            if (ptrbuf >= lenbuf) {
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if (lenbuf <= 0)
                    return -1;
            }
            return inbuf[ptrbuf++];
        }

        public boolean isSpaceChar(int c) {
            return !(c >= 33 && c <= 126);
        }

        public int skip() {
            int b;
            while ((b = readByte()) != -1 && isSpaceChar(b)) ;
            return b;
        }

        public String next() {
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while (!(isSpaceChar(b))) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }

        public String nextLine() {
            int b = readByte();
            StringBuilder sb = new StringBuilder();
            while (b != '\n' || b != '\r') {
                sb.appendCodePoint(b);
            }
            return sb.toString();
        }

        public int nextInt() {
            int num = 0, b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            while (true) {
                if (b >= '0' && b <= '9') {
                    num = (num << 3) + (num << 1) + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public long nextLong() {
            long num = 0;
            int b;
            boolean minus = false;
            while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
            if (b == '-') {
                minus = true;
                b = readByte();
            }
            while (true) {
                if (b >= '0' && b <= '9') {
                    num = (num << 3) + (num << 1) + (b - '0');
                } else {
                    return minus ? -num : num;
                }
                b = readByte();
            }
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }

        public char[] next(int n) {
            char[] buf = new char[n];
            int b = skip(), p = 0;
            while (p < n && !(isSpaceChar(b))) {
                buf[p++] = (char) b;
                b = readByte();
            }
            return n == p ? buf : Arrays.copyOf(buf, p);
        }

        public char nextChar() {
            return (char) skip();
        }
    }
}
