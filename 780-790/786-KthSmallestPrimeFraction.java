/*
A sorted list A contains 1, plus some number of primes.  Then, for every p < q in the list, we consider the fraction p/q.

What is the K-th smallest fraction considered?  Return your answer as an array of ints, where answer[0] = p and answer[1] = q.

Examples:
Input: A = [1, 2, 3, 5], K = 3
Output: [2, 5]
Explanation:
The fractions to be considered in sorted order are:
1/5, 1/3, 2/5, 1/2, 3/5, 2/3.
The third fraction is 2/5.

Input: A = [1, 7], K = 1
Output: [1, 7]
Note:

A will have length between 2 and 2000.
Each A[i] will be between 1 and 30000.
K will be between 1 and A.length * (A.length - 1) / 2.
*/
class Solution {
    public int[] kthSmallestPrimeFraction(int[] A, int K) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> A[a[0]] * A[b[1]] - A[a[1]] * A[b[0]]);
        int m = A.length;
        for (int i = 0; i < m; i++) {
            pq.offer(new int[]{i, m - 1});
        }
        for (int i = 0; i < K-1; i++) {
            int[] pop = pq.poll();
            int ni = pop[0];
            int di = pop[1];
            if (pop[1] - 1 > pop[0]) {
                pop[1]--;
                pq.offer(pop);
            }
        }
        int[] peek = pq.peek();
        return new int[]{A[peek[0]], A[peek[1]]};
    }
}
