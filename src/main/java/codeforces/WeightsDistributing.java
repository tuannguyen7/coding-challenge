package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class WeightsDistributing {
    static PrintWriter pw = new PrintWriter(System.out);

    public static void solution() {
        FastReader reader = new FastReader(System.in);
        int numTests = reader.nextInt(); // reads integer
        int[] ms = new int[10];
        int[][] weights = new int[10][10];
        int[] optimalPath = new int[10];
        boolean[] visited = new boolean[10];


        for (int i = 0; i < numTests; i++) {
            int n = reader.nextInt();
            int m = reader.nextInt();
            int a = reader.nextInt();
            int b = reader.nextInt();
            int c = reader.nextInt();

            for (int tmp = 1; tmp <= n; tmp++) {
                Arrays.fill(weights[tmp], 0);
            }
            Arrays.fill(optimalPath, 99999);
            Arrays.fill(visited, 0, n + 1, false);

            for (int mi = 0; mi < m; mi++) {
                ms[mi] = reader.nextInt();
            }

            for (int mi =0; mi < m; mi++) {
                int v1 = reader.nextInt();
                int v2 = reader.nextInt();
                int w = ms[mi];
                weights[v1][v2] = w;
                weights[v2][v1] = w;
            }

            // solution
            PriorityQueue<Vertex> queue = new PriorityQueue<>();
            queue.add(new Vertex(a, 0));
            visited[a] = true;

            while (!queue.isEmpty()) {
                Vertex v = queue.poll();
                optimalPath[v.id] = Math.min(optimalPath[v.id], v.weight);
                visited[v.id] = true;
                for (int neighbor = 1; neighbor <= n; neighbor++) {
                    if (weights[v.id][neighbor] == 0 || visited[neighbor])
                        continue;
                    queue.remove(new Vertex(neighbor, 1));
                    optimalPath[neighbor] = Math.min(optimalPath[neighbor],
                            optimalPath[v.id] + weights[v.id][neighbor]);
                    queue.add(new Vertex(neighbor, optimalPath[neighbor]));
                }
            }

            int aToB = optimalPath[b];

            Arrays.fill(optimalPath, 99999);
            Arrays.fill(visited, 0, n + 1, false);
            queue = new PriorityQueue<>();

            queue.add(new Vertex(b, 0));
            visited[a] = true;

            while (!queue.isEmpty()) {
                Vertex v = queue.poll();
                optimalPath[v.id] = Math.min(optimalPath[v.id], v.weight);
                visited[v.id] = true;
                for (int neighbor = 1; neighbor <= n; neighbor++) {
                    if (weights[v.id][neighbor] == 0 || visited[neighbor])
                        continue;
                    queue.remove(new Vertex(neighbor, 1));
                    optimalPath[neighbor] = Math.min(optimalPath[neighbor],
                            optimalPath[v.id] + weights[v.id][neighbor]);
                    queue.add(new Vertex(neighbor, optimalPath[neighbor]));
                }
            }
            int bToC = optimalPath[c];
            pw.println(aToB + bToC);
        }
    }

    static class Vertex implements Comparable<Vertex> {
        int id;
        int weight;

        public Vertex(int id, int weight) {
            this.id = id;
            this.weight = weight;
        }

        @Override
        public int compareTo(Vertex o) {
            return weight - o.weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex vertex = (Vertex) o;
            return id == vertex.id;
        }
    }

    public static void main(String[] args) {
        solution();
        pw.close();
    }

    static class FastReader {
        InputStream is;
        private byte[] inbuf = new byte[1024];
        private int lenbuf = 0, ptrbuf = 0;

        public FastReader(InputStream is) {
            this.is = is;
        }

        public int readByte(){
            if(lenbuf == -1)throw new InputMismatchException();
            if(ptrbuf >= lenbuf){
                ptrbuf = 0;
                try {
                    lenbuf = is.read(inbuf);
                } catch (IOException e) {
                    throw new InputMismatchException();
                }
                if(lenbuf <= 0)
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
        public String next(){
            int b = skip();
            StringBuilder sb = new StringBuilder();
            while(!(isSpaceChar(b))) {
                sb.appendCodePoint(b);
                b = readByte();
            }
            return sb.toString();
        }
        public String nextLine(){
            int b = readByte();
            StringBuilder sb = new StringBuilder();
            while(b != '\n' || b != '\r'){
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
        public char[] next(int n){
            char[] buf = new char[n];
            int b = skip(), p = 0;
            while (p < n && !(isSpaceChar(b))) {
                buf[p++] = (char) b;
                b = readByte();
            }
            return n == p ? buf : Arrays.copyOf(buf, p);
        }
        public char nextChar () {
            return (char) skip();
        }
    }
}
