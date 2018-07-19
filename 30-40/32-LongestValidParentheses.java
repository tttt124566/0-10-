/*
Given a string containing just the characters '(' and ')', find the length of the longest valid (well-formed) parentheses substring.

Example 1:

Input: "(()"
Output: 2
Explanation: The longest valid parentheses substring is "()"
Example 2:

Input: ")()())"
Output: 4
Explanation: The longest valid parentheses substring is "()()"
*/

class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        stack.push(-1);
        int longestLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if (stack.peek() != -1 && s.charAt(stack.peek()) == '(' && s.charAt(i) == ')') {
                stack.pop();
                longestLength = Math.max(longestLength, i - stack.peek());
            } else {
                stack.push(i);
            }
        }
        
        return longestLength;
    }
}
