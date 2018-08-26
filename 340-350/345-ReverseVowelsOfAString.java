/*
Write a function that takes a string as input and reverse only the vowels of a string.

Example 1:

Input: "hello"
Output: "holle"
Example 2:

Input: "leetcode"
Output: "leotcede"
Note:
The vowels does not include the letter "y".
*/

class Solution {
    private boolean isVowel(char c) {
        return c == 'e' || c == 'a' || c == 'i' || c == 'o' || c == 'u' ||
            c == 'E' || c == 'A' || c == 'I' || c == 'O' || c == 'U';
    }
    
    public String reverseVowels(String s) {
        char[] array = s.toCharArray();
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && !isVowel(array[i])) {
                i++;
            }
            while (i < j && !isVowel(array[j])) {
                j--;
            }
            if (i < j) {
                char temp = array[i];
                array[i] = array[j];
                array[j] = temp;
                i++;
                j--;
            }
        }
        
        return new String(array);
    }
}
