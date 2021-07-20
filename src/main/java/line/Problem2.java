package line;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;

/**
 * Name: Problem2
 * URL:
 * rating:
 * status: inprogress
 */
public class Problem2 {

    public static void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split("\\s");
        double a = Double.parseDouble(input[0])/2;
        double b = Double.parseDouble(input[1]);
        double c = Math.sqrt(a*a + b*b);
        double r = a*b/c;
        System.out.println(String.format("%.4f", r));
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
