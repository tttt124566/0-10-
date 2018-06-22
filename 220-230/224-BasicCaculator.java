/*
Implement a basic calculator to evaluate a simple expression string.

The expression string may contain open ( and closing parentheses ), the plus + or minus sign -, non-negative integers and empty spaces .

Example 1:

Input: "1 + 1"
Output: 2
Example 2:

Input: " 2-1 + 2 "
Output: 3
Example 3:

Input: "(1+(4+5+2)-3)+(6+8)"
Output: 23
Note:
You may assume that the given expression is always valid.
Do not use the eval built-in library function.
*/
class Solution {
    public int calculate(String s) {
        Stack<Integer> stack = new Stack<Integer>();
        int num = 0;
        int sign = 1;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) >= '0' && s.charAt(i) <= '9') {
                num = num * 10 + s.charAt(i) - '0';
            } else {
                if (s.charAt(i) == '+') {
                    result += num * sign;
                    sign = 1;
                    num = 0;
                } else if (s.charAt(i) == '-') {
                    result += num * sign;
                    sign = -1;
                    num = 0;
                } else if (s.charAt(i) == '(') {
                    stack.push(result);
                    stack.push(sign);
                    result = 0;
                    sign = 1;
                    num = 0;
                } else if (s.charAt(i) == ')') {
                    result += sign * num;
                    result = stack.pop() * result + stack.pop();
                    num = 0;
                    sign = 1;
                }
            }
        }
        return result + sign * num;
    }
}
