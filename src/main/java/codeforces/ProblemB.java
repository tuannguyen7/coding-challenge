package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

public class ProblemB {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void solution() {
        FastReader reader = new FastReader(System.in);
        int numTests = reader.nextInt(); // reads integer
        for (int i = 0; i < numTests; i++) {
            int size = reader.nextInt();
            List<Integer> a = new ArrayList<>();
            List<Integer> d = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                a.add(reader.nextInt());
            }

            for (int j = 0; j < size; j++) {
                d.add(reader.nextInt());
            }
            solve(a, d);
        }
    }

    public static void solve(List<Integer> a, List<Integer> d) {
        int curL = 0;
        int maxL = 0;
        int maxR = 0;

        for (int i = 1; i < d.size(); i++) {
            if (d.get(i) < d.get(i-1)) {
                int curR = i - 1;
                // check if this is a valid array
                boolean valid= false;
                for (int j = curL; j <= curR; j++) {
                    if (!a.get(j).equals(d.get(j))) {
                        valid = true;
                        break;
                    }
                }
                if (valid) {
                    if ( (curR - curL) > (maxR - maxL)) {
                        maxL = curL;
                        maxR = curR;
                    }
                }

                curL = i;
            }
        }

        int curR = d.size() - 1;
        boolean valid= false;
        for (int j = curL; j <= curR; j++) {
            if (!a.get(j).equals(d.get(j))) {
                valid = true;
                break;
            }
        }
        if (valid) {
            if ( (curR - curL) > (maxR - maxL)) {
                maxL = curL;
                maxR = curR;
            }
        }

        System.out.println("" + (maxL+1) + " " + (maxR+1));
    }

    public static void main(String[] args) {
        solution();
        pw.close();
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
