package leetcode;

import java.util.ArrayList;
import java.util.List;

public class Problem1483 {

    int[][] ancestor;
    public Problem1483(int n, int[] parent) {
        int log2 = (int)Math.ceil(log2(n)) + 1;
        ancestor = new int[n][log2];
        for (int i = 0; i < n; i++) {
            ancestor[i][0] = parent[i];
        }

        for (int j = 1; j < log2; j++) {
            for (int i = 0; i < n; i++) {
                int nextAncestor = ancestor[i][j-1];
                if (nextAncestor == -1) {
                    ancestor[i][j] = nextAncestor;
                } else {
                    ancestor[i][j] = ancestor[nextAncestor][j-1];
                }
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        List<Integer> kBreakdown = findLogs(k);
        int curAncestor = node;
        for (int l : kBreakdown) {
            if (curAncestor == -1) {
                return curAncestor;
            } else {
                curAncestor = ancestor[curAncestor][l];
            }
        }

        return curAncestor;
    }

    public List<Integer> findLogs(double k) {
        List<Integer> l = new ArrayList<>();
        int log = (int)Math.floor(log2(k));
        while (k > 0) {
            l.add(log);
            k = k - Math.pow(2, log);
            log = (int)Math.floor(log2(k));
        }
        return l;
    }

    public double log2(double x) {
        return Math.log(x) / Math.log(2);
    }
}
