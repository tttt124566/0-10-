/*
Given a 2D grid, each cell is either a wall 'W', an enemy 'E' or empty '0' (the number zero), return the maximum enemies you can kill using one bomb.
The bomb kills all the enemies in the same row and column from the planted point until it hits the wall since the wall is too strong to be destroyed.
Note: You can only put the bomb at an empty cell.

Example:

Input: [["0","E","0","0"],["E","0","W","E"],["0","E","0","0"]]
Output: 3 
Explanation: For the given grid,

0 E 0 0 
E 0 W E 
0 E 0 0

Placing a bomb at (1,1) kills 3 enemies.
*/

class Solution {
    public int maxKilledEnemies(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        
        int m = grid.length;
        int n = grid[0].length;
        int[][] count = new int[m][n];
        
        for (int i = 0; i < m; i++) {
            int head = 0;
            int tail = 0;
            for (int j = 0; j < n; j++) {
                count[i][j] = grid[i][j] != '0' ? 0 : (count[i][j] + head);
                count[i][n - j - 1] = grid[i][n - j - 1] != '0' ? 0 : (count[i][n - j - 1] + tail);
                head = grid[i][j] == 'W' ? 0 : head + (grid[i][j] == 'E' ? 1 : 0);
                tail = grid[i][n - j - 1] == 'W' ? 0 : tail + (grid[i][n - j - 1] == 'E' ? 1 : 0);
            }
        }
        
        for (int j = 0; j < n; j++) {
            int head = 0;
            int tail = 0;
            for (int i = 0; i < m; i++) {
                count[i][j] = grid[i][j] != '0' ? 0 : (count[i][j] + head);
                count[m - i - 1][j] = grid[m - i - 1][j] != '0' ? 0 : (count[m - i - 1][j] + tail);
                head = grid[i][j] == 'W' ? 0 : (head + (grid[i][j] == 'E' ? 1 : 0));
                tail = grid[m - i - 1][j] == 'W' ? 0 : (tail + (grid[m - i - 1][j] == 'E' ? 1 : 0));
            }
        }
        
        int res = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                res = Math.max(res, count[i][j]);
            }
        }
        
        return res;
    }
}
