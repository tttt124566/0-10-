/*
Given a 32-bit signed integer, reverse digits of an integer.

Example 1:

Input: 123
Output: 321
Example 2:

Input: -123
Output: -321
Example 3:

Input: 120
Output: 21
Note:
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
*/

class Solution {
    public int reverse(int x) {
        int sign = 1;
        if (x < 0) {
            sign = -1;
            x = -x;
        }
        int res = 0;
        while (x != 0) {
            int a = x % 10;
            if (Integer.MAX_VALUE / 10 < Math.abs(res) || Integer.MAX_VALUE / 10 == res && a > Integer.MAX_VALUE % 10) {
                return 0;
            }
            res *= 10;
            res += a;
            x -= a;
            x /= 10;
        }
        
        return res * sign;
    }
}
