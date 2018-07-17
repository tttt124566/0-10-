/*
Given a string s, find the longest palindromic substring in s. You may assume that the maximum length of s is 1000.

Example 1:

Input: "babad"
Output: "bab"
Note: "aba" is also a valid answer.
Example 2:

Input: "cbbd"
Output: "bb"
*/

class Solution {
    public String longestPalindrome(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int i = 0; i < s.length(); i++) {
            dp[i][i] = true;
        }
        
        int length = 1;
        int start = 0;
        for (int i = 1; i < s.length(); i++) {
            for (int j = i - 1; j >= 0; j--) {
                if ((i - j < 2 || dp[i - 1][j + 1]) && s.charAt(i) == s.charAt(j)) {
                    dp[i][j] = true;
                    if (dp[i][j] && (i - j + 1  > length)) {
                        start = j;
                        length = i - j + 1;
                    }
                }
            }
        }
        
        return s.substring(start, start + length);
    }
}
