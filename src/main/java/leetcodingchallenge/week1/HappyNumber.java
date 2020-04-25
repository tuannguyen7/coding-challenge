package leetcodingchallenge.week1;

import java.util.HashSet;
import java.util.Set;

public class HappyNumber {
    public int pdiFunction(int n) {
        int sum = 0;
        while (n > 0) {
            sum += Math.pow(n%10, 2);
            n /= 10;
        }
        return sum;
    }

    public boolean isHappy(int n) {
        Set<Integer> seenNumbers = new HashSet<>();
        while (n > 1 && !seenNumbers.contains(n)) {
            seenNumbers.add(n);
            n = pdiFunction(n);
        }
        return n == 1;
    }

    public static void main(String[] args) {
        System.out.println(new HappyNumber().isHappy(19));
    }
}
