package leetcode;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Problem201 {
    public int rangeBitwiseAnd(int m, int n) {
        String binM = Integer.toBinaryString(m);
        String binN = Integer.toBinaryString(n);
        int lenDiff = binN.length() - binM.length();
        String prefixZeroes = IntStream.range(0, lenDiff).mapToObj(ignore -> "0").collect(Collectors.joining());
        binM = prefixZeroes + binM;
        StringBuilder common = new StringBuilder();
        int i = 0;
        for (; i < binM.length() && binM.charAt(i) == binN.charAt(i); i++) {
            common.append(binM.charAt(i));
        }
        for (; i< binM.length(); i++)
            common.append(0);
        return Integer.valueOf(common.toString(), 2);
    }

    public static void main(String[] args) {
        new Problem201().rangeBitwiseAnd(5, 7);
    }
}
