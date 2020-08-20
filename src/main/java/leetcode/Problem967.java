package leetcode;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Problem967 {

    private static final Logger log = LoggerFactory.getLogger(Problem967.class);

    public int[] numsSameConsecDiff(int N, int K) {
        List<Integer> ans = new ArrayList<>();
        if (N == 1) {
            ans.addAll(buildNumber(N, K, new StringBuilder("0")));
        }
        for (char i = '1'; i <= '9'; i++) {
            ans.addAll(buildNumber(N, K, new StringBuilder("" + i)));
        }

        int[] finalAns = new int[ans.size()];
        for (int i = 0; i < ans.size(); i++) {
            finalAns[i] = ans.get(i);
        }

        return finalAns;
    }

    public List<Integer> buildNumber(int N, int K, StringBuilder curNumber) {

        if (curNumber.length() == N) {
            return Arrays.asList(Integer.parseInt(curNumber.toString()));
        }
        List<Integer> result = new ArrayList<>();
        char last = curNumber.charAt(curNumber.length() - 1);
        if (last + K <= '9') {
            char letter = (char)(last + K);
            curNumber.append(letter);
            result.addAll(buildNumber(N, K, curNumber));
            curNumber.deleteCharAt(curNumber.length() - 1);
        }

        if (K != 0 && last - K >= '0') {
            char letter = (char)(last - K);
            curNumber.append(letter);
            result.addAll(buildNumber(N, K, curNumber));
            curNumber.deleteCharAt(curNumber.length() - 1);
        }

        return result;
    }

    public static void main(String[] args) {
        log.info("{}", new Problem967().numsSameConsecDiff(2,1));;
    }
}
