/*
There are a row of n houses, each house can be painted with one of the k colors. The cost of painting each house with a certain color is different. You have to paint all the houses such that no two adjacent houses have the same color.

The cost of painting each house with a certain color is represented by a n x k cost matrix. For example, costs[0][0] is the cost of painting house 0 with color 0; costs[1][2] is the cost of painting house 1 with color 2, and so on... Find the minimum cost to paint all houses.

Note:
All costs are positive integers.

Example:

Input: [[1,5,3],[2,9,4]]
Output: 5
Explanation: Paint house 0 into color 0, paint house 1 into color 2. Minimum cost: 1 + 4 = 5; 
             Or paint house 0 into color 2, paint house 1 into color 0. Minimum cost: 3 + 2 = 5. 
Follow up:
Could you solve it in O(nk) runtime?

*/

class Solution {
    public int minCostII(int[][] costs) {
        if (costs == null || costs.length == 0) {
            return 0;
        }
        
        int min1 = Integer.MAX_VALUE;
        int min2 = Integer.MAX_VALUE;
        for (int i = 0; i < costs[0].length; i++) {
            if (costs[0][i] < min1) {
                min2 = min1;
                min1 = costs[0][i];
            } else if (costs[0][i] < min2) {
                min2 = costs[0][i];
            }
        }
        for (int i = 1; i < costs.length; i++) {
            int temp1 = Integer.MAX_VALUE;
            int temp2 = Integer.MAX_VALUE;
            for (int j = 0; j < costs[i].length; j++) {
                if (costs[i - 1][j] == min1) {
                    costs[i][j] += min2;
                } else {
                    costs[i][j] += min1;
                }
                
                if (costs[i][j] < temp1) {
                    temp2 = temp1;
                    temp1 = costs[i][j];
                } else if (costs[i][j] < temp2) {
                    temp2 = costs[i][j];
                }
            }
            min1 = temp1;
            min2 = temp2;
        }
        
        return min1;
    }
}
