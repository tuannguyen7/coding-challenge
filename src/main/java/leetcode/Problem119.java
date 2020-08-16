package leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Problem119 {

    private static final Logger log = LoggerFactory.getLogger(Problem119.class);

    public List<Integer> getRow(int rowIndex) {
        int[] arr = new int[rowIndex + 1];
        Arrays.fill(arr, 1);
        for (int i = 0; i <= rowIndex; i++) {
            int a = 0;
            int b = 1;
            for (int j = 0; j < i; j++) {
                arr[j] = a + b;
                a = b;
                b = arr[j+1];
            }
        }

        return Arrays.stream(arr).boxed().collect(Collectors.toList());
    }

    public static void main(String[] args) {
        var result = new Problem119().getRow(3);
        log.info("{}", result);
    }
}
