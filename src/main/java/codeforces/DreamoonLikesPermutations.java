package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

/**
 * URL: https://codeforces.com/problemset/problem/1330/B
 * rank: 1400
 * */
public class DreamoonLikesPermutations {

    static PrintWriter pw;
    public static int solve(int[] a, int size) {
        Set<Integer> existedL1 = new HashSet<>();
        Set<Integer> existedL2 = new HashSet<>();
        Stack<Integer> maxL1Stack = new Stack<>();
        //int maxL1 = a[0];
        int lenL1 = 0;
        int maxL2 = -1;
        int lenL2 = 0;
        for (int i = 0; i < size; i++) {
            if (existedL1.contains(a[i])) {
                break;
            }
            existedL1.add(a[i]);
            if (maxL1Stack.isEmpty())
                maxL1Stack.push(a[i]);
            else if (a[i] > maxL1Stack.peek())
                maxL1Stack.push(a[i]);
            else {
                maxL1Stack.push(maxL1Stack.peek());
            }
            lenL1++;
        }

        for (int i = lenL1; i < size; i++) {
            if (existedL2.contains(a[i])) {
                break;
            }
            existedL2.add(a[i]);
            maxL2 = Math.max(maxL2, a[i]);
            lenL2++;
        }

        if (lenL1 == size || lenL1 + lenL2 < size) {
            pw.println("0");
            return 0;
        }

        int totalWays = 0;
        StringBuilder ways = new StringBuilder();
        while (lenL1 >= 1){

            // check if sastified
            if (maxL1Stack.peek() == lenL1 && maxL2 == lenL2) {
                totalWays++;
                ways.append(lenL1 + " " + lenL2 + "\n");
            }

            if (lenL1 == 1) // cannot move
                break;

            // move last value from l1 to l2
            int lastL1Element = a[lenL1 - 1];
            if (existedL2.contains(lastL1Element)) // cannot extend l2 more
                break;

            existedL2.add(lastL1Element);
            lenL2++;
            lenL1--;

            // update max
            maxL2 = Math.max(maxL2, lastL1Element);
            maxL1Stack.pop();
        }

        pw.println(totalWays);
        if (totalWays != 0) {
            pw.print(ways.toString());
        }
        return totalWays;
    }

    public static void solution() {
        FastReader reader = new FastReader(System.in);
        int T = reader.nextInt();
        int[] a = new int[200001]; // p1 + p2

        for (int i = 0; i < T; i++) {
            int size = reader.nextInt();
            for (int s = 0; s < size; s++)
                a[s] = reader.nextInt();

            // Algorithm
            solve(a, size);
        }

    }

    public static void test() {
        int[] a = {2, 1, 3, 4, 5, 6, 7, 8, 9, 1, 10, 2};
        int[] a2 = {2, 1, 3, 4, 5, 6, 7, 8, 9, 8};
        int[] a3 = {1, 2, 5, 2, 1, 3, 4};
        solve(a3, 7);
    }

    public static void main(String[] args) {
        pw = new PrintWriter(System.out);
        //solution();
        test();
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
