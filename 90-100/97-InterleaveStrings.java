/*
Given s1, s2, s3, find whether s3 is formed by the interleaving of s1 and s2.

Example 1:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbcbcac"
Output: true
Example 2:

Input: s1 = "aabcc", s2 = "dbbca", s3 = "aadbbbaccc"
Output: false
*/

class Solution {
    public boolean isInterleave(String s1, String s2, String s3) {
        if ((s1.length() + s2.length()) != s3.length()) {
            return false;
        }
        
        boolean[][] interleave = new boolean[s1.length() + 1][s2.length() + 1];
        interleave[0][0] = true;
        for (int i = 1; i < s1.length() + 1; i++) {
            interleave[i][0] = interleave[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        }
        
        for (int j = 1; j < s2.length() + 1; j++) {
            interleave[0][j] = interleave[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        }
        
        for (int i = 1; i < s1.length() + 1; i++) {
            for (int j = 1; j < s2.length() + 1; j++) {
                interleave[i][j] = interleave[i - 1][j] && (s1.charAt(i - 1) == s3.charAt(i + j - 1)) || interleave[i][j - 1] && (s2.charAt(j - 1) == s3.charAt(i + j - 1));
            }
        }
        return interleave[s1.length()][s2.length()];
    }
}
