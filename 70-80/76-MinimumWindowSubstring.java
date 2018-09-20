/*
Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).

Example:

Input: S = "ADOBECODEBANC", T = "ABC"
Output: "BANC"
Note:

If there is no such window in S that covers all characters in T, return the empty string "".
If there is such window, you are guaranteed that there will always be only one unique minimum window in S.
*/

class Solution {
    public String minWindow(String s, String t) {
        int[] toBeFound = new int[256];
        int[] hasBeenFound = new int[256];
        int count = 0;
        int start = 0;
        int minStart = 0;
        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < t.length(); i++) {
            toBeFound[t.charAt(i)]++;
        }
        
        for (int i = 0; i < s.length(); i++) {
            if (toBeFound[s.charAt(i)] != 0) {
                hasBeenFound[s.charAt(i)]++;
                if (hasBeenFound[s.charAt(i)] <= toBeFound[s.charAt(i)]) {
                    count++;
                }
            }
            if (count == t.length()) {
                while(hasBeenFound[s.charAt(start)] > toBeFound[s.charAt(start)] || toBeFound[s.charAt(start)] == 0) {
                    if (hasBeenFound[s.charAt(start)] > toBeFound[s.charAt(start)]) {
                        hasBeenFound[s.charAt(start)]--;
                    }
                    start++;
                }
                if (i - start + 1 < minLength) {
                    minStart = start;
                    minLength = i - start + 1;
                }
            }
        }
        
        return minLength == Integer.MAX_VALUE ? "" : s.substring(minStart, minStart + minLength);
    }
}
