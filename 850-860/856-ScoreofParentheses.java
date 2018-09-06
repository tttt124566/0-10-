/*
Given a balanced parentheses string S, compute the score of the string based on the following rule:

() has score 1
AB has score A + B, where A and B are balanced parentheses strings.
(A) has score 2 * A, where A is a balanced parentheses string.
 

Example 1:

Input: "()"
Output: 1
Example 2:

Input: "(())"
Output: 2
Example 3:

Input: "()()"
Output: 2
Example 4:

Input: "(()(()))"
Output: 6
 

Note:

S is a balanced parentheses string, containing only ( and ).
2 <= S.length <= 50
*/

class Solution {
    int i = 0;
    public int scoreOfParentheses(String S) {
        int num = 0;
        while (i < S.length()) {
            char c = S.charAt(i);
            i++;
            if (c == '(') {
                if (S.charAt(i) == ')') {
                    num += 1;
                    i++;
                } else {
                    num += 2 * scoreOfParentheses(S);
                }
            } else {
                break;
            }
        }
        return num;
    }

}
