package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * URL: https://codeforces.com/contest/1337/problem/C
 * rating: 1700
 */
public class LinovaAndKingdom {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void solution() {
        FastReader reader = new FastReader(System.in);
        boolean[] visited = new boolean[200005];
        int[] numChildren = new int[200005];
        int[] levels = new int[200005];


        int numTests = 1;
        for (int i = 0; i < numTests; i++) {
            int n = reader.nextInt();
            int k = reader.nextInt();

            Arrays.fill(visited, 0, n + 1, false);
            Map<Integer, List<Integer>> adjacentVertexList = new HashMap<>();

            for (int s = 0; s < n; s++) {
                adjacentVertexList.put(s + 1, new ArrayList<>());
            }

            for (int s = 0; s < n - 1; s++) {
                int u = reader.nextInt();
                int v = reader.nextInt();
                adjacentVertexList.get(u).add(v);
                adjacentVertexList.get(v).add(u);
            }

            getNumChildren(1, adjacentVertexList, visited, numChildren, levels, 0);
            List<Triple> list = new ArrayList<>();
            for (int s = 1; s <= n; s++) {
                list.add(new Triple(s, numChildren[s], levels[s]));
            }
            Collections.sort(list);
            long maxHappiness = 0;
            for (int tmp = 0; tmp < k; tmp++) {
                var triple = list.get(tmp);
                maxHappiness += levels[triple.id] - numChildren[triple.id];
            }
            pw.println(maxHappiness);

        }
        pw.close();
    }

    public static int getNumChildren(Integer vertex,
                                     Map<Integer, List<Integer>> adjacentVertexList,
                                     boolean[] visited,
                                     int[] numChildren,
                                     int[] levels,
                                     int level
                                  ) {
        levels[vertex] = level;
        visited[vertex] = true;
        int num = 0;
        for (Integer neighbor : adjacentVertexList.get(vertex)) {
            if (visited[neighbor]) continue;
            num += 1 + getNumChildren(neighbor, adjacentVertexList, visited, numChildren, levels, level + 1);
        }
        numChildren[vertex] = num;
        return num;
    }

    static class Triple implements Comparable<Triple> {
        int id;
        int numChildren;
        int level;

        public Triple(int id, int numChildren, int level) {
            this.id = id;
            this.numChildren = numChildren;
            this.level = level;
        }

        @Override
        public int compareTo(Triple o) {
           return numChildren - level - (o.numChildren - o.level);
        }
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
