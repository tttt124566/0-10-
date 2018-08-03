/*
Given a string s, partition s such that every substring of the partition is a palindrome.

Return the minimum cuts needed for a palindrome partitioning of s.

Example:

Input: "aab"
Output: 1
Explanation: The palindrome partitioning ["aa","b"] could be produced using 1 cut.
*/

class Solution {
    public int minCut(String s) {
        int c = s.length();
        int[] cut = new int[c + 1];
        for (int i = 0; i < c + 1; i++) {
            cut[i] = i - 1;
        }
        boolean[][] isPalindrome = new boolean[c][c];
        
        for (int i = 0; i < c; i++) {
            for (int j = 0; j <= i; j++) {
                if ((i - j < 2 || isPalindrome[i - 1][j + 1]) && s.charAt(i) == s.charAt(j)) {
                    isPalindrome[i][j] = true;
                    cut[i + 1] = Math.min(cut[j] + 1, cut[i + 1]);
                }
            }
        }
        
        return cut[c];
    }
}
