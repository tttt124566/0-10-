/*
Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.

The distance between two adjacent cells is 1.
Example 1: 
Input:

0 0 0
0 1 0
0 0 0
Output:
0 0 0
0 1 0
0 0 0
Example 2: 
Input:

0 0 0
0 1 0
1 1 1
Output:
0 0 0
0 1 0
1 2 1
Note:
The number of elements of the given matrix will not exceed 10,000.
There are at least one 0 in the given matrix.
The cells are adjacent in only four directions: up, down, left and right.

*/

class Solution {
    int[][] dirs = new int[][] {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    public int[][] updateMatrix(int[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            return new int[0][0];
        }
        
        int m = matrix.length;
        int n = matrix[0].length;
        Queue<Point> queue = new LinkedList<Point>();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (matrix[i][j] == 0) {
                    queue.offer(new Point(i, j));
                }
                else {
                    matrix[i][j] = Integer.MAX_VALUE;
                }
            }
        }
        
      
        while (!queue.isEmpty()) {
            Point front = queue.poll();
            for (int[] dir : dirs) {
                int x = front.x + dir[0];
                int y = front.y + dir[1];
                // we don't need visited matrix, just see if the ongoing route is longer than the current one.
                if (x >= 0 && y >= 0 && x < m && y < n && matrix[x][y] > matrix[front.x][front.y] + 1) {
                    matrix[x][y] = matrix[front.x][front.y] + 1;
                    queue.offer(new Point(x, y));
                }
            }
        }
        
        return matrix;
    }
}
