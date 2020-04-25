package codeforces;


import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;

/** URL: https://codeforces.com/problemset/problem/1335/C rank: 1100 */
public class TwoTeamsComposing {


  public static int solve(int[] skills, int[] count, int size) {

    for (int i = 0; i < size; i++) {
        count[skills[i] - 1] += 1;
    }
    int maxSame = 0;
    int numDistinct = 0;
    for (int i = 0; i < size; i++) {
      maxSame = Math.max(count[i], maxSame);
      if (count[i] != 0)
        numDistinct++;
    }

    if (maxSame < numDistinct) return maxSame;
    else if (maxSame > numDistinct) return numDistinct;
    else return numDistinct - 1;
  }

  public static void real() {
    FastReader reader = new FastReader(System.in);
    PrintWriter pw = new PrintWriter(System.out);

    int numTests = reader.nextInt(); // reads integer
    int[] skills = new int[200000];
    int[] counts = new int[200000];
    for (int i = 0; i < numTests; i++) {
      int teamSize = reader.nextInt();
      for (int s = 0; s < teamSize; s++) {
        skills[s] = reader.nextInt();
        counts[s] = 0;
      }

      int solution = solve(skills, counts, teamSize);
      pw.println(solution);
    }
    pw.close();
  }

  public static void main(String[] args) {
    real();
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
