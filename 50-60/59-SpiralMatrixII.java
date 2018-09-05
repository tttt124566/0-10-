/*
Given a positive integer n, generate a square matrix filled with elements from 1 to n2 in spiral order.

Example:

Input: 3
Output:
[
 [ 1, 2, 3 ],
 [ 8, 9, 4 ],
 [ 7, 6, 5 ]
]
*/

class Solution {
    public int[][] generateMatrix(int n) {
        int[][] res = new int[n][n];
        
        int startRow = 0;
        int endRow = n - 1;
        int startCol = 0;
        int endCol = n - 1;
        int num = 1;
        while (startRow <= endRow && startCol <= endCol) {
            for (int j = startCol; j <= endCol; j++) {
                res[startRow][j] = num++;
            }
            startRow++;
            
            for (int i = startRow; i <= endRow; i++) {
                res[i][endCol] = num++;
            }
            endCol--;
            
            if (startRow <= endRow) {
                for (int j = endCol; j >= startCol; j--) {
                    res[endRow][j] = num++;
                }
                endRow--;
            }
            
            if (startCol <= endCol) {
                for (int i = endRow; i >= startRow; i--) {
                    res[i][startCol] = num++;
                }
                startCol++;
            }
        }
        
        return res;
    }
}
