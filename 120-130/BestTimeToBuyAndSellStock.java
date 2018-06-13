class Solution {
    public int maxProfit(int[] prices) {
        int smallestFromLeft = Integer.MAX_VALUE;
        int maxProfit = 0;
        for (int i = 0; i < prices.length; i++) {
            if (prices[i] < smallestFromLeft) {
                smallestFromLeft = prices[i];
            }
            maxProfit = Math.max(maxProfit, prices[i] - smallestFromLeft);
        }
        return maxProfit;
    }
}
