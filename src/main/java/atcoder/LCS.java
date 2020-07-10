package atcoder;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Name: LCS
 * URL: https://atcoder.jp/contests/dp/tasks/dp_f
 * rating:
 */
public class LCS {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void solution() {
        FastReader reader = new FastReader(System.in);

        int numTests = 1;
        for (int i = 0; i < numTests; i++) {
            String s = reader.next();
            String t = reader.next();
            pw.println(solve(s, t));

        }
        pw.close();
    }

    public static String solve(String a, String b) {
        //String[][] dp = new String[a.length()][b.length()];
        //return recursive_memo(a, b, a.length() - 1, b.length() - 1, dp);
        return dynamicProgramming2(a, b);
    }

    // without memorization
    public static String recursive(String s, String t, int si, int ti) {
        if (si < 0 || ti < 0)
            return "";
        String lcs1 = recursive(s, t, si - 1, ti);
        String lcs2 = recursive(s, t, si, ti - 1);
        String lcs3 = "";
        if (s.charAt(si) == t.charAt(ti)) {
            lcs3 = recursive(s, t, si - 1, ti -1) + s.charAt(si);
        }
        String max = lcs1.length() > lcs2.length() ? lcs1 : lcs2;
        max = max.length() > lcs3.length() ? max : lcs3;
        return max;
    }

    // with memorization
    public static String recursive_memo(String s, String t, int si, int ti, String[][] dp) {
        if (si < 0 || ti < 0)
            return "";
        if (dp[si][ti] != null)
            return dp[si][ti];
        String lcs1 = recursive_memo(s, t, si - 1, ti, dp);
        String lcs2 = recursive_memo(s, t, si, ti - 1, dp);
        String lcs3 = "";
        if (s.charAt(si) == t.charAt(ti)) {
            lcs3 = recursive_memo(s, t, si - 1, ti -1, dp) + s.charAt(si);
        }
        String max = lcs1.length() > lcs2.length() ? lcs1 : lcs2;
        max = max.length() > lcs3.length() ? max : lcs3;
        dp[si][ti] = max;
        return max;
    }

    // 2-D array
    public static String dynamicProgramming(String s, String t) {
        String[][] dp = new String[s.length() + 1][t.length() + 1];

        for (int i = 0; i <= s.length(); i++)
            dp[i][0] = "";

        for (int i = 0; i <= t.length(); i++)
            dp[0][i] = "";

        for (int si = 1; si <= s.length(); si++)
            for (int ti = 1; ti <= t.length(); ti++) {
                dp[si][ti] =
                        dp[si - 1][ti].length() > dp[si][ti - 1].length()
                        ? dp[si - 1][ti]
                        : dp[si][ti - 1];
                if (s.charAt(si - 1) == t.charAt(ti - 1))
                    dp[si][ti] =
                            dp[si][ti].length() > (dp[si - 1][ti - 1].length() + 1)
                            ? dp[si][ti]
                            : (dp[si - 1][ti - 1] + s.charAt(si - 1));
            }

        return dp[s.length()][t.length()];
    }

    // 2-D array, lately construct answer
    public static String dynamicProgramming2(String s, String t) {
        int[][] dp = new int[s.length() + 1][t.length() + 1];

        for (int i = 0; i <= s.length(); i++)
            dp[i][0] = 0;

        for (int i = 0; i <= t.length(); i++)
            dp[0][i] = 0;

        for (int si = 1; si <= s.length(); si++)
            for (int ti = 1; ti <= t.length(); ti++) {
                dp[si][ti] =
                        dp[si - 1][ti] > dp[si][ti - 1]
                                ? dp[si - 1][ti]
                                : dp[si][ti - 1];
                if (s.charAt(si - 1) == t.charAt(ti - 1))
                    dp[si][ti] =
                            dp[si][ti] > (dp[si - 1][ti - 1] + 1)
                                    ? dp[si][ti]
                                    : (dp[si - 1][ti - 1] + 1);
            }

        return constructString(dp, s, t);
    }

    public static String constructString(int[][] dp, String s, String t) {
        StringBuilder ans = new StringBuilder();
        int si = s.length();
        int ti = t.length();
        while (si > 0 && si > 0) {
            if (dp[si][ti] == dp[si-1][ti]) {
                si -= 1;
            } else if (dp[si][ti] == dp[si][ti-1]) {
                ti -= 1;
            } else {
                si -= 1;
                ti -= 1;
                ans.append(Character.toString(s.charAt(si)));
            }
        }
        return ans.reverse().toString();
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
