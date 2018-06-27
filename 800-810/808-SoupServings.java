/*There are two types of soup: type A and type B. Initially we have N ml of each type of soup. There are four kinds of operations:

Serve 100 ml of soup A and 0 ml of soup B
Serve 75 ml of soup A and 25 ml of soup B
Serve 50 ml of soup A and 50 ml of soup B
Serve 25 ml of soup A and 75 ml of soup B
When we serve some soup, we give it to someone and we no longer have it.  Each turn, we will choose from the four operations with equal probability 0.25. If the remaining volume of soup is not enough to complete the operation, we will serve as much as we can.  We stop once we no longer have some quantity of both types of soup.

Note that we do not have the operation where all 100 ml's of soup B are used first.  

Return the probability that soup A will be empty first, plus half the probability that A and B become empty at the same time.

 

Example:
Input: N = 50
Output: 0.625
Explanation: 
If we choose the first two operations, A will become empty first. For the third operation, A and B will become empty at the same time. For the fourth operation, B will become empty first. So the total probability of A becoming empty first plus half the probability that A and B become empty at the same time, is 0.25 * (1 + 1 + 0.5 + 0) = 0.625.

Notes:

0 <= N <= 10^9. 
Answers within 10^-6 of the true value will be accepted as correct.

*/
class Solution {
    public double soupServings(int N) {
        if (N > 5000) {  // trick
            return 1.0;
        }

        double[][] memo = new double[N + 1][N + 1];
        return soupServingsHelper(N, N, memo);
    }
    
    public double soupServingsHelper(int A, int B, double[][] memo) {
        if (A <= 0 && B <= 0) {
            return 0.5;
        }
        if (A <= 0 && B > 0) {
            return 1.0;
        }
        if (A > 0 && B <= 0) {
            return 0.0;
        }
        if (memo[A][B] != null) {
            return memo[A][B];
        }
        memo[A][B] = 0.0;
        int[] serveA = {100, 75, 50, 25};
        int[] serveB = {0, 25, 50, 75};
        for (int i = 0; i < 4; i++) {
            memo[A][B] += soupServingsHelper(A - serveA[i], B - serveB[i], memo);
        }
        memo[A][B] = memo[A][B] * 0.25
        return memo[A][B];
    }
}
