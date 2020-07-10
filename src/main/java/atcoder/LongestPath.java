package atcoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Name: LongestPath
 * URL: https://atcoder.jp/contests/dp/tasks/dp_g
 * rating:
 */
public class LongestPath {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void solution() {
        FastReader reader = new FastReader(System.in);

        int numTests = 1;
        for (int i = 0; i < numTests; i++) {
            int N = reader.nextInt();
            int M = reader.nextInt();
            List<Integer>[] adjacencyList = new List[N + 1];
            List<Integer>[] backAdjacencyList = new List[N + 1];

            for (int ni = 0; ni <= N; ni++) {
                adjacencyList[ni] = new ArrayList<>();
                backAdjacencyList[ni] = new ArrayList<>();
            }

            for (int ni = 0; ni < M; ni++) {
                int x = reader.nextInt();
                int y = reader.nextInt();
                List<Integer> adj = adjacencyList[x];
                adj.add(y);
                List<Integer> backAdj = backAdjacencyList[y];
                backAdj.add(x);
            }

            //pw.println(solve(adjacencyList, N));
            pw.println(dynamicProgramming(adjacencyList, backAdjacencyList, N));

        }
        pw.close();
    }

    // Begin. Memorization recursion
    public static int solve(List<Integer>[] adjacencyList, int N) {
        int[] memo = new int[N+1];
        Arrays.fill(memo, -1);
        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(recursive(adjacencyList, i, memo), max);
        }
        return max;
    }

    public static int recursive(List<Integer>[] adjacencyList, int vertex, int[] memo) {
        int max = 0;
        if (memo[vertex] != -1)
            return memo[vertex];
        for (int adj : adjacencyList[vertex]) {
            max = Math.max(recursive(adjacencyList, adj, memo) + 1, max);
        }
        memo[vertex] = max;
        return max;
    }
    // End. Memorization recursion

    // Start. Dynamic programming
    public static int dynamicProgramming(List<Integer>[] adjacencyList, List<Integer>[] backAdjacencyList, int N) {
        int[] memo = new int[N+1];

        Arrays.fill(memo, 0);
        Queue<Integer> queue = new LinkedList<>();
        queue.addAll(firstVertexes(adjacencyList));
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            backAdjacencyList[vertex]
                    .stream()
                    .forEach(adj -> {
                        queue.add(adj);
                    });

            if (adjacencyList[vertex].isEmpty()) {
                memo[vertex] = 0;
            } else {
                int max = adjacencyList[vertex].stream().map(adj -> 1 + memo[adj]).max(Integer::compareTo).get();
                memo[vertex] = Math.max(max, memo[vertex]);
            }
        }

        return Arrays.stream(memo).max().getAsInt();
    }

    public static List<Integer> firstVertexes(List<Integer>[] adjacencyList) {
        return IntStream.range(1, adjacencyList.length)
                .boxed()
                .filter(index -> adjacencyList[index].isEmpty())
                .collect(Collectors.toList());
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
