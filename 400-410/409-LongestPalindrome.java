/*
Given a string which consists of lowercase or uppercase letters, find the length of the longest palindromes that can be built with those letters.

This is case sensitive, for example "Aa" is not considered a palindrome here.

Note:
Assume the length of given string will not exceed 1,010.

Example:

Input:
"abccccdd"

Output:
7

Explanation:
One longest palindrome that can be built is "dccaccd", whose length is 7.
*/


class Solution {
    public int longestPalindrome(String s) {
        int[] chars = new int[256];
        for (int i = 0; i < s.length(); i++) {
            chars[s.charAt(i)]++;
        }
        
        boolean hasOdd = false;
        int count = 0;
        for (int i = 0; i < 256; i++) {
            if (chars[i] % 2 == 0) {
                count += chars[i]; 
            } else {
                count += (chars[i] - 1);
                hasOdd = true;
            }
        }
        
        if (hasOdd) {
            count++;
        }
        return count;
    }
}
