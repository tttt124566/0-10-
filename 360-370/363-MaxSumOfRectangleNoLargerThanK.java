/*
Given a non-empty 2D matrix matrix and an integer k, find the max sum of a rectangle in the matrix such that its sum is no larger than k.

Example:
Given matrix = [
  [1,  0, 1],
  [0, -2, 3]
]
k = 2
The answer is 2. Because the sum of rectangle [[0, 1], [-2, 3]] is 2 and 2 is the max number no larger than k (k = 2).

Note:
The rectangle inside the matrix must have an area > 0.
What if the number of rows is much larger than the number of columns?
*/

class Solution {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int result = Integer.MIN_VALUE;
        int rows = matrix.length;
        if (rows == 0) {
            return 0;
        }
        int cols = matrix[0].length;
        for (int i = 0; i < rows; i++) {
            int[] sumPer = new int[cols];
            for (int j = i; j >= 0; j--) {
                TreeSet<Integer> sumSet = new TreeSet<>();
                int sumCur = 0;
                sumSet.add(0);
                for (int t = 0; t < cols; t++) {
                    sumPer[t] += matrix[j][t];
                    sumCur += sumPer[t];
                    Integer subRes = sumSet.ceiling(sumCur - k);
                    if (subRes != null) {
                        result = Math.max(result, sumCur - subRes);
                    }
                    sumSet.add(sumCur);
                }
            }
        }
        return result;
    }
}
