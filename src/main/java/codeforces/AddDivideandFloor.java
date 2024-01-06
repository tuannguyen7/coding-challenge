package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;

public class AddDivideandFloor {

    public static void solution() {
        FastReader reader = new FastReader(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int T = reader.nextInt();

        while (T > 0) {
            int n = reader.nextInt();
            List<Integer> nums = new ArrayList<>();
            for (int ni = 0; ni < n; ni++) {
                nums.add(reader.nextInt());
            }
            solve(nums);
            T--;
        }
        pw.close();
    }

    public static void solve(List<Integer> nums) {
        int min = nums.stream().min(Integer::compare).get();
        int max = nums.stream().max(Integer::compare).get();
        List<Integer> res = logToEqual(min, max);
        System.out.println(res.size());
        if (res.size() <= nums.size() && !res.isEmpty()) {
            for (int v : res) {
                System.out.print(v + " ");
            }
            System.out.println();
        }
    }

    public static List<Integer> logToEqual(int a, int b) {
        List<Integer> rs = new ArrayList<>();
        while (true) {
            if (a == b) {
                break;
            }

            if (a % 2 != 0 && b % 2 == 0) {
                a += 1;
                b += 1;
                rs.add(1);
            } else {
                rs.add(0);
            }

            a = a/2;
            b = b/2;
        }

        return rs;
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
