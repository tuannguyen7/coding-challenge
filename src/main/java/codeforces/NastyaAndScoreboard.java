package codeforces;

import javax.lang.model.type.IntersectionType;
import javax.print.attribute.standard.NumberUp;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

/**
 * URL: https://codeforces.com/contest/1341/problem/D
 * rating:
 */
public class NastyaAndScoreboard {

    static PrintWriter pw = new PrintWriter(System.out);
    static Map<Integer, Map<Integer, Integer>> MAP = turnNumberIntoNumber();
    static Map<String, Integer> NUMS = helper();
    static String BEST;

    private static Map<String, Integer> helper() {
       Map<String, Integer> NUMS = new HashMap<>();
        String[] encoded = {"1110111", "0010010", "1011101", "1011011", "0111010", "1101011", "1101111", "1010010", "1111111", "1111011"};
        for (int i = 0; i < encoded.length; i++)
            NUMS.put(encoded[i],i);
        return NUMS;
    }

    public static void solution() {
        FastReader reader = new FastReader(System.in);

        int numTests = 1;
        //String[] digitals = new String[20001];
        int[] digitals = new int[200001];
        for (int i = 0; i < numTests; i++) {
            int n = reader.nextInt();
            int k = reader.nextInt();
            for (int s = 0; s < n; s++) {
                //digitals[s] = NUMS.get();
            }

            solve(digitals, n, k);
        }
        pw.close();
    }

    public static void solve(int[] nums, int size, int k) {
        recursive(nums, 0, size, k, new StringBuilder());
        pw.println(BEST);

    }

    private static String recursive(int[] nums, int index, int size, int k, StringBuilder curNumber) {
        if (k == 0) {
            if (curNumber.toString().compareTo(BEST) > 0) {
                return curNumber.toString();
            } else {
                return BEST;
            }
        }

        if (k < 0 || index == size)
            return BEST;

        curNumber.append(nums[index]);
        Map<Integer, Integer> convert = MAP.get(nums[index]);
        for (var entry : convert.entrySet()) {
            recursive(nums, index++, size, k - entry.getValue(), curNumber);
        }

        curNumber.deleteCharAt(curNumber.length() - 1);
        return "";
    }

    public static Map<Integer, Map<Integer, Integer>> turnNumberIntoNumber() {
        String[] encodedDigital = {"1110111", "0010010", "1011101", "1011011", "0111010", "1101011", "1101111", "1010010", "1111111", "1111011"};
        Map<Integer, Map<Integer, Integer>> result = new HashMap<>();
        for (int i = 0; i < encodedDigital.length; i++) {
            Map<Integer, Integer> convertCount = new HashMap<>();
            for (int j = 0; j < encodedDigital.length; j++) {
                convertCount.put(j, fromTo(encodedDigital[i], encodedDigital[j]));
            }
            result.put(i, convertCount);
        }
        return result;
    }

    private static int fromTo(String s1, String s2) {
        int count = 0;
        for (int i = 0; i < s1.length(); i++) {
            if (s1.charAt(i) == '0' && s2.charAt(i) == '1')
                count++;
            else if (s1.charAt(i) == '1' && s2.charAt(i) == '0')
                return 99999; // cannot convert
        }
        return count;
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
