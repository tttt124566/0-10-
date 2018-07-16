/*
Given a positive integer num, write a function which returns True if num is a perfect square else False.

Note: Do not use any built-in library function such as sqrt.

Example 1:

Input: 16
Returns: True
Example 2:

Input: 14
Returns: False

*/

class Solution {
    public boolean isPerfectSquare(int num) {
        long l = 0, r = num;
        while (l <= r) {
            long mid = l + (r - l) / 2;
            if (mid * mid == num) {
                return true;
            } else if (mid * mid > num) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }
}
