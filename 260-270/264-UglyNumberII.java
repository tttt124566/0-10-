/*
Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 

Example:

Input: n = 10
Output: 12
Explanation: 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.
Note:  

1 is typically treated as an ugly number.
n does not exceed 1690.
*/

class Solution {
    public int nthUglyNumber(int n) {
        if (n <= 0) {
            return 0;
        }
        int[] result = new int[n];
        result[0] = 1;
        int t2 = 0;
        int t3 = 0;
        int t5 = 0;
        for (int i = 1; i < n; i++) {
            result[i] = Math.min(result[t2] * 2, Math.min(result[t3] * 3, result[t5] * 5));
            if (result[i] == result[t2] * 2) t2++;
            if (result[i] == result[t3] * 3) t3++;
            if (result[i] == result[t5] * 5) t5++;
        }
        return result[n-1];
    }
}
