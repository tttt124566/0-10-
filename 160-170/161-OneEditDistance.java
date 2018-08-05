/*
Given two strings s and t, determine if they are both one edit distance apart.

Note: 

There are 3 possiblities to satisify one edit distance apart:

Insert a character into s to get t
Delete a character from s to get t
Replace a character of s to get t
Example 1:

Input: s = "ab", t = "acb"
Output: true
Explanation: We can insert 'c' into s to get t.
Example 2:

Input: s = "cab", t = "ad"
Output: false
Explanation: We cannot get t from s by only one step.
Example 3:

Input: s = "1203", t = "1213"
Output: true
Explanation: We can replace '0' with '1' to get t.

*/


class Solution {
    public boolean isOneEditDistance(String s, String t) {
        int difference = Math.abs(s.length() - t.length());
        if (difference > 1) {
            return false;
        }
        
        int i = 0;
        int j = 0;
        while (i < s.length() && j < t.length()) {
            if (s.charAt(i) == t.charAt(j)) {
                i++;
                j++;
            } else {
                if (s.length() == t.length()) {
                    return s.substring(i + 1).equals(t.substring(j + 1));
                } else {
                    return s.substring(i + 1).equals(t.substring(j)) || s.substring(i).equals(t.substring(j + 1));
                }
            }
        }
        return Math.abs(s.length() - t.length()) == 1;
    }
}
