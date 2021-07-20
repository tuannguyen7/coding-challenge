package line;

import java.io.*;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Name: Problem1
 * URL:
 * rating:
 * status: inprogress
 */
public class Problem1 {

    public static void solution() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int lines = Integer.parseInt(br.readLine().trim());
        for (int i = 0; i < lines; i++) {
            String line = br.readLine();
            String[] words = line.split("\\s");
            String finalOutput = Arrays.stream(words)
                    .map(String::trim)
                    .map(w -> reverse(w))
                    .collect(Collectors.joining(" "));
          System.out.println(finalOutput);
        }
    }

    public static String reverse(String word) {
        StringBuilder b = new StringBuilder(word);
        return b.reverse().toString();
    }

    public static void main(String[] args) throws Exception {
        solution();
    }
}
