package aoc;

import java.util.*;

public class Problem10Part2 {

    public static void solution() {
        Utils.FastReader reader = new Utils.FastReader(System.in);
        List<char[]> matrix = new ArrayList<>();

        int lineCount = 140;
        while (lineCount-- > 0) {
            String line = reader.next();
            matrix.add(line.toCharArray());
        }
        char[][] matrix2 = new char[matrix.size()][matrix.get(0).length];

        for (int i = 0; i < matrix.size(); i++)
            matrix2[i] = matrix.get(i);
        System.out.println(solve(matrix2));
    }

    public static int solve(char[][] matrix) {
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];

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

        visited[startPosX][startPosY] = true;
        LinkedList<int[]> l = new LinkedList<>();
        if (startPosX > 0) {
            l = dfs(matrix, startPosX - 1, startPosY, visited);
        }
        if (startPosX < matrix.length - 1) {
            var tmpL = dfs(matrix, startPosX + 1, startPosY, visited);
            if (tmpL.size() > l.size()) {
                l = tmpL;
            }
        }
        if (startPosY > 0) {
            var tmpL = dfs(matrix, startPosX, startPosY - 1, visited);
            if (tmpL.size() > l.size()) {
                l = tmpL;
            }
        }

        if (startPosY < matrix[0].length - 1) {
            var tmpL = dfs(matrix, startPosX, startPosY + 1, visited);
            if (tmpL.size() > l.size()) {
                l = tmpL;
            }
        }

