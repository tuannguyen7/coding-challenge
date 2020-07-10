package atcoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Name: Knapsack1
 * URL: https://atcoder.jp/contests/dp/tasks/dp_d
 * rating:
 */
public class Knapsack1 {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void solution() {
        FastReader reader = new FastReader(System.in);

        int numTests = 1;
        for (int i = 0; i < numTests; i++) {
            int N = reader.nextInt();
            int W = reader.nextInt();
            int[] ns = new int[N];
            int[] ws = new int[N];
            long[][] dp = new long[N][W + 1];
            for (int ni = 0; ni < N; ni++) {
                ws[ni] = reader.nextInt();
                ns[ni] = reader.nextInt();
            }
            for (int ni = 0; ni < N; ni++)
                Arrays.fill(dp[ni], 0);
            long ans =solve(ns, ws, dp, N-1, W);
            long ans2 =solve2(N, W, ns, ws);
            pw.println(ans);
            pw.println(ans2);
        }
        pw.close();
    }

    // Knapsack
    public static long solve(int[] ns, int[] ws, long[][] dp, int index, int curW) {
        if (index < 0) {
            return 0;
        }
        if (dp[index][curW] != 0)
            return dp[index][curW];

        long bestValue = solve(ns, ws, dp, index - 1, curW);
        if (curW >= ws[index])
            bestValue = Math.max(bestValue, ns[index] + solve(ns, ws, dp, index - 1, curW - ws[index]));

        dp[index][curW] = bestValue;
        return bestValue;
    }

    public static long solve2(int N, int W, int[] ns, int[] ws) {
        long[][] dp = new long[N+1][W+1];
        for (int n = 0; n <= N; n++) {
            dp[n][0] = 0;
        }
        for (int w = 0; w <= W; w++) {
            dp[0][w] = 0;
        }

        for (int ni = 1; ni <= N; ni++) {
            for (int wi = 1; wi <= W; wi++) {
                long val = dp[ni - 1][wi];
                if (wi >= ws[ni-1]) {
                    val = Math.max(val, dp[ni - 1][wi - ws[ni-1]] + ns[ni-1]);
                }
                dp[ni][wi] = val;
            }
        }

        return dp[N][W];

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
