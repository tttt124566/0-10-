/*
Given a matrix of m x n elements (m rows, n columns), return all elements of the matrix in spiral order.

Example 1:

Input:
[
 [ 1, 2, 3 ],
 [ 4, 5, 6 ],
 [ 7, 8, 9 ]
]
Output: [1,2,3,6,9,8,7,4,5]
Example 2:

Input:
[
  [1, 2, 3, 4],
  [5, 6, 7, 8],
  [9,10,11,12]
]
Output: [1,2,3,4,8,12,11,10,9,5,6,7]

*/

class Solution {
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> list = new ArrayList<Integer>();
        // initial check
        if (matrix == null || matrix.length == 0) {
            return list;
        }
        int startRow = 0;
        int endRow = matrix.length - 1;
        int startCol = 0;
        int endCol = matrix[0].length - 1;
        while (startRow <= endRow && startCol <= endCol) {
            for (int j = startCol; j <= endCol; j++) {
                list.add(matrix[startRow][j]);
            }
            startRow++;
            
            for (int i = startRow; i <= endRow; i++) {
                list.add(matrix[i][endCol]);
            }
            endCol--;

            if (startRow <= endRow) {
                for (int j = endCol; j >= startCol; j--) {
                    list.add(matrix[endRow][j]);
                }
                endRow--;
            }

            if (startCol <= endCol) {
                for (int i = endRow; i >= startRow; i--) {
                    list.add(matrix[i][startCol]);
                }
                startCol++;
            }
        }
        return list;
    }
}