        boolean[][] loopMap = new boolean[matrix.length * 2][matrix[0].length * 2];
        char[][] extendedMatrix = new char[matrix.length * 2][matrix[0].length * 2];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                extendedMatrix[i * 2][j * 2] = matrix[i][j];
                extendedMatrix[i * 2][j * 2 + 1] = '#';
                extendedMatrix[i * 2 + 1][j * 2] = '#';
                extendedMatrix[i * 2 + 1][j * 2 + 1] = '#';
            }
        }
        for (int i = 0; i < loopMap.length; i++)
            Arrays.fill(loopMap[i], false);

        for (int i = 0; i < l.size(); i++) {
            int[] e = l.get(i);
            int[] ne = l.get((i+1) % l.size());
            int ex = e[0]*2;
            int ey = e[1]*2;
            int nex = ne[0]*2;
            int ney = ne[1]*2;
            loopMap[ex][ey] = true;
            if (nex > ex) {
                loopMap[ex+1][ey] = true;
            } else if (nex < ex) {
                loopMap[ex-1][ey] = true;
            }
            if  (ney > ey) {
                loopMap[ex][ey+1] = true;
            } else if (ney < ey) {
                loopMap[ex][ey-1] = true;
            }
            if (nex > ex && ney > ey) {
                assert false;
            }
        }

        return fillMap(loopMap, extendedMatrix);
    }

    static int fillMap(boolean[][] loopMap, char[][] matrix) {
        int m = loopMap.length;
        int n = loopMap[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i  < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }

        for (int i = 0; i < m; i++) {
            dfs(i, 0, loopMap, visited);
            dfs(i, n-1, loopMap, visited);
        }
        for (int j = 0; j < n; j++) {
            dfs(0, j, loopMap, visited);
            dfs(m-1, j, loopMap, visited);
        }

        int titleCount = 0;
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (!loopMap[i][j] && !visited[i][j] && matrix[i][j] != '#') {
                    //System.out.println("x: " + i + ", y: " + j);
                    titleCount++;
                }
            }
        return titleCount;
    }

    static void dfs(int x, int y, boolean[][] loopMap, boolean[][] visited) {
        Stack<int[]> stack = new Stack<>();
        stack.push(new int[]{x, y});
        while (!stack.isEmpty()) {
            int[] v = stack.pop();
            x = v[0];
            y = v[1];
            if (x < 0 || x >= loopMap.length|| y < 0 || y >= loopMap[0].length)
                continue;
            if (visited[x][y]) {
                continue;
            }
            if (loopMap[x][y]) {
                continue;
            }
            visited[x][y] = true;
            stack.push(new int[]{x - 1, y});
            stack.push(new int[]{x + 1, y});
            stack.push(new int[]{x, y - 1});
            stack.push(new int[]{x, y + 1});
        }
    }

    static Set<Character> right = Set.of('-', 'J', '7', 'S');
    static Set<Character> down = Set.of('|', 'J', 'L', 'S');
    static Set<Character> up = Set.of('|', '7', 'F', 'S');
    static Set<Character> left = Set.of('-', 'L', 'F', 'S');

    static class StackFrame {
        int x, y;
        LinkedList<int[]> nodes;

        StackFrame(int x, int y, LinkedList<int[]> nodes) {
            this.x = x;
            this.y = y;
            this.nodes = nodes;
        }
    }

    public static LinkedList<int[]> dfs(char[][] matrix, int x, int y, boolean[][] visited) {
        if (matrix[x][y] == '.')
            return new LinkedList();

        for (int i = 0; i  < visited.length; i++) {
            Arrays.fill(visited[i], false);
        }
        int loopCount = 0;
        Stack<StackFrame> stack = new Stack<>();
        stack.push(new StackFrame(x, y, new LinkedList<>()));

        while (!stack.isEmpty()) {
            StackFrame stackVal = stack.pop();
            x = stackVal.x;
            y = stackVal.y;

            if (matrix[x][y] == 'S' && loopCount > 1) {
                stackVal.nodes.add(new int[]{x, y});
                return stackVal.nodes;
            }
            loopCount++;
            if (visited[x][y]) {
                continue;
            }
            visited[x][y] = true;
            LinkedList<int[]> nodes = new LinkedList<>(stackVal.nodes);
            nodes.add(new int[]{x, y});

            //System.out.println("x: " + x + ", y: " + y);

            if (matrix[x][y] == 'F') {
                if (x < matrix.length - 1 && down.contains(matrix[x+1][y])) {
                    stack.push(new StackFrame(x+1, y, nodes));
                }

                if (y < matrix[0].length - 1 && right.contains(matrix[x][y+1])) {
                    stack.push(new StackFrame(x, y+1, nodes));
                }
            } else if (matrix[x][y] == 'J') {
                if (x > 0 && up.contains(matrix[x-1][y])) {
                    stack.push(new StackFrame(x-1, y, nodes));
                }

                if (y > 0 && left.contains(matrix[x][y-1])) {
                    stack.push(new StackFrame(x, y-1, nodes));
                }
            } else if (matrix[x][y] == '7') {
                if (x < matrix.length - 1  &&  down.contains(matrix[x+1][y])) {
                    stack.push(new StackFrame(x+1, y, nodes));
                }

                if (y > 0 && left.contains(matrix[x][y-1])) {
                    stack.push(new StackFrame(x, y-1, nodes));
                }
            } else if (matrix[x][y] == 'L') {
                if (x > 0 && up.contains(matrix[x-1][y])) {
                    stack.push(new StackFrame(x-1, y, nodes));
                }

                if (y < matrix[0].length - 1 && right.contains(matrix[x][y+1])) {
                    stack.push(new StackFrame(x, y+1, nodes));
                }
            } else if (matrix[x][y] == '-') {
                if (y > 0 && left.contains(matrix[x][y-1])) {
                    stack.push(new StackFrame(x, y-1, nodes));
                }
                if (y < matrix[0].length - 1 && right.contains(matrix[x][y+1])) {
                    stack.push(new StackFrame(x, y+1, nodes));
                }
            } else if (matrix[x][y] == '|') {
                if (x < matrix.length - 1 && down.contains(matrix[x+1][y])) {
                    stack.push(new StackFrame(x+1, y, nodes));
                }

                if (x > 0 && up.contains(matrix[x-1][y])) {
                    stack.push(new StackFrame(x-1, y, nodes));
                }
            }
        }

        assert false;
        return new LinkedList<>();
    }

    public static void main(String[] args) {
        solution();
    }
}
