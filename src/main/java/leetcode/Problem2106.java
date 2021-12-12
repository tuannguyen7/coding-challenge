package leetcode;

import java.util.Arrays;

public class Problem2106 {

    public int maxTotalFruits(int[][] fruits, int startPos, int k) {
        int SIZE = 200001;
        int MAX_POS = fruits[fruits.length - 1][0];
        int[] amountFruits = new int[SIZE];
        int midFruit = 0;
        int maxFruit = 0;
        int lIndex = Math.max(0, startPos - (k/3) - 1);
        int restK = k - (startPos - lIndex);
        int rIndex = Math.min(lIndex + restK, MAX_POS);
        Arrays.fill(amountFruits, 0);
        for (int i = 0; i < fruits.length; i++) {
            amountFruits[fruits[i][0]] = fruits[i][1];
        }
        for (int i = lIndex; i <= rIndex; i++)
            midFruit += amountFruits[i];

        int curFruit = midFruit;
        maxFruit = curFruit;
        while (lIndex < startPos && rIndex <= MAX_POS - 1) {
            curFruit -= amountFruits[lIndex];
            if (rIndex + 1 > startPos) {
                curFruit += amountFruits[rIndex + 1];
            }
            if (rIndex + 2 > startPos && rIndex + 2 <= MAX_POS) {
                curFruit += amountFruits[rIndex + 2];
            }
            maxFruit = Math.max(maxFruit, curFruit);
            lIndex++;
            rIndex += 2;
        }

        rIndex = Math.min(startPos + (k/3), SIZE);
        restK = k - (rIndex - startPos);
        lIndex = Math.max(rIndex - restK, 0);
        midFruit = 0;
        for (int i = rIndex; i >= lIndex; i--)
            midFruit += amountFruits[i];

        curFruit = midFruit;
        maxFruit = Math.max(curFruit, maxFruit);
        while (lIndex >= 1 && rIndex > startPos) {
            curFruit -= amountFruits[rIndex];
            if (lIndex - 1 < startPos) {
                curFruit += amountFruits[lIndex - 1];
            }
            if (lIndex - 2 < startPos && lIndex - 2 >= 0) {
                curFruit += amountFruits[lIndex - 2];
            }
            maxFruit = Math.max(maxFruit, curFruit);
            rIndex--;
            lIndex -= 2;
        }
        return maxFruit;
    }

    public static void main(String[] args) {
        var app = new Problem2106();
        //int[][] fruits = {{0,7},{7,4},{9,10},{12,6},{14,8},{16,5},{17,8},{19,4},{20,1},{21,3},{24,3},{25,3},{26,1},{28,10},{30,9},{31,6},{32,1},{37,5},{40,9}};
        //int startPos = 21;
        //int k = 30;
        //int[][] fruits = {{2,8},{6,3},{8,6}};
        //int startPos = 5;
        //int k = 4;
        //int[][] fruits = {{2,8},{6,3},{8,6}};
        //int startPos = 2;
        //int k = 0;
        int[][] fruits = {{0,4}};
        int startPos = 10;
        int k = 5;
        System.out.println(app.maxTotalFruits(fruits, startPos, k));
    }
}
