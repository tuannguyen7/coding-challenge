package leetcodingchallenge.week1;

import java.util.List;

public class LeftMostColumnWithAtLeastOne {


    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        List<Integer> mn = binaryMatrix.dimensions();
        int m = mn.get(0);
        int n = mn.get(1);
        int leftMost = n;
        for (int i = 0; i < m; i++) {
            int lower = lowerBoundY(binaryMatrix, i, leftMost);
            if (lower == 0)
                return lower;
            else if (lower != -1)
                leftMost = Math.min(leftMost, lower);
        }
        return leftMost == n ? -1 : leftMost;
    }

    public int lowerBoundY(BinaryMatrix binaryMatrix, int x, int hi) {
        if (binaryMatrix.get(x, hi - 1) == 0)
            return -1;
        int lo = 0;
        int ans = -1;
        while (lo < hi) {
            int mid = lo + (hi - lo)/2;
            if (binaryMatrix.get(x, mid) >= 1) {
                ans = mid;
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }
        return ans;
    }

    public static void main(String[] args) {
    }

    interface BinaryMatrix {
         int get(int x, int y);
         List<Integer> dimensions();
    }
}
