package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * URL: https://codeforces.com/contest/1341/problem/C
 * rating:
 */
public class NastyaAndStrangeGenerator {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void solution() {
        FastReader reader = new FastReader(System.in);

        int[] permutations = new int[100001];
        int numTests = reader.nextInt(); // reads integer
        for (int i = 0; i < numTests; i++) {
            int n = reader.nextInt();
            for (int s = 0; s < n; s++)
                permutations[s] = reader.nextInt();
            if (solve(permutations, n))
                pw.println("YES");
            else
                pw.println("NO");

        }
        pw.close();
    }

    public static boolean solve(int[] permutations, int size) {
        int minValue = 1;
        int lastIndex = size - 1;
        while (lastIndex >= 0 && permutations[lastIndex] == minValue) {
            lastIndex--;
            minValue++;
        }

        // found index of min value
        while (lastIndex >= 0) {

            int curMinValueIndex = 0;
            for (int i = 0; i <= lastIndex; i++) {
                if (permutations[i] == minValue) {
                    curMinValueIndex = i;
                    break;
                }
            }

            // safe
            int index = curMinValueIndex;
            while (index <= lastIndex) {
                if (permutations[index] != minValue) {
                    return false;
                }
                index++;
                minValue++;
            }
            lastIndex = curMinValueIndex - 1;
        }

        return true;
    }

    public static void test() {
        int[] permutations = {1, 2, 3};
        int[] permutations1 = {2, 3, 1};
        int[] permutations2 = {3, 1, 2};
        int[] permutations3 = {1, 2, 3, 4};
        int[] permutations4 = {3, 4, 2, 1};
        int[] permutations5 = {4, 3, 2, 1};
        int[] permutations6 = {4, 3, 5, 2, 1};
        int[] permutations7 = {5, 3, 4, 2, 1};
        int[] permutations8 = {6, 5, 3, 4, 2, 1};

        System.out.println(solve(permutations, permutations.length));
        System.out.println(solve(permutations1, permutations1.length));
        System.out.println(solve(permutations2, permutations2.length));
        System.out.println(solve(permutations3, permutations3.length));
        System.out.println(solve(permutations4, permutations4.length));
        System.out.println(solve(permutations5, permutations5.length));
        System.out.println(solve(permutations6, permutations6.length));
        System.out.println(solve(permutations7, permutations7.length));
        System.out.println(solve(permutations8, permutations8.length));
    }

    public static void main(String[] args) {
        //test();
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
