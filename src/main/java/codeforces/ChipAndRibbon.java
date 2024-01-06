package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

//https://codeforces.com/contest/1901/problem/B
public class ChipAndRibbon {

    public static void solution() {
        FastReader reader = new FastReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int T = reader.nextInt();

        for (; T > 0; T--) {
            int n = reader.nextInt();
            List<Long> chips = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                chips.add(reader.nextLong());
            }
            solve(chips);
        }
        pw.close();
    }

    public static void solve2(List<Long> chips) {
        long count = chips.get(0) - 1;
        for (int i = 1; i < chips.size(); i++) {
            if (chips.get(i) > chips.get(i-1)) {
                count += chips.get(i) - chips.get(i-1);
            }
        }
        System.out.println(count);
    }


    public static void solve(List<Long> chips) {
        long res = calTeleport(chips, 0, chips.size() - 1, 0) - 1;
        System.out.println(res);
    }

    public static long calTeleport(List<Long> chips, int l, int r, long base) {
        if (l > r) {
            return 0;
        }
        if (l == r) {
            return chips.get(l) - base;
        }
        int minIndex = l;
        for (int i = l + 1; i <= r; i++) {
            if (chips.get(i) < chips.get(minIndex)) {
                minIndex = i;
            }
        }

        long minV = chips.get(minIndex);
        long numTele = minV - base;
        return numTele + calTeleport(chips, l, minIndex - 1, minV) + calTeleport(chips, minIndex + 1, r, minV);
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
