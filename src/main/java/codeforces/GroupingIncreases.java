package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public class GroupingIncreases {

    public static void solve(int[] a) {
        List<Integer> l1 = new ArrayList<>();
        List<Integer> l2 = new ArrayList<>();
        l1.add(a[0]);
        for (int i = 1; i < a.length; i++) {
            int ai = a[i];
            int lastL1 = l1.get(l1.size() - 1);
            if (l2.isEmpty()) {
                if (ai > lastL1) {
                    l2.add(ai);
                } else {
                    l1.add(ai);
                }
            } else {
                int lastL2 = l2.get(l2.size() - 1);
                if (ai == lastL1) {
                    l1.add(ai);
                } else if (ai == lastL2) {
                    l2.add(ai);
                } else {
                    // ai != lastL1, lastL2
                    if (lastL1 > lastL2) {
                        if (ai > lastL1) {
                            l2.add(ai);
                        } else if (ai > lastL2){ // lastL2 < ai < lastL1
                            l1.add(ai);
                        } else { // ai < lastL2 < lastL1
                            l2.add(ai);
                        }
                    } else if (lastL2 > lastL1) {
                        if (ai > lastL2) {
                            l1.add(ai);
                        } else if (ai > lastL1){ // lastL1 < ai < lastL2
                            l2.add(ai);
                        } else { // ai < lastL1 < lastL2
                            l1.add(ai);
                        }
                    } else {  // lastL2 = lastL1
                        l1.add(ai); // can add ai to whatever list
                    }
                }
            }
        }
        System.out.println(calPenalty(l1) + calPenalty(l2));
    }

    public static int calPenalty(List<Integer> l) {
        if (l.isEmpty()) return 0;
        int pen = 0;
        for (int i = 1; i < l.size(); i++) {
            if (l.get(i - 1) < l.get(i)) pen++;
        }
        return pen;
    }
    public static void solution() {
        FastReader reader = new FastReader(System.in);
        int T = reader.nextInt();

        for (int i = 0; i < T; i++) {
            int size = reader.nextInt();
            int[] a = new int[size]; // p1 + p2
            for (int s = 0; s < size; s++)
                a[s] = reader.nextInt();

            // Algorithm
            solve(a);
        }

    }

    public static void test() {
        int[] a = {2, 1, 3, 4, 5, 6, 7, 8, 9, 1, 10, 2};
        int[] a2 = {2, 1, 3, 4, 5, 6, 7, 8, 9, 8};
        int[] a3 = {1, 2, 5, 2, 1, 3, 4};
    }

    public static void main(String[] args) {
        solution();
        //test();
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
