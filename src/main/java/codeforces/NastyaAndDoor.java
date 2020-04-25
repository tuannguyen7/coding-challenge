package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * URL: https://codeforces.com/contest/1341/problem/B
 * rating:
 */
public class NastyaAndDoor {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void solution() {
        FastReader reader = new FastReader(System.in);
        int[] heights = new int[200001];
        int[] peaks = new int[200001];

        int numTests = reader.nextInt(); // reads integer
        for (int i = 0; i < numTests; i++) {
            int n = reader.nextInt();
            int k = reader.nextInt();
            for (int s = 0; s < n; s++)
                heights[s] = reader.nextInt();
            // Solution
            peaks[0] = 0;
            peaks[n - 1] = 0;
            solve(heights, n, k, peaks);
        }
        pw.close();
    }

    public static void solve(int[] arr, int size, int k, int[] peaks) {
        int minPart = 0;
        int leftBound = 0;

        for (int i = 1; i < size - 1; i++) {
            if (arr[i] > arr[i + 1] && arr[i] > arr[i-1]) {
                peaks[i] = 1;
            } else
                peaks[i] = 0;
        }

        for (int i = 0; i < k - 1; i++) {
            minPart += peaks[i];
        }

        int left = 1;
        int right = k;
        int curPart = minPart;
        while (right < size) {
            curPart -= peaks[left];
            curPart += peaks[right - 1];
            if (curPart > minPart) {
                minPart = curPart;
                leftBound = left;
            }
            left++;
            right++;
        }
        pw.println((minPart + 1) + " " + (leftBound + 1));

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
