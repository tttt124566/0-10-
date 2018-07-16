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
        int[] primes = {2, 3, 5};
        int[] pointer = new int[3];
        
        int[] ugly = new int[n];
        ugly[0] = 1;
        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;
            int minIndex = 0;
            
            for (int j = 0; j < primes.length; j++) {
                if (ugly[pointer[j]] * primes[j] < min) {
                    min = ugly[pointer[j]] * primes[j];
                    minIndex = j;
                } else if (ugly[pointer[j]] * primes[j] == min) {
                    pointer[j]++;
                }
            }
            
            pointer[minIndex]++;
            ugly[i] = min;
        }
        
        return ugly[n - 1];
    }
}
