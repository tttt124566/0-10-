/*
Given an input string, reverse the string word by word.

Example:  

Input: "the sky is blue",
Output: "blue is sky the".
Note:

A word is defined as a sequence of non-space characters.
Input string may contain leading or trailing spaces. However, your reversed string should not contain leading or trailing spaces.
You need to reduce multiple spaces between two words to a single space in the reversed string.
Follow up: For C programmers, try to solve it in-place in O(1) space.
*/

public class Solution {
    public String reverseWords(String s) {
        char[] a = s.toCharArray();
        reverseString(a, 0, a.length - 1);
        reverseWords(a);
        return cleanSpaces(a);
    }
    
    private String cleanSpaces(char[] a) {
        int i = 0, j = 0;
        int n = a.length;
        while (j < n) {
          while (j < n && a[j] == ' ') j++;             // skip spaces
          while (j < n && a[j] != ' ') a[i++] = a[j++]; // keep non spaces
          while (j < n && a[j] == ' ') j++;             // skip spaces
          if (j < n) a[i++] = ' ';                      // keep only one space
        }

        return new String(a).substring(0, i);
    }
    
    private void reverseWords(char[] s) {
        int i = 0; 
        int j = 0;
        while(i < s.length) {
            j = i;
            while (j < s.length && s[j] != ' ') {
                j++;
            }
            reverseString(s, i, j - 1);
            i = j + 1;
        }
    }
    
    private void reverseString(char[] s, int i, int j) {
        while(i < j) {
            char temp = s[i];
            s[i] = s[j];
            s[j] = temp;
            i++;
            j--;
        }
    }
}
