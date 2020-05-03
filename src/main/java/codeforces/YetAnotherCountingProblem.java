package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Name: YetAnotherCountingProblem
 * URL: https://codeforces.com/contest/1342/problem/C
 * rating:
 */
public class YetAnotherCountingProblem {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void solution() {
        FastReader reader = new FastReader(System.in);

        long[] cache = new long[40005];
        int numTests = reader.nextInt(); // reads integer
        for (int i = 0; i < numTests; i++) {
            int a = reader.nextInt();
            int b = reader.nextInt();
            int q = reader.nextInt();
            int lcm = lcm(a, b);

            for (int lcmi = 0; lcmi < lcm; lcmi++) {
                if (lcmi%a%b != lcmi%b%a) {
                    cache[lcmi] = 1;
                } else
                    cache[lcmi] = 0;
            }

            for (int lcmi = 1; lcmi < lcm; lcmi++) {
                cache[lcmi] += cache[lcmi - 1];
            }

            for (int s = 0; s < q; s++) {
                long l = reader.nextLong();
                long r = reader.nextLong();
                pw.print((calc(cache, r, lcm) - calc(cache, l - 1, lcm)) + " ");
            }
            pw.println();
        }
        pw.close();
    }

    // amount of integer from 0 -> n
    public static long calc(long[] cache, long m, int lcm) {
        int mModLCM = (int)(m%lcm);
        return m/lcm * cache[lcm - 1] + cache[mModLCM];
    }

    public static int gcd(int a, int b) {
        if (a == 0)
            return b;
        return gcd(b % a, a);
    }

    // Function to return LCM of two numbers
    public static int lcm(int a, int b) {
        return (a*b)/gcd(a, b);
    }

    public static void main(String[] args) {
        //System.out.println(lcm(45, 172));
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
