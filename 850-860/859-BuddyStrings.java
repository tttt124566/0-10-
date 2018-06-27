/*
Given two strings A and B of lowercase letters, return true if and only if we can swap two letters in A so that the result equals B.

 

Example 1:

Input: A = "ab", B = "ba"
Output: true
Example 2:

Input: A = "ab", B = "ab"
Output: false
Example 3:

Input: A = "aa", B = "aa"
Output: true
Example 4:

Input: A = "aaaaaaabc", B = "aaaaaaacb"
Output: true
Example 5:

Input: A = "", B = "aa"
Output: false
 

Note:

0 <= A.length <= 20000
0 <= B.length <= 20000
A and B consist only of lowercase letters.
Seen this question in a real interview before?  Yes
*/

class Solution {
    public boolean buddyStrings(String A, String B) {
        if (A.length() < 2 && B.length() < 2) {
            return false;
        }
        if (A.length() != B.length()) {
            return false;
        }
        
        if (A.equals(B)) {
            Set<Character> s = new HashSet<Character>();
            for (char c : A.toCharArray()) s.add(c);
            return s.size() < A.length();
        }
        int index1 = -1;
        int index2 = -1;
        for (int i = 0; i < A.length(); i++) {
            if (A.charAt(i) != B.charAt(i)) {
                if (index1 == -1) {
                    index1 = i;
                } else if (index2 == -1) {
                    index2 = i;
                } else {
                    return false;
                }
            }
        }
        
        if (index1 != -1 && index2 != -1 && A.charAt(index1) == B.charAt(index2) && A.charAt(index2) == B.charAt(index1)) {
            return true;
        }
        return false;
    }
}
