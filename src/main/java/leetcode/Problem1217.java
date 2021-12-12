package leetcode;

public class Problem1217 {
    public int minCostToMoveChips(int[] position) {
        int evenCount = 0;
        int oddCount = 0;
        for (int p : position) {
            if (p%2 == 0)
                evenCount++;
            else
                oddCount++;
        }
        return Math.min(evenCount, oddCount);
    }
}
