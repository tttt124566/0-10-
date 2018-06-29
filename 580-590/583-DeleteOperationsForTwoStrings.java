/*
Given two words word1 and word2, find the minimum number of steps required to make word1 and word2 the same, where in each step you can delete one character in either string.

Example 1:
Input: "sea", "eat"
Output: 2
Explanation: You need one step to make "sea" to "ea" and another step to make "eat" to "ea".
Note:
The length of given words won't exceed 500.
Characters in given words can only be lower-case letters.
*/

class Solution {
    public int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] distance = new int[m + 1][n + 1];
        for (int i = 0; i < m + 1; i++) {
            distance[i][0] = i;
        }
        for (int j = 0; j < n + 1; j++) {
            distance[0][j] = j;
        }
        
        for (int i = 1; i < m + 1; i++) {
            for (int j = 1; j < n + 1; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    distance[i][j] = Math.min(distance[i - 1][j - 1] + 2, Math.min(distance[i - 1][j] + 1, distance[i][j - 1] + 1));
                }
            }
        }
        return distance[m][n];
    }
}
