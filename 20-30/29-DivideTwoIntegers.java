/*
Given two integers dividend and divisor, divide two integers without using multiplication, division and mod operator.

Return the quotient after dividing dividend by divisor.

The integer division should truncate toward zero.

Example 1:

Input: dividend = 10, divisor = 3
Output: 3
Example 2:

Input: dividend = 7, divisor = -3
Output: -2
Note:

Both dividend and divisor will be 32-bit signed integers.
The divisor will never be 0.
Assume we are dealing with an environment which could only store integers within the 32-bit signed integer range: [−231,  231 − 1]. For the purpose of this problem, assume that your function returns 231 − 1 when the division result overflows.
*/

class Solution {
    public int divide(int dividend, int divisor) {
        if (divisor == 0 || dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        
        int sign = (dividend > 0) ^ (divisor > 0) ? -1 : 1;
        long ldividend = Math.abs((long)dividend);
        long ldivisor = Math.abs((long)divisor);
        long multiplir = 1;
        long ans = 0;
        
        // STEP 1: shift "divisor" to the most siginificant bit
        while ((ldivisor << 1) <= ldividend) {
            multiplir <<= 1;
            ldivisor <<= 1;
        }
        
        while (multiplir > 0) {
            if (ldividend >= ldivisor) {
                ldividend -= ldivisor;
                ans += multiplir;
            }
            multiplir >>= 1;
            ldivisor >>= 1;
        }
        
        return sign == 1 ? (int) ans : (int) -ans;
    }
}
