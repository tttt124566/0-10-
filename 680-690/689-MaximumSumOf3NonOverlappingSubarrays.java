/*
In a given array nums of positive integers, find three non-overlapping subarrays with maximum sum.

Each subarray will be of size k, and we want to maximize the sum of all 3*k entries.

Return the result as a list of indices representing the starting position of each interval (0-indexed). If there are multiple answers, return the lexicographically smallest one.

Example:
Input: [1,2,1,2,6,7,5,1], 2
Output: [0, 3, 5]
Explanation: Subarrays [1, 2], [2, 6], [7, 5] correspond to the starting indices [0, 3, 5].
We could have also taken [2, 1], but an answer of [1, 3, 5] would be lexicographically larger.
Note:
nums.length will be between 1 and 20000.
nums[i] will be between 1 and 65535.
k will be between 1 and floor(nums.length / 3).
*/

/*
dp[i][j] = the maximum before i with j non-overlapping subarrays (not necessarily ends at i)
dp[i][j] = max { dp[i - k][j - 1] + sum(i - k + 1... i - 1), dp[i - 1][j] } means for every subarray ends at index i, we either select it or not, if we select it, we can only select subarray ends <= i - k + 1, and the remaining subarray becomesj - 1. Otherwise, we don't select subarray ends at index i, the problem becomes finding 3 subarrays in [0..i - 1]
*/
class Solution {
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int m = 3;
        int[] sums = new int[n+1];
        int[][] max = new int[n+1][m+1];
        int[][] indices = new int[n+1][m+1];
        for (int i = 0; i < nums.length; i++) {
            sums[i + 1] = sums[i] + nums[i]; 
        }
        /*
        The outer loop is for the number of partitions - (for this problem it is 3)
        The inner loop tries to find the max sum and the starting index for each partition - starting from the end of the previous partition.
        So the first partition ends at k-1, so 2nd partition starts at k and 2nd partition ends at 2*(k-1), 3rd partition starts at 3k and ends at 3(k-1) and so on.
        */
        for (int i = 1; i <= m; i++) {
            for (int j = k * i; j <= n; j++) {
                if (max[j - k][i - 1] + sums[j] - sums[j - k] > max[j - 1][i]) {
                    indices[j][i] = j - k;
                    max[j][i] = max[j-k][i-1]+sums[j]-sums[j-k];
                } else {
                    max[j][i] = max[j-1][i];
                    indices[j][i] = indices[j-1][i];
                }
            }
        }

        int[] ret = new int[m];
        ret[m-1] = indices[n][m];
        for (int i = m-2; i >= 0; i--)
            ret[i] = indices[ret[i+1]][i+1];
        return ret;
    }
}
