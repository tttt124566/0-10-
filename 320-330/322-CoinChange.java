/*

You are given coins of different denominations and a total amount of money amount. Write a function to compute the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.

Example 1:

Input: coins = [1, 2, 5], amount = 11
Output: 3 
Explanation: 11 = 5 + 5 + 1
Example 2:

Input: coins = [2], amount = 3
Output: -1
Note:
You may assume that you have an infinite number of each kind of coin.

*/
class Solution {
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (i >= coins[j] && dp[i - coins[j]] != Integer.MAX_VALUE) {
                    dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
                }
            }   
        }
        
        if (dp[amount] == Integer.MAX_VALUE) {
            return -1; 
        } else {
            return dp[amount];
        }
    }
}

class Solution {
    Map<Integer, Integer> map = new HashMap<>();
    public int coinChange(int[] coins, int amount) {
        if (map.containsKey(amount)) {
            return map.get(amount);
        }
        
        if (amount < 0) {
            return -1;
        }
        
        if (amount == 0) {
            return 0;
        }
        
        int curr = amount + 1;
        for (int i = 0; i < coins.length; i++) {
            int next = coinChange(coins, amount-coins[i]);
            if (next >= 0) {
                curr = Math.min(curr, next + 1);
            }
        }
        int finalCount = (curr == amount + 1) ? -1 : curr;
        map.put(amount, finalCount);
        return finalCount;
    }
}
