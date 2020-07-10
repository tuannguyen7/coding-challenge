package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.InputMismatchException;
import java.util.PriorityQueue;

/**
 * Name: ConstructingTheArray
 * URL: https://codeforces.com/contest/1353/problem/D
 * rating:
 */
public class ConstructingTheArray {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void solution() {
        FastReader reader = new FastReader(System.in);

        int numTests = reader.nextInt(); // reads integer
        int[] a = new int[200001];
        for (int i = 0; i < numTests; i++) {
            int n = reader.nextInt();
            solve(n, a);
        }
        pw.close();
    }

    public static void solve(int n, int[] a) {
        PriorityQueue<Pair> queue = new PriorityQueue<>(Comparator.reverseOrder());
        queue.add(new Pair(0, n-1));
        for (int i = 0; i < n; i++) {
            Pair p = queue.poll();
            int mid = p.mid();
            if (mid > p.l) {
                queue.add(new Pair(p.l, mid - 1));
            }
            if (mid < p.r) {
                queue.add(new Pair(mid + 1, p.r));
            }
            a[mid] = i + 1;
        }
        for (int i = 0; i < n; i++)
            pw.print(a[i] + " ");
        pw.println();
    }

    static class Pair implements Comparable<Pair> {
        int l;
        int r;

        public Pair(int l, int r) {
            this.l = l;
            this.r = r;
        }

        public int len() {
            return r - l + 1;
        }

        public int mid() {
            return (r + l)/2;
        }

        @Override
        public int compareTo(Pair o) {
            if (len() != o.len())
                return len() - o.len();
            return o.l - l;
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
