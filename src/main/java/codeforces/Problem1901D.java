package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// https://codeforces.com/problemset/problem/1901/D
public class Problem1901D {

    public static void solution() {
        FastReader reader = new FastReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int T = 1;

        for (; T > 0; T--) {
            int n = reader.nextInt();
            List<Integer> monsters = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                monsters.add(reader.nextInt());
            }
            solve2(monsters);
        }
        pw.close();
    }

    public static void solve2(List<Integer> healths) {
        int n = healths.size();
        long[] prefixMaxes = new long[healths.size()];
        long[] suffixMaxes = new long[healths.size()];
        prefixMaxes[0] = healths.get(0) + (n - 1 - 0); // health(i) + (n - 1 - i)
        suffixMaxes[suffixMaxes.length-1] = healths.get(n - 1) + (n - 1); // health(i) + i

        for (int i = 1; i < prefixMaxes.length; i++) {
            prefixMaxes[i] = Math.max(prefixMaxes[i-1], healths.get(i) + (n - 1 - i));
        }

        for (int i = suffixMaxes.length-2; i>= 0; i--) {
            suffixMaxes[i] = Math.max(suffixMaxes[i+1], healths.get(i) + i);
        }

        long ans = Long.MAX_VALUE;
        for (int i = 0; i < healths.size(); i++) {
            ans = Math.min(ans, solve2(healths, i, prefixMaxes, suffixMaxes));
        }
        System.out.println(ans);
    }

    public static long solve2(List<Integer> healths, int startIndex, long[] prefixMax, long[] suffixMax) {
        long leftMax = startIndex > 0 ? prefixMax[startIndex - 1] : 0;
        long rightMax = startIndex < healths.size() - 1 ? suffixMax[startIndex + 1] : 0;
        return Math.max(Math.max(healths.get(startIndex), leftMax), rightMax);
    }

//    /**
//     * O(n^2) solution
//     * */
//    public static void solve(List<Integer> healths) {
//        long rs = Long.MAX_VALUE;
//        for (int i = 0; i < healths.size(); i++) {
//            rs = Math.min(rs, solve(healths, i));
//        }
//
//        System.out.println(rs);
//    }
//
//    public static long solve(List<Integer> healths, int startIndex) {
//        int n = healths.size();
//        long[] needed = new long[n];
//        for (int i = 0; i < needed.length; i++) {
//            if (i < startIndex) {
//                // this is the formula: needed[i] = (n - 1 - startIndex) + (startIndex - i) + healths.get(i);
//                // which is shorten: needed[i] = (n - 1 - i) + heaths.get(i)
//                needed[i] = (n - i - 1) + healths.get(i);
//            } else if (i > startIndex){
//                // this is the formula: needed[i] = (startIndex) + (i - startIndex) + healths.get(i);
//                // which is shorten: needed[i] = i + healths.get(i)
//                needed[i] = i + healths.get(i);
//            } else {
//                needed[i] = healths.get(i);
//            }
//        }
//        long rs = Arrays.stream(needed).max().getAsLong();
//        return rs;
//    }

    static class Monster {
        int index;
        int health;

        public Monster(int index, int health) {
            this.health = health;
            this.index = index;
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
