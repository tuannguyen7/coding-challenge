package leetcode;

public class Problem121 {
    public int maxProfit(int[] prices) {
        int curMax = 0;
        int curPriceMin = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (prices[i] > curPriceMin) {
                curMax = Math.max(prices[i] -curPriceMin, curMax);
            } else if (prices[i] < curPriceMin) {
                curPriceMin = prices[i];
            }
        }
        return curMax;
    }

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        int maxProfit = new Problem121().maxProfit(prices);
        System.out.println(maxProfit);
    }
}
