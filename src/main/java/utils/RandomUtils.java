package utils;

import java.util.List;
import java.util.Random;

public class RandomUtils {

    public static int[] generateInteger(int n) {
        int[] gen = new int[n];
        Random random = new Random();
        for (int i = 0; i < n; i++)
            gen[i] = random.nextInt();
        return gen;
    }
}
