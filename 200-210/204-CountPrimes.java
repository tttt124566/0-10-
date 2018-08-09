/*
Count the number of prime numbers less than a non-negative number, n.

Example:

Input: 10
Output: 4
Explanation: There are 4 prime numbers less than 10, they are 2, 3, 5, 7.
*/

class Solution {
    public int countPrimes(int n) {
        int count = 0;
        
        boolean[] isPrime = new boolean[n];
        Arrays.fill(isPrime, true);
        for (int i = 2; i < n; i++) {
            if (isPrime[i]) {
                count++;
                for (int j = 2; j * i < n; j++) {
                    isPrime[j * i] = false;
                }
            }
        }
        
        return count;
    }
}
