/*
Given a 2D binary matrix filled with 0's and 1's, find the largest square containing only 1's and return its area.

Example:

Input: 

1 0 1 0 0
1 0 1 1 1
1 1 1 1 1
1 0 0 1 0

Output: 4
*/

class Solution {
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return 0;
        }
        int maxSize = 0;
        int[][] dp = new int[matrix.length][matrix[0].length];
        
        for (int i = 0; i < matrix.length; i++) {
            dp[i][0] = matrix[i][0] == '1' ? 1 : 0;
            maxSize = Math.max(maxSize, dp[i][0]);
        }
        
        for (int j = 0; j < matrix[0].length; j++) {
            dp[0][j] = matrix[0][j] == '1' ? 1 : 0;
            maxSize = Math.max(maxSize, dp[0][j]);
        }
        
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1') {
                    dp[i][j] = Math.min(Math.min(dp[i -1][j], dp[i][j - 1]), dp[i - 1][j - 1]) + 1;
                    maxSize = Math.max(dp[i][j], maxSize);
                }
            }
        }
        
        return maxSize * maxSize;
    }
}
