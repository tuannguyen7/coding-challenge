package codeforces;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class ExercisingWalk {


  public static void solution() {
    FastReader reader = new FastReader(System.in);
    PrintWriter pw = new PrintWriter(System.out);
    int T = reader.nextInt();

    for (int i = 0; i < T; i++) {
      int a = reader.nextInt();
      int b = reader.nextInt();
      int c = reader.nextInt();
      int d = reader.nextInt();

      int u = reader.nextInt();
      int v = reader.nextInt();
      int x1 = reader.nextInt();
      int y1 = reader.nextInt();
      int x2 = reader.nextInt();
      int y2 = reader.nextInt();

      // Algorithm
        int right = (b - a);
        int up = (d - c);
        if ((b > 0 || a > 0) && (x2 == x1) || (d > 0 || c > 0) && (y1 == y2)) // no space to move
          pw.println("NO");
        else if (u + right < x1 || u + right > x2 || v + up < y1 || v + up > y2) // false
          pw.println("NO");
        else
          pw.println("YES");

    }
    pw.close();
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
