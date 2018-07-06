
public class Solution {
    public int getMoneyAmount(int n) {
        // minimum number of money to guarantee win for [i, j]
        int[][] dp = new int[n + 1][n + 1];
        for (int j = 2; j <= n; j++) {
            for (int i = j - 1; i > 0; i--) {
                if (i + 1 == j) dp[i][j] = i;
                else {
                    int globalMin = Integer.MAX_VALUE;
                    for (int k = i + 1; k < j; k++) {
                        // chosee k as our guess and pay k, assuming everytime is wrong
                        // after guess it goes to [i, k - 1] and [k + 1][j]
                        int localMax = k + Math.max(dp[i][k - 1], dp[k + 1][j]);
                        globalMin = Math.min(globalMin, localMax);
                    }
                    dp[i][j] = globalMin;
                }
            }
        }
        return dp[1][n];
    }
}
