package leetcode;

public class Problem69 {

    // Solution1. Newton's method
    public int mySqrt(int x) {
        if (x == 0)
            return 0;
        int lastGuess = 1;
        while (!(lastGuess*lastGuess <= x && (lastGuess+1)*(lastGuess+1) > x)) {
            lastGuess = (lastGuess + x/lastGuess)/2;
        }
        return lastGuess;
    }

    // Solution2. Binary search
    public int mySqrt2(int x) {
        int lo = 1;
        int hi = x;
        int mid = lo + (hi - lo)/2;
        int ans = x;
        while (hi >= lo) {
            int divided = x / mid;
            if (divided == mid) {
                return mid;
            } else if (divided > mid) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
            mid = lo + (hi - lo)/2;
        }
        return ans;
    }

    public static void main(String[] args) {
        int sqr = new Problem69().mySqrt2(7);
        System.out.println(sqr);
    }
}
