package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;

public class ConstantPalindromeSum {

    static PrintWriter pw = new PrintWriter(System.out);

    public static void solution() {
        FastReader reader = new FastReader(System.in);
        int[] arr = new int[200001];
        int[] safePoints = new int[400004];
        int numTests = reader.nextInt(); // reads integer
        for (int i = 0; i < numTests; i++) {
            int n = reader.nextInt();
            int k = reader.nextInt();
            for (int s = 0; s < n; s++)
                arr[s] = reader.nextInt();

            Arrays.fill(safePoints, 0, k*2 + 1, 0);
            solve(arr, safePoints, n, k);
        }
    }

    public static void solve(int[] arr, int[] safePoints, int n, int k) {
        Map<Integer, Integer> sumCounts = new HashMap<>();

        for (int i = 0; i < n/2; i++) {
            int sum = arr[i] + arr[n-1-i];
            if (!sumCounts.containsKey(sum))
                sumCounts.put(sum, 1);
            else
                sumCounts.put(sum, sumCounts.get(sum) + 1);
        }

        for (int i = 0; i < n/2; i++) {
            int min = Math.min(arr[i], arr[n-1-i]);
            int max = Math.max(arr[i], arr[n-1-i]);
            if (min + k >= max) {
                safePoints[min + 1] += 1;
                safePoints[max + k + 1] -= 1;
            } else {
                safePoints[min + 1] += 1;
                safePoints[min + k + 1] -= 1;
                safePoints[max + 1] += 1;
                safePoints[max + k + 1] -= 1;
            }
        }

        for (int i = 1; i < k*2 + 1; i++) {
            safePoints[i] += safePoints[i-1];
        }

        int minReplacement = n;
        for (int i = 1; i < k*2 + 1; i++) {
            int safe = safePoints[i];
            int notSafe = n/2 - safePoints[i];
            int val = safe + notSafe*2;
            minReplacement = Math.min(minReplacement, val);
        }

        for (var entry : sumCounts.entrySet()) {
            int sum = entry.getKey();
            int count = entry.getValue();
            int safe = safePoints[sum];
            int notSafe = n/2 - safePoints[sum];
            minReplacement = Math.min(safe - count + notSafe * 2, minReplacement);
        }
        pw.println(minReplacement);
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
