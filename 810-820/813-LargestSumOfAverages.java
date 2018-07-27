/*
We partition a row of numbers A into at most K adjacent (non-empty) groups, then our score is the sum of the average of each group. What is the largest score we can achieve?

Note that our partition must use every number in A, and that scores are not necessarily integers.

Example:
Input: 
A = [9,1,2,3,9]
K = 3
Output: 20
Explanation: 
The best choice is to partition A into [9], [1, 2, 3], [9]. The answer is 9 + (1 + 2 + 3) / 3 + 9 = 20.
We could have also partitioned A into [9, 1], [2], [3, 9], for example.
That partition would lead to a score of 5 + 2 + 6 = 13, which is worse.
 

Note:

1 <= A.length <= 100.
1 <= A[i] <= 10000.
1 <= K <= A.length.
Answers within 10^-6 of the correct answer will be accepted as correct.

*/

class Solution {
    public double largestSumOfAverages(int[] A, int K) {
        int[] sum = new int[A.length];
        for (int i = 0;i < A.length; i++) sum[i] = A[i] + (i > 0 ? sum[i-1] : 0); 
        double[][] dp = new double[A.length][K+1];
        return h(A, K, sum, dp, A.length, 0);
    }
    
    public double h(int[] A, int k, int[] sum, double[][] dp, int len, int s) {
        if (dp[s][k] != 0) return dp[s][k];
        if (k == 1){
            dp[s][k] = ((double)(sum[len-1] - sum[s] + A[s]) / (len-s));
            return dp[s][k];
        }
        for (int i = s; i + k <= len ; i++) {
            dp[s][k] = Math.max(dp[s][k], ((double) (sum[i] - sum[s] + A[s]) / (i - s + 1)) + h(A, k-1, sum, dp, len, i+1));
        }
        return dp[s][k];
    }
}
