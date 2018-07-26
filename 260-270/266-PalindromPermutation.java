/*
Given a string, determine if a permutation of the string could form a palindrome.

Example 1:

Input: "code"
Output: false
Example 2:

Input: "aab"
Output: true
Example 3:

Input: "carerac"
Output: true

*/

class Solution {
    public boolean canPermutePalindrome(String s) {
        int[] p = new int[256];
        for (int i = 0; i < s.length(); i++) {
            p[s.charAt(i)]++;
        }
        int oddNums = 0;
        for (int i = 0; i < 256; i++) {
            if (p[i] % 2 == 1) {
                oddNums++;
            }
        }
        return s.length() % 2 == 0 && oddNums == 0 || s.length() % 2 == 1 && oddNums == 1;
    }
}
